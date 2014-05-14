package org.cerberus.testbed.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class ResultProfilingServlet extends HttpServlet {

	private static final Logger logger = Logger.getLogger(ResultProfilingServlet.class);
	
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		String data = request.getParameter("data");
				
		logger.info(data);
		
//		String url = "http://172.16.101.193:3000/api/v1/detail_reports";
//		HttpClient client = new DefaultHttpClient();
//		ArrayList<NameValuePair> nameValuePairs = 
//				new ArrayList<NameValuePair>();
//		nameValuePairs.add(new BasicNameValuePair("detail_report", data));
//
//
//		HttpPost httpPost = new HttpPost(url);
//		UrlEncodedFormEntity entityRequest = 
//				new UrlEncodedFormEntity(nameValuePairs, "EUC-KR");
//		
//		httpPost.setEntity(entityRequest);
//		
//		HttpResponse responsePost = client.execute(httpPost);
//		HttpEntity resEntity = responsePost.getEntity();
//		System.out.println(EntityUtils.toString(resEntity)); 
		
		System.out.println("finish...");
	
	
		
		
		
	}

	
	
}
