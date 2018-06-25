package com.example.mrhan.maketravel;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpUtil {

//    private CookieManager cookieManager;
    private HttpCookie cookie;

    public HttpUtil(){
//        cookieManager = new CookieManager();
//        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        cookie = new HttpCookie("Cookie","");
    }

    /**
     * Function  :   封装请求体信息
     * Param     :   params请求体内容，encode编码格式
     **/
    public StringBuffer getRequestData(Map<String, String> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
        try {
            for(Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), encode))
                        .append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个"&"
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }

    public JSONObject HttpPost(String urlStr, String data){
        AsyncTask<String,Integer,JSONObject> task = new AsyncTask<String, Integer, JSONObject>() {
            @Override
            protected JSONObject doInBackground(String... strings) {
                return InnerHttpPost(strings[0],strings[1]);
            }
        };
        task.execute(urlStr,data);
        JSONObject result;
        try {
            result = task.get();
        } catch (Exception ex){
            result = new JSONObject();
            try {
                result.put("Status", false);
            } catch (Exception e) {
                ;
            }
        }
        return  result;
    }

    private JSONObject InnerHttpPost(String urlStr, String data){
        try {
            // 创建url资源
            URL url = new URL(urlStr);

            System.out.println(url.toString());

            // 建立http连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 设置传递方式
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
//            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.setRequestProperty("accept","application/json");
            conn.setRequestProperty(cookie.getName(),cookie.getValue());
//            System.out.println(cookie.getName());

            if (data != null) {
                // 设置文件长度
//                conn.setRequestProperty("Content-Length", String.valueOf(Json.length()));

                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                System.out.println(data);
                out.writeBytes(data);
                out.flush();
                out.close();
            }
            // 请求返回的状态
            if (conn.getResponseCode() == 200) {
                // 请求返回的数据
                try {
                    InputStreamReader in = new InputStreamReader(conn.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(in);
                    StringBuffer stringBuffer = new StringBuffer();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    String result = stringBuffer.toString();
                    if(conn.getHeaderField("Set-Cookie") != null) {
                        cookie.setValue(conn.getHeaderField("Set-Cookie"));
                    }
                    return new JSONObject(result);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            conn.disconnect();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new JSONObject();
    }

    public JSONObject HttpGet(String urlStr){
        AsyncTask<String,Integer,JSONObject> task = new AsyncTask<String, Integer, JSONObject>() {
            @Override
            protected JSONObject doInBackground(String... strings) {
                return InnerHttpGet(strings[0]);
            }
        };
        task.execute(urlStr);
        JSONObject result;
        try {
            result = task.get();
        } catch (Exception ex){
            result = new JSONObject();
        }
        return  result;
    }

    private static JSONObject InnerHttpGet(String urlStr){
        try {
            // 创建url资源
            URL url = new URL(urlStr);

            System.out.println(url.toString());

            // 建立http连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 设置传递方式
            conn.setRequestMethod("GET");

            // 开始连接请求
            conn.connect();

            //System.out.println(conn.getResponseCode());

            // 请求返回的状态
            if (conn.getResponseCode() == 200) {
                // 请求返回的数据
                try {
                    InputStreamReader in = new InputStreamReader(conn.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(in);
                    StringBuffer stringBuffer = new StringBuffer();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    String result = stringBuffer.toString();
                    return new JSONObject(result);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    ;
                }
            } else {
                ;
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new JSONObject();
    }
}
