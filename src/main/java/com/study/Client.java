package com.study;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class Client
{
	public static void main(String[] args) throws IOException
	{
		Socket socket = new Socket("localhost", 3000);
		//out
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write("client test".getBytes(StandardCharsets.UTF_8));

		InputStream inputStream = socket.getInputStream();
		byte[] buffer = new byte[50];
		int count = inputStream.read(buffer);
		System.out.println(new String(buffer, 0, count));
	}
}
