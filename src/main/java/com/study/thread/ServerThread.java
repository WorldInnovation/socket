package com.study.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerThread
{
	private ServerSocket serverSocket;



	public void start(int port) throws IOException
	{
		while (true)
		{
			new EchoClient(serverSocket.accept()).start();
		}
	}

	public void stop() throws IOException
	{
		serverSocket.close();
	}

	private static class EchoClient extends Thread
	{
		private Socket clientSocket;
		private BufferedReader in;
		private PrintWriter out;

		public EchoClient(Socket socket){
			this.clientSocket = socket;
		}

		public void run()
		{
			try
			{
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				out = new PrintWriter(clientSocket.getOutputStream(), true);

				String inputLine;
				while ((inputLine = in.readLine()) != null)
				{
					out.println(inputLine);
				}
			}
			catch (IOException e)
			{
				System.out.println("Server client has error:" + e.getMessage());
			}

		}

	 }
}
