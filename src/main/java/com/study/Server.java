package com.study;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class Server

{
	public static void main(String[] args) throws IOException
	{
		ServerSocket serverSocket = new ServerSocket(3000);
		Socket socket = serverSocket.accept();
		//--read
		InputStream inputStream = socket.getInputStream();
		byte[] buffer = new byte[50];
		int count = inputStream.read(buffer);

		StringBuilder stringBuilder = new StringBuilder("echo: ${");
		char[] echoChar = new char[50];
		for (int i = 0; i < count; i++)
		{
			echoChar[i] = (char) buffer[i];
		}
		String echoMessage = new String(echoChar, 0, count);

		stringBuilder.append(echoMessage);
		stringBuilder.append("}");
		String echoServer = stringBuilder.toString();

		OutputStream outputStream = socket.getOutputStream();
		outputStream.write(echoServer.getBytes(StandardCharsets.UTF_8));


	}
}
