package org.cerberus.testbed.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ImageUploadServlet extends HttpServlet {
       
	private static final String SERVER_URL = "http://172.16.101.193:3000/api/v1/detail_reports/upload_screenshot";
	
	private static final Logger logger = Logger.getLogger(ImageUploadServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	
	public void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int postMaxSize = 10 * 1024 * 1024;
		String folderPath = getServletContext().getRealPath("/") + "/image/" ;
		String encording = "UTF-8";
		
		new File(folderPath).mkdirs();
		
		MultipartRequest mRequest = new MultipartRequest(request, folderPath, postMaxSize, encording, new DefaultFileRenamePolicy());
		
		logger.info("ImageUpload called...");
		
		String timeStamp = mRequest.getParameter("timestamp");
		String deviceKey = mRequest.getParameter("deviceKey");
		
		logger.debug("timestamp : " + timeStamp);
		logger.debug("device key : " + deviceKey);
		
		folderPath = getServletContext().getRealPath("/") + "/image/" + deviceKey;
		
		new File(folderPath).mkdirs();
		
		byte[] buffer = new byte[1024];
		
		File file = mRequest.getFile("file");
		
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(new File(folderPath + "/" + timeStamp + ".jpg"));

		while(fis.read(buffer) != -1 ) {

			fos.write(buffer);
			
		}

		fis.close();
		fos.close();
		
		
//		HttpClient httpClient = new DefaultHttpClient();
//		HttpPost httpPost = new HttpPost(SERVER_URL);
//		
//		MultipartEntity requestEntity = new MultipartEntity();
//		
//		requestEntity.addPart("file",new FileBody(file));
//		requestEntity.addPart("timestamp", new StringBody(timeStamp));
//		
//		httpPost.setEntity(requestEntity);
//		HttpResponse response2 = httpClient.execute(httpPost);
		
	}
	
}
