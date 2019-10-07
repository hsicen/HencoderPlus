# 线程间交互
### 一个线程启动另一个线程  
> `new Thread().start()`  `Executor.execute()` 等

###  一个线程终结另一个线程
> - `Thread.stop()`  暴力终结,结果不可预期   
> - `Thread.interrupt()` 预期的终结,不立即,不强制
> 
> **Thread.interrupted() 和 isInterrupted()：**都是检查中断状态，但是静态方法会重置中断状态为false
**InterruptedException：**如果在线程等待时中断，或者在中断状态等待，会直接结束等待过程(因为等待过程什么也不会做，而interrupt的目的是让线程做完收尾工作尽快终结，所以要跳过等待过程)
**SystemClock.sleep()：**不会被打断，不会有Exception

### Object.wait()和Object.notify()/Object.notifyAll
> - 在为满足条件时wait，利用while判断条件，不能用if判断
> - 利用while循环检查wait条件
> - 设置完成后 notifyAll()
> - wait()和notify()/notifyAll() 都需要放在同步代码块中

###  Thread.join()

> 让另一个线程插在自己前面运行

### Thread.yield()

> 暂时让出自己的时间片给同优先级的线程

# Android的Handler机制

### 本质

> 在某个指定的运行中的线程上执行代码(执行任务Runnable)

### 思路

> 在接受任务的线程上执行循环判断

### 基本实现

> - Thread 里while循环检查(Looper.looop())
> - 加上Looper(优势在于自定义Thread的代码可以少写很多)
> - 再加上Handler(优势在于功能分拆，而且可以有多个Handler)

### Java的Handler机制

> - HandlerThread  具体的线程
> - Looper   负责循环，条件判断和执行任务
> - Handler   负责任务的制定和线程间传递

### AsyncTask

> ##### AsyncTask的内存泄露
> 众所周知的原因：AsyncTask持有外部Activity的引用
> 没提到的原因：执行中的线程不会被系统回收
>
> Java的回收策略：没有被GC Root直接或者间接持有引用的对象，会被回收
> GC Root：
> 1.运行中的线程
> 2.静态对象
> 3.来自 native code 中的引用
>
> ##### 总结
> - AsyncTask的内存泄露，其它类型的线程方案(Thread，Executor，HandlerThread)一样都有，所以不要忽略它们，或者认为AsyncTask比别的方案更危险。并没有
> - 就算使用AsyncTask，只要任务的时间不长(例如 10s 之内)，那就完全没有必要做防止内存泄露的处理
>

###  Service和IntentService

> 拥有Context的Service
>
> - Service：后台任务的活动空间，统一管理      适用场景：音乐播放器等
> - IntentService：执行单个任务后自动关闭的Service
> 

### Executor  AsyncTask  HandlerThread  IntentService  Service  的选择

> 原则： 哪个简单使用哪个
>
> 1.能用Executor就用Executor
> 2.需要用到后台线程推送任务到UI线程时，再考虑AsyncTask或者Handler
> 3.HandlerThread使用场景：原本它设计的使用场景是在已经运行的指定线程上执行代码，但现实开发中，除了主线程之外，几乎没有这种需求，因为HandlerThread和Executor相比在实际应用中并没有什么优势，反而用起来会麻烦一点。
> 4.IntentService：首先，它是一个带Context的Service；另外，它在处理线程本身，没有比Executor有任何优势

### Executor和HandlerThread的关闭

> 如果在界面组件里创建Executor或者HandlerThread，记得要在组件关闭的时候(如：Activity.onDestroy())关闭Executor和HandlerThread
>
> ```java
> @Override
> protected void onDestroy() {
>    	super.onDestroy();
>    	executor.shutdown();
> }
> ```
>
> ```java
> @Override
> protected void onDestroy() {
>    	super.onDestroy();
>    	handlerThread.quit(); // 这个其实就是停止 Looper 的循环
> }
> ```
> 
