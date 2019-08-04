package com.lixin.socketpoolrmicache_userserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ServiceLoader;

import com.lixin.interfaces.ICode;

/**
 * Hello world!
 *
 */
public class AppUserServer
{

	public AppUserServer()
	{
		System.out.println("用户的操作的服务器正在启动******************");

		try
		{
			ServerSocket serverSocket = new ServerSocket(8889);

			while (true)
			{
				Socket socket = serverSocket.accept();
				System.out.println("用户的操作的服务器建立了连接");

				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				String clientMsg = br.readLine();

				System.out.println("服务器端接受的客户端的消息为:" + Thread.currentThread().getName() + ",-->" + clientMsg);

				ServiceLoader<ICode> codeService = ServiceLoader.load(ICode.class);

				ICode scode = null;
				for (ICode code : codeService)
				{
					scode = code;
				}
				String codeValue = scode.createCode();

				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				pw.println(codeValue);
				pw.flush();
			}

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{

		new AppUserServer();
	}
}
