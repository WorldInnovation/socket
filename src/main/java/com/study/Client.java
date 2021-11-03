package com.study;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import static com.study.Server.BUFFER_CAPACITY;
import static com.study.Server.ERROR_STOP_ECHO_SERVER_MESSAGE;


public class Client
{
	public static final String ERROR_CLIENT_SOCKET_START = "Error client socket start:";
	public static final int BUFFER_CAPACITY = 2048;
	public static final String ERROR_STOP_ECHO_SERVER_MESSAGE = "IOError when close echo server:";
	public static final String UNKNOWN_HOST_ERROR = " Unknown host error:";
	public static final String SOCKET_HOST = "localhost";
	public static final int SOCKET_PORT = 3000;



	public Client()
	{
	}

	public static void echoClientStart(Socket socket)
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
		catch (UnknownHostException e)
		{
			System.out.println(UNKNOWN_HOST_ERROR + e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println(ERROR_CLIENT_SOCKET_START + e.getMessage());
		}
	}

	public static void echoClientStop(Socket socket)
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



	public static void main(String[] args) throws IOException
	{
		Socket socket = new Socket(SOCKET_HOST, SOCKET_PORT);
		echoClientStart(socket);
		echoClientStop(socket);
	}
}
