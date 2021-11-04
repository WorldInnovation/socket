package com.study.thread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerThread
{
	public static final int SOCKET_PORT = 3000;

	public static void main(String[] args)
	{
		try (
				ServerSocket serverSocket = new ServerSocket(SOCKET_PORT);

		)
		{
			System.out.println("Server start on localhost:3000");
			ExecutorService pool = Executors.newCachedThreadPool();
			while (true)
			{
						Socket socket = serverSocket.accept();
				{
					System.out.println("Server have got connection:" + socket.getInetAddress().getHostAddress());
					ClientHandler clientSocket = new ClientHandler(socket);
					pool.execute(clientSocket);
					System.out.println(socket.getInetAddress().getCanonicalHostName());
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}

