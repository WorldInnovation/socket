package com.study.thread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerThread
{
	public static final int SOCKET_PORT = 3000;
	public static final String ERROR_STOP_ECHO_SERVER_MESSAGE = "IOError when close echo server:";

	public static void main(String[] args) throws IOException
	{
		try (
				ServerSocket serverSocket = new ServerSocket(SOCKET_PORT);

		)
		{
			System.out.println("Server start on localhost:3000");
			while (true)
			{
				try (
						Socket socket = serverSocket.accept();
				)
				{
					System.out.println("Server have got connection:" + socket.getInetAddress().getHostAddress());
					//ClientHandler clientSocket = new ClientHandler(socket);
					new Thread(new ClientHandler(socket)).start();
					System.out.println(socket.getInetAddress().getCanonicalHostName());
				}
			}
		}
	}

	private static class ClientHandler implements Runnable
	{
		private final Socket clientSocket;

		public ClientHandler(Socket socket){
			this.clientSocket = socket;
		}

		public void run()
		{
			System.out.println("Server run");

			try(
					BufferedReader	in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			)
			{
				String echoMessage = null;
				while((echoMessage = in.readLine()) != null)
				{
					System.out.println("Server got message:" + echoMessage);
					StringBuilder stringBuilder = new StringBuilder("echo: ${");
					stringBuilder.append(echoMessage);
					stringBuilder.append("}");
					String echoServer = stringBuilder.toString() + "\n";
					out.write(echoServer, 0, echoServer.length());
					out.newLine();
					out.flush();
				}

			}
			catch (IOException e)
			{
				System.out.println(ERROR_STOP_ECHO_SERVER_MESSAGE + e.getMessage());
			}

		}

	}

}

