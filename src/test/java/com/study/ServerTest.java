package com.study;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.*;


class ServerTest
{

	@Test
	void echoServerStart() throws IOException
	{
		ServerSocket serverSocket = new  ServerSocket(3000);
		Server server = new Server(serverSocket);
		server.echoServerStart();
	}
}
