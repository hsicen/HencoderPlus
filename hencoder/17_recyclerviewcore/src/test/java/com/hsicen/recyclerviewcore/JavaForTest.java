package com.hsicen.recyclerviewcore;

import org.junit.Test;

/**
 * 作者：hsicen  2020/3/27 18:00
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
public class JavaForTest {

    @Test
   public void rangeLoop() {
        int bottomVisiblePosition = 4;

        for (int i = bottomVisiblePosition - 1, j = 1; i >= 0; i--, j++) {
            System.out.println("result:  " + i);
        }
    }
}
