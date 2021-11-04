package com.study.thread;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class ClientHandler implements Runnable
{
	public static final String ERROR_STOP_ECHO_SERVER_MESSAGE = "IOError in ClientHandler:";
	private final Socket clientSocket;

	public ClientHandler(Socket socket){
		this.clientSocket = socket;
	}

	public void run()
	{
		System.out.println("Client handler run on server");

		try(
				Scanner scanner = new Scanner(clientSocket.getInputStream());
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		)
		{

			while (scanner.hasNextLine()){

				String echoMessage = scanner.nextLine();
				//while((echoMessage = in.readLine()) != null)
				//{
				System.out.println("Client handler got message:" + echoMessage);
				StringBuilder stringBuilder = new StringBuilder("echo: ${");
				stringBuilder.append(echoMessage);
				stringBuilder.append("}");
				String echoServer = stringBuilder.toString() + "\n";
				out.write(echoServer, 0, echoServer.length());
				out.newLine();
				out.flush();
			}

			//}
		}

		catch (IOException e)
		{
			//System.out.println(ERROR_STOP_ECHO_SERVER_MESSAGE + e.getMessage());
			e.printStackTrace();
		}

	}

}
