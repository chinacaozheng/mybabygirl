package com.lixin.socketpoolrmicache_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class AppClient
{

	public AppClient()
	{
		System.out.println("客户端正在启动.....");

		while (true)
		{
			try
			{

				// 1.建立连接
				Socket socket = new Socket("127.0.0.1", 8888);

				System.out.println("客户机和服务器建立了连接");

				// 2.客户端输入消息
				System.out.println("请客户机输入消息---------------:");

				Scanner s = new Scanner(System.in);
				String message = s.next();

				System.out.println("客户机输入的消息为:" + message);

				// 3.发送消息
				PrintWriter pw = new PrintWriter(socket.getOutputStream());

				pw.println(message);
				pw.flush();
				
				
				//4.接受服务器返回的消息
				BufferedReader   br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String   recriver=br.readLine();
				
				System.out.println("客户机接受服务器的消息为:"+recriver);

			} catch (UnknownHostException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args)
	{
		new AppClient();
	}
}
