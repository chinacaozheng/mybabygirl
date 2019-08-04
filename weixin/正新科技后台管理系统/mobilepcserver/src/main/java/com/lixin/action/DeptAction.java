package com.lixin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.lixin.model.Depts;

public class DeptAction extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		/* 允许跨域的主机地址 */
		resp.setHeader("Access-Control-Allow-Origin", "*");
		/* 允许跨域的请求方法GET, POST, HEAD 等 */
		resp.setHeader("Access-Control-Allow-Methods", "*");
		/* 重新预检验跨域的缓存时间 (s) */
		resp.setHeader("Access-Control-Max-Age", "3600");
		/* 允许跨域的请求头 */
		resp.setHeader("Access-Control-Allow-Headers", "*");
		/* 是否携带cookie */
		// resp.setHeader("Access-Control-Allow-Credentials", "true

		System.out.println("DeptAction  is    doPost  start...");

		// 怎么判断是PC浏览器的请求，还是移动的请求
		String headsInfo = req.getHeader("user-agent");
		System.out.println("headsInfo-->" + headsInfo);

		List<Depts> lists = new ArrayList<Depts>();
		Depts d1 = new Depts();
		d1.setDid(100);
		d1.setDname("质量部");
		d1.setDphone("8888");
		d1.setDlocation("南京");

		Depts d2 = new Depts();
		d2.setDid(101);
		d2.setDname("保卫部");
		d2.setDphone("9999");
		d2.setDlocation("北京");

		lists.add(d1);
		lists.add(d2);

		if (headsInfo.toLowerCase().contains("android") || headsInfo.toLowerCase().contains("iphone")) {
			System.out.println("手机端的请求");
			
			String  strs=JSON.toJSONString(lists);
			System.out.println(strs);
			
			PrintWriter  pw=resp.getWriter();
			pw.println(strs);
			pw.flush();
			pw.close();
			
			

		} else {
			System.out.println("pc浏览器的请求");

			req.setAttribute("datas", lists);

			req.getRequestDispatcher("main.jsp").forward(req, resp);
		}

	}

}
