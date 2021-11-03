package com.study;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class Server

{
	public static final String ERROR_ECHO_SERVER_MESSAGE = "IOError in echo server:";
	public static final int BUFFER_CAPACITY = 2048;
	public static final String ERROR_STOP_ECHO_SERVER_MESSAGE = "IOError when close echo server:";
	ServerSocket serverSocket;

	public Server(ServerSocket serverSocket)
	{
		this.serverSocket = serverSocket;
	}


	public void echoServerStart()
	{
		try (
				Socket socket = serverSocket.accept();
				InputStream inputStream = socket.getInputStream();
				OutputStream outputStream = socket.getOutputStream();
		)
		{
			byte[] buffer = new byte[BUFFER_CAPACITY];
			int count = inputStream.read(buffer);

			StringBuilder stringBuilder = new StringBuilder("echo: ${");

			char[] echoChar = new char[BUFFER_CAPACITY];
			for (int i = 0; i < count; i++)
			{
				echoChar[i] = (char) buffer[i];
			}

			String echoMessage = new String(echoChar, 0, count);
			stringBuilder.append(echoMessage);
			stringBuilder.append("}");
			String echoServerResponse = stringBuilder.toString();

			outputStream.write(echoServerResponse.getBytes(StandardCharsets.UTF_8));
		}
		catch (IOException e)
		{
			System.out.println(ERROR_ECHO_SERVER_MESSAGE + e.getMessage());
		}
	}

	public void echoServerStop()
	{
		try
		{
			serverSocket.close();
		}
		catch (IOException e)
		{
			System.out.println(ERROR_STOP_ECHO_SERVER_MESSAGE + e.getMessage());
		}
	}


/*	public static void main(String[] args) throws IOException
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
	}*/
}
