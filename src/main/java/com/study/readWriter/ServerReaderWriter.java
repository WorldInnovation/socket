package com.study.readWriter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerReaderWriter
{
	private static Socket clientSocket;
	private static ServerSocket server;
	private static BufferedReader in;
	private static BufferedWriter out;

	public static void main(String[] args) throws IOException
	{

		try
		{
			server = new ServerSocket(3000);
			clientSocket = server.accept();
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			System.out.println("Server start on localhost:3000");
			String echoMessage = in.readLine();
			System.out.println("Server got message:" + echoMessage);

			StringBuilder stringBuilder = new StringBuilder("echo: ${");
			stringBuilder.append(echoMessage);
			stringBuilder.append("}");
			String echoServer = stringBuilder.toString();
			out.write(echoServer, 0, echoServer.length());
			out.flush();

		}
		finally
		{
			clientSocket.close();
			in.close();
			out.close();
		}

	}
}
