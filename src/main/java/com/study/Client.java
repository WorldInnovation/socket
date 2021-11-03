package com.study;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static com.study.Server.BUFFER_CAPACITY;
import static com.study.Server.ERROR_STOP_ECHO_SERVER_MESSAGE;


public class Client
{
	public static final String ERROR_CLIENT_SOCKET_START = "Error client socket start:";
	public static final int BUFFER_CAPACITY = 2048;
	public static final String ERROR_STOP_ECHO_SERVER_MESSAGE = "IOError when close echo server:";

	Socket socket;

	public Client(Socket socket)
	{
		this.socket = socket;
	}

	public void echoClientStart()
	{
		try(
				OutputStream out = socket.getOutputStream();
				InputStream in = socket.getInputStream();
				)
		{
			out.write("client test".getBytes(StandardCharsets.UTF_8));
			byte[] buffer = new byte[BUFFER_CAPACITY];
			int count = in.read(buffer);
			System.out.println(new String(buffer, 0, count));
		}
		catch (IOException e)
		{
			System.out.println(ERROR_CLIENT_SOCKET_START + e.getMessage());
		}
	}

	public void echoClientStop()
	{
		try
		{
			socket.close();
		}
		catch (IOException e)
		{
			System.out.println(ERROR_STOP_ECHO_SERVER_MESSAGE + e.getMessage());
		}
	}



/*	public static void main(String[] args) throws IOException
	{
		Socket socket = new Socket("localhost", 3000);
		//out
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write("client test".getBytes(StandardCharsets.UTF_8));

		InputStream inputStream = socket.getInputStream();
		byte[] buffer = new byte[50];
		int count = inputStream.read(buffer);
		System.out.println(new String(buffer, 0, count));
	}*/
}
