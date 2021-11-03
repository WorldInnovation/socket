package com.study.readWriter;

import java.io.*;
import java.net.Socket;


public class ClientReaderWriter
{
	private static BufferedReader in;
	private static BufferedWriter out;

	public static void main(String[] args) throws IOException
	{
		Socket socket = new Socket("localhost", 3000);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter message for echo server, please:");
		String clientMessage = reader.readLine();

		out.write(clientMessage, 0, clientMessage.length());//send
		out.newLine();
		out.flush();
		String responseMessage = in.readLine();
		System.out.println("Response from server:" + responseMessage);

	}
}
