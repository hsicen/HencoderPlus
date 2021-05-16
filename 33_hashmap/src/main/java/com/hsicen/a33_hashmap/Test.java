package com.hsicen.a33_hashmap;

import java.util.HashMap;

/**
 * 作者：hsicen  2020/3/4 18:57
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
public class Test {
  private static final int MAXIMUM_CAPACITY = 1 << 30;

  public static void main(String[] args) {
    System.out.println(tableSizeFor(15));
    System.out.println(tableSizeFor(16));
    System.out.println(tableSizeFor(17));

    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put("key", "Hello");
  }

  private static int tableSizeFor(int cap) {
    int n = cap - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
  }
}
