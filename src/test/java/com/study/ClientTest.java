package com.study;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;


class ClientTest
{

	@Test
	void echoClientStart() throws IOException
	{
		try (Socket socket = new Socket("localhost", 3000))
		{
			Client client = new Client(socket);
			client.echoClientStart();
		}
	}
}
