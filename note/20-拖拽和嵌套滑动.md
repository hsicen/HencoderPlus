### Android拖拽

#### View.onDragListener(View)
- Api 11引入的工具类，用于实现View的拖拽操作，列表拖动

- 适用于用户的**托起**,**放下**操作，重在内容的移动，拖拽时可以附加拖拽数据，数据分为本地数据**LocalState**(App内进行拖拽)，跨进程数据**ClipData**(两个App之间进行拖拽)；比如添加物品进购物车

- 不需要进行自定义View，使用**view.startDrag()**/**startDragAndDrop()**来启动拖拽操作
  通过**view.setDragListener()**或者重写View的**onDragEvent(**)来监听View的拖拽状态
  
-  拖拽原理：创建一个图层(DragShadowBuilder)在屏幕的最上层,这个图层会随着用户手指的移动而移动
	

**使用**

> **开启拖拽**
>
> ```Kotlin
> val clipData = ClipData.newPlainText("name", "drag data")
> ViewCompat.startDragAndDrop(view, clipData, View.DragShadowBuilder(it), "LocalState", 0)
> 
> //或者view.startDragAndDrop()
> val clipData = ClipData.newPlainText("name", "drag data")
> view.startDragAndDrop(clipData, View.DragShadowBuilder(it), "LocalState", 0)
> ```
>
> **拖拽监听**
>
> ```kotlin
> view.setOnDragListener(HDragListener)
> 
> //重写OnDragListener实现拖拽监听
> inner class HDragListener : OnDragListener {
>      override fun onDrag(v: View, event: DragEvent): Boolean {
>            when (event.action) {
>                DragEvent.ACTION_DRAG_STARTED ->{
>                    //开始拖拽时回调
>                }
>                DragEvent.ACTION_DRAG_ENTERED ->{
>                    //拖拽到View的边界内回调(可进行排序操作)
>                }
>                DragEvent.ACTION_DRAG_ENDED ->{
>                    //结束拖拽时回调
>                }
>           }
>   
>           return true
>     }
> }
> 
> //或者重写View的onDragEvent()实现拖拽监听
> override fun onDragEvent(event: DragEvent): Boolean {
>  	//当前界面中所有View都会回调这个方法
> }
> ```



#### ViewDragHelper(ViewGroup)

- 2015年 SupportV4 包新增的工具类，主要用于ViewGroup中子View的拖拽操作
- 需要开发者在自定义ViewGroup中使用，重写ViewGroup的onInterceptTouchEvent()和onTouchEvent()来接管触摸事件
- **拖拽原理：**实时修改被拖拽的子View的mLeft,mTop,mRight,mBottom值

**使用：**

>自定义Callback实现ViewDragHelper.callback接口，**监听拖拽**回调
>```kotlin
>inner class HDragCallback : ViewDragHelper.Callback() {
>  //记录下被托起的View的初始位置
>  private var capturedLeft = 0
>  private var capturedTop = 0
>
>  /*** 返回true时View可以被拖起来*/
>  override fun tryCaptureView(child: View, pointerId: Int): Boolean {
>    	return true
>  }
>
>  override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
>    	return left  //水平偏移
>  }
>
>  override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
>    	return top  //垂直偏移
>  }
>
>  /*** View被拖起时回调*/
>  override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
>    	capturedChild.elevation = elevation + 1
>    	capturedLeft = capturedChild.left
>    	capturedTop = capturedChild.top
>  }
>
>  /*** 拖拽状态改变时回调*/
>  override fun onViewDragStateChanged(state: Int) {
>    	if (state == ViewDragHelper.STATE_IDLE) {
>      		val capturedView = mDragHelper.capturedView!!
>      		capturedView.elevation = capturedView.elevation - 1
>    	}
>  }
>
>  override fun onViewPositionChanged(changedView: View, left: Int, top: Int,dx: Int, dy: Int) {
>    	//位置改变时回调
>  }
>
>  /*** View被松开时回调*/
>  override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
>    	mDragHelper.settleCapturedViewAt(capturedLeft, capturedTop)
>    		//更新下一帧的绘制 和computeScroll结合
>    		postInvalidateOnAnimation()
>  	}
>}
>```
>重写ViewGroup的onInterceptTouchEvent()和onTouchEvent()来接管触摸事件
>
>```kotlin
>private val mDragHelper by lazy {
>  	ViewDragHelper.create(this, HDragCallback())
>}
>
>override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
>  	return mDragHelper.shouldInterceptTouchEvent(ev)
>}
>
>override fun onTouchEvent(event: MotionEvent): Boolean {
>  	mDragHelper.processTouchEvent(event)
>  	return true
>}
>
>//和DragCallback的onViewReleased()相结合,循环绘制每一帧
>override fun computeScroll() {
>  	if (mDragHelper.continueSettling(true)) {
>    		ViewCompat.postInvalidateOnAnimation(this)
>  	}
>}
>```


### 嵌套滑动

##### 不同向嵌套
- **onInterceptTouchEvent**   父 View 拦截子View
- **requestDisallowInterceptTouchEvent()**  子 View 阻止父 View 拦截

##### 同向嵌套
- 父 View 会彻底卡住子 View(滑动冲突)
  **原因：**抢夺条件一致，但父View的onInterceptTouchEvent() 早于子View的dispatchTouchEvent()
  
- 本质上是策略问题：嵌套状态下用户手指滑动，他是想滑谁？
  1. **场景一：NestedScrollView**   子View能滑动的时候滑动子View；滑不动的时候滑动父View
  
  2. 场景二：Google 的样例
  
     **父View展开的时候：**
  
     ​	上滑：优先滑动父View
  
     ​	下滑：滑不动
  
     **父View半展开的时候：**
  
     ​	上滑：优先滑动父View，滑到父View完全折叠后开始滑动子View
  
     ​	下滑：优先滑动父View，滑到父View完全展开后开始滑动子View
  
     **父View折叠的时候：**
  
     ​	上滑：滑动子View
  
     ​	下滑：优先滑动子View，滑到子View顶部后开始滑动父View
  
- 滑动嵌套解决方案： 自定义滑动策略(父View，子View谁来消费滑动事件)

- 实现：

  1. 大多数场景下SDk就能解决：ScrollView嵌套问题,换成NestedScrollView
  2. 自己实现：实现 NestedScrollingChild2 接口来实现自定义的嵌套滑动逻辑