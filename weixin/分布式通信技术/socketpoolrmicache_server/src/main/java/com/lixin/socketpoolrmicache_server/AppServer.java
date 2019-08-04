package com.lixin.socketpoolrmicache_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import  java.util.concurrent.Executors;

import com.lixin.socketpoolrmicache_server.thread.WorkRunn;;


/**
 * Hello world!
 *
 */
public class AppServer 
{
	private  ServerSocket  serverSocket;
	
	
	public AppServer()
	{
		System.out.println("服务器正在启动....");
		try
		{
			serverSocket =new ServerSocket(8888);
			
			//创建固定数量的线程池
			ExecutorService  Threadpool= Executors.newFixedThreadPool(2);
			
			while(true)
			{
				Socket  socket=serverSocket.accept();
				 
				System.out.println("服务器和客户机建立了连接");
				
				
				//1.创建了一个线程去处理客户机的消息
				//客户机请求一次，服务器端就会创建一个新的线程，线程的开销太大。
				//new  Thread(new WorkRunn(socket)).start();
				
				//2.线程池 2.1 JDK线程池  2.2自定义线程池
				
				Threadpool.execute(new WorkRunn(socket));
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
    public static void main( String[] args )
    {
       new  AppServer();
    }
}
