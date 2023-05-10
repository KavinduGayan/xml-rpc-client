package org.example;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        String xmlRpcUrl = "http://localhost:8088"; // Replace with the URL of your XML-RPC server
        String xmlRpcRequest = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
                "<methodCall>\n" +
                "<methodName>sample.sum</methodName>\n" +
                "<params>\n" +
                "<param>\n" +
                "<value><int>17</int></value>\n" +
                "</param>\n" +
                "<param>\n" +
                "<value><int>13</int></value>\n" +
                "</param>\n" +
                "</params>\n" +
                "</methodCall>";

        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpPost httpPost = new HttpPost(xmlRpcUrl);
            httpPost.setHeader("Content-type", "text/xml");
            httpPost.setEntity(new StringEntity(xmlRpcRequest));

            HttpEntity httpEntity = httpClient.execute(httpPost).getEntity();

            String xmlRpcResponse = EntityUtils.toString(httpEntity);

            // Process the XML-RPC response here
            System.out.println(xmlRpcResponse);

        } finally {
            httpClient.close();
        }
    }
}
