package org.wzp.nio;

import java.nio.*;
/**
 * 缓冲区类型
 * Buffer 有多种类型，ByteBuffer,CharBuffer,ShortBuffer,IntBuffer,LongBuffer,FloatBuffer,DoubleBuffer。最常用的是 ByteBuffer
 * @author wzp
 * @date: 2018年3月1日 上午10:35:33 
 *
 */
public class UseFloatBuffer {
	public static void main(String[] args) {
		FloatBuffer buffer = FloatBuffer.allocate(10);
		for(int i = 0; i < buffer.capacity(); i++) {
			float f = (float) Math.sin((((float)i) / 10) * ( 2 * Math.PI));
			buffer.put(f);
		}
		
		buffer.flip();
		
		while(buffer.hasRemaining()) {
			float f = buffer.get();
			System.out.println(f);
		}
	}
}
