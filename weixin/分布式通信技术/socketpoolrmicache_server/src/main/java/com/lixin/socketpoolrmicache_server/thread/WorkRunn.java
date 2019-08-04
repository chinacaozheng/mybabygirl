package com.lixin.socketpoolrmicache_server.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class WorkRunn  implements Runnable
{
	private Socket  socket;
	public WorkRunn(Socket  socket)
	{
		this.socket=socket;
	}

	public void run()
	{
		// TODO Auto-generated method stub
		try
		{
			BufferedReader  br= new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		    String clientMsg =br.readLine();
		    
		    System.out.println("服务器端接受的客户端的消息为:"+Thread.currentThread().getName()+",-->"+clientMsg);
		
		
		    try
			{
		    	
		    	//模拟服务器完成任务，需要20秒
				Thread.sleep(15*1000);
				String[]  msgs=clientMsg.split(",");
				
				if(msgs[0].equals("wx"))
				{
					//java调用python服务，不能使用rmi，rmi属于远程方法调用，rmi是有局限的,只能是java平台
					System.out.println("客户机的请求是操作 微信");
					
					Socket socket  = new Socket("127.0.0.1",8878);
					System.out.println("java和python微信服务端建立了连接");
					
					PrintWriter  pw = new PrintWriter(socket.getOutputStream());
					pw.println(clientMsg);
					pw.flush();
					
					
				}
				else if(msgs[0].equals("mail"))
				{
					System.out.println("客户机的请求是操作-->用户发送邮件");
					Socket socket  = new Socket("127.0.0.1",9998);
					System.out.println("java和python邮件服务端建立了连接");
					
					PrintWriter  pw = new PrintWriter(socket.getOutputStream());
					pw.println(clientMsg);
					pw.flush();
				}
				else  if(msgs[0].equals("code"))
				{
	            System.out.println("客户机的请求是操作-->用户的需要验证码");
					
					Socket socket  = new Socket("127.0.0.1",8889);
					System.out.println("java和用户的服务端建立了连接");
					
					PrintWriter  pw = new PrintWriter(socket.getOutputStream());
					pw.println(clientMsg);
					pw.flush();
					
					BufferedReader cbr = new BufferedReader(new InputStreamReader(socket.getInputStream()));

					String codeMsg = cbr.readLine();

					System.out.println("接受服务器验证码为:" + Thread.currentThread().getName() + ",-->" + codeMsg);

					
					
				}
				
				
				
				
				
				
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		    
		    
		    
		

			// 3.发送消息
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			pw.println("服务器向你们问好******************");
			pw.flush();
		    
		    
		
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
