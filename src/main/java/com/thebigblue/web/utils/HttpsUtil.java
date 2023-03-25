package com.thebigblue.web.utils;


import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description(描述) : Https工具类
 */
public class HttpsUtil {

    private static final CloseableHttpClient HTTP_CLIENT;
    private static final String CHARSET = "UTF-8";
    private static final String HTTP = "http";
    private static final String HTTPS = "https";

    //采用静态代码块，初始化超时时间配置，再根据配置生成默认httpClient对象
    static {
        //采用绕过验证的方式处理https请求
        SSLContext sslcontext = createIgnoreVerifySSL();
        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register(HTTP, PlainConnectionSocketFactory.INSTANCE)
                .register(HTTPS, new SSLConnectionSocketFactory(sslcontext, (String s, SSLSession sslSession) -> true))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        // 延迟300ms中断连接
        RequestConfig config = RequestConfig.custom().setConnectTimeout(300).setSocketTimeout(300).build();
        HTTP_CLIENT = HttpClients.custom().setDefaultRequestConfig(config).setConnectionManager(connManager).build();
    }

    /**
     * @Description: https发送get请求，kv格式
     */
    public static String httpsGet(String url, String param) throws IOException {
        if (!IsNullUtil.isEmpty(param)) {
            url = url + "?" + param;
        }
        //创建get方式请求对象
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = HTTP_CLIENT.execute(httpGet);
        return EntityUtils.toString(response.getEntity());
    }

    /**
     * @Description: https发送post请求，json格式
     */
    public static String httpsPostJson(String url, String jsonStr) throws IOException {
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        //设置参数到请求对象中
        StringEntity stringEntity = new StringEntity(jsonStr, CHARSET);
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        return execute(httpPost);
    }

    /**
     * @Description: https发送post请求，kv格式
     */
    public static String httpsPost(String url, Map<String, String> map) throws IOException {
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        //装填参数
        List<NameValuePair> nvps = new ArrayList<>();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        //设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, CHARSET));

        //设置header信息
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        return execute(httpPost);
    }

    /**
     * @Description: 发送请求
     */
    public static String execute(HttpPost httpPost) throws IOException {
        String body = "";
        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = HTTP_CLIENT.execute(httpPost);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, CHARSET);
        }
        //获取结果实体
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }

    /**
     * @Description: 绕过验证
     */
    public static SSLContext createIgnoreVerifySSL() {
        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{trustManager}, null);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return sc;
    }

    /**
     * @Description: https发送post请求，kv格式 设置文件上传的请求头
     */
    public static String httpsPosts(String url, Map<String, String> map) throws IOException {
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        //装填参数
        List<NameValuePair> nvps = new ArrayList<>();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        //设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, CHARSET));

        //设置header信息
        httpPost.setHeader("Content-type", "application/form-data");
        return execute(httpPost);
    }
}