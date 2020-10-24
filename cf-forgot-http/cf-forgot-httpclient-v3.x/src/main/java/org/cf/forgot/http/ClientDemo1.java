package org.cf.forgot.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2020/10/24
 * @since todo
 */
public class ClientDemo1 {
    private static Logger LOG = LoggerFactory.getLogger(ClientDemo1.class);

    public static void main(String[] args) {
        ClientDemo1 clientDemo = new ClientDemo1();
        String uri = "http://127.0.0.1:8989/common/demo";
        clientDemo.doPostRequestFromFile(uri);
        clientDemo.doPostRequestStringBody(uri);
        clientDemo.doPostRequestNameValuePairsBody(uri);

    }

    private void doPostRequestStringBody(final String uri) {
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(uri);

        postMethod.setRequestBody("Request body: postMethod.setRequestBody");

        try {
            client.executeMethod(postMethod);
            LOG.info("after execute method, responseBody: {}",
                    new String(postMethod.getResponseBody(), postMethod.getResponseCharSet()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }
        LOG.info("do task end, url: {}", uri);
    }

    private void doPostRequestNameValuePairsBody(final String uri) {
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(uri);

        NameValuePair nameValuePairName = new NameValuePair("name", "zhangsan");
        NameValuePair nameValuePairAge = new NameValuePair("age", "18");

        postMethod.setRequestBody(new NameValuePair[]{nameValuePairName, nameValuePairAge});

        RequestEntity requestEntity = postMethod.getRequestEntity();
        if (requestEntity instanceof ByteArrayRequestEntity) {
            byte[] bs = ((ByteArrayRequestEntity) requestEntity).getContent();
            try {
                LOG.info("test capture entity: {}", new String(bs, postMethod.getRequestCharSet()));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            client.executeMethod(postMethod);
            LOG.info("after execute method, responseBody: {}",
                    new String(postMethod.getResponseBody(), postMethod.getResponseCharSet()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }
        LOG.info("do task end, url: {}", uri);
    }

    private void doPostRequestFromFile(String uri) {
        HttpClient client = new HttpClient();

        PostMethod postMethod1 = new PostMethod(uri);
        String uri2 = "http://127.0.0.1:8989/common/demo";
        PostMethod postMethod2 = new PostMethod(uri2);
        String path = this.getClass().getClassLoader().getResource("./").getPath();
        File file = new File(path + "requestfile1.txt");
        try {
            postMethod1.setRequestEntity(new InputStreamRequestEntity(new FileInputStream(file)));
            postMethod1.setContentChunked(true);

            postMethod2.setRequestEntity(postMethod1.getRequestEntity());
            //-- 1
            client.executeMethod(postMethod1);
            LOG.info("after execute method, responseBody: {}",
                    new String(postMethod1.getResponseBody(), postMethod1.getResponseCharSet()));
            //-- 2
            client.executeMethod(postMethod2);
            LOG.info("after execute method, responseBody: {}",
                    new String(postMethod2.getResponseBody(), postMethod2.getResponseCharSet()));

            RequestEntity requestEntity = postMethod1.getRequestEntity();
            if (requestEntity instanceof InputStreamRequestEntity) {
                InputStreamRequestEntity inputStreamRequestEntity = (InputStreamRequestEntity) requestEntity;
                InputStream inputStream = inputStreamRequestEntity.getContent();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                System.out.println("inputStreamRequestEntity.isRepeatable(): " + inputStreamRequestEntity.isRepeatable());
                inputStreamRequestEntity.writeRequest(bos);
                byte[] bs = bos.toByteArray();
                System.out.println("requestBody: " + new String(bs, postMethod1.getRequestCharSet()));
            }
            if (postMethod1.getStatusCode() == HttpStatus.SC_OK) {
                LOG.info("after execute method, responseBody: {}",
                        new String(postMethod1.getResponseBody(), postMethod1.getResponseCharSet()));
            } else {
                System.out.println("Unexpected failure: " + postMethod1.getStatusLine().toString());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            postMethod1.releaseConnection();
        }
        LOG.info("do task end, url: {}", uri);
    }

}
