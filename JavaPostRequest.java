package com.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;


public class JavaPostRequest {

	public static void main(String args) throws Exception {
		
		String[] data = args.split("__");
		
		String dataurl = data[0];
		String Data = data[1];
		
		System.out.println(postData(dataurl,Data));		

	}
	
	static String postData(String URL,String payload) {
		
		JavaPostRequest httpPostReq=new JavaPostRequest();
        HttpPost httpPost=httpPostReq.createConnectivity(URL);
        return httpPostReq.executeReq( payload, httpPost);
        
	}
	
	
	HttpPost createConnectivity(String restUrl)
    {
        HttpPost post = new HttpPost(restUrl);
        post.setHeader("Content-Type", "application/json");
            post.setHeader("Accept", "application/json");
            post.setHeader("X-Stream" , "true");
        return post;
    }
     
    String executeReq(String jsonData, HttpPost httpPost)
    {
        try{
           return executeHttpRequest(jsonData, httpPost);
        }
        catch (UnsupportedEncodingException e){
            System.out.println("error while encoding api url : "+e);
        }
        catch (IOException e){
            System.out.println("ioException occured while sending http request : "+e);
        }
        catch(Exception e){
            System.out.println("exception occured while sending http request : "+e);
        }
        finally{
             httpPost.releaseConnection();
        }
		return null;
    }
	
	String executeHttpRequest(String jsonData,  HttpPost httpPost)  throws UnsupportedEncodingException, IOException
	    {
	        HttpResponse response=null;
	        String line = "";
	        StringBuffer result = new StringBuffer();
	        httpPost.setEntity(new StringEntity(jsonData));
	        System.out.println("Post parameters : " + jsonData );
	        HttpClient client = HttpClientBuilder.create().build();
	        response = client.execute(httpPost);
	        System.out.println("Response Code : " +response.getStatusLine().getStatusCode());
	        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	        while ((line = reader.readLine()) != null){ result.append(line); }
	        System.out.println(result.toString());
	        return result.toString();
	    }
}
