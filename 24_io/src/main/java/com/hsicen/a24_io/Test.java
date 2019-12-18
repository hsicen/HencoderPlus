package com.hsicen.a24_io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <p>作者：hsicen  2019/12/12 23:17
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */
public class Test {


	private void copyFiles() {
		try (
			InputStream fis = new FileInputStream("./hsc.txt");
			OutputStream fos = new FileOutputStream("./hscCopy.txt");
		) {

			byte[] bytes = new byte[1024];
			int len = 0;
			while ((len = fis.read(bytes)) != -1) {
				fos.write(bytes, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
