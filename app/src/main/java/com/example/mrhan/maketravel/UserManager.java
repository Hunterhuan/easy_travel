package com.example.mrhan.maketravel;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.FactoryConfigurationError;

public class UserManager {
    private static String urlString = "http://118.25.77.165:8080/user";
    private static Integer instances = 0;
    private Boolean valid;
    private String username;
    private String password;
    private String email;


    public UserManager() throws MultiUserException {
        if(instances==0){
            valid = true;
            instances += 1;
        }
        else{
            valid = false;
            throw new MultiUserException("Trying to create more than one user!");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        instances -= 1;
        System.out.println("finalize");
        super.finalize();
    }

    public Boolean login(String em, String pw) {
        if(!valid){
            return false;
        }
        String login_url = urlString + "/login";
        JSONObject login_info = new JSONObject();
        try {
            login_info.put("email", em);
            login_info.put("password", pw);
            JSONObject result = HttpPost(login_url, login_info.toString());
            if(result.getBoolean("Status")) {
                email = em;
                password = pw;
                username = result.getString("username");
                return true;
            }
            else{
                return false;
            }
        } catch (Exception ex){
            return false;
        }
    }

    public Boolean logout() {
        if(!valid){
            return false;
        }
        String logout_url = urlString + "/logout";
        try {
            JSONObject result = HttpGet(logout_url);
            if(result.getBoolean("Status")) {
                email = null;
                password = null;
                username = null;
                return true;
            }
            else{
                return false;
            }
        } catch (Exception ex){
            return false;
        }
    }

    public Boolean register(String em, String pw, String username) {
        if(!valid){
            return false;
        }
        String register_url = urlString + "/register";
        JSONObject register_info = new JSONObject();
        try {
            register_info.put("email", em);
            register_info.put("password", pw);
            register_info.put("username", username);
            JSONObject result = HttpPost(register_url, register_info.toString());
            if(result.getBoolean("Status")) {
                return true;
            }
            else{
                return false;
            }
        } catch (Exception ex){
            return false;
        }
    }

    private JSONObject HttpPost(String urlStr,String Json){
        AsyncTask<String,Integer,JSONObject> task = new AsyncTask<String, Integer, JSONObject>() {
            @Override
            protected JSONObject doInBackground(String... strings) {
                return InnerHttpPost(strings[0],strings[1]);
            }
        };
        task.execute(urlStr,Json);
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

    private JSONObject InnerHttpPost(String urlStr, String Json){
        try {
            // 创建url资源
            URL url = new URL(urlStr);

            //System.out.println(url.toString());

            // 建立http连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 设置传递方式
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            conn.setRequestProperty("accept","application/json");


            if (Json != null) {
                // 设置文件长度
//                conn.setRequestProperty("Content-Length", String.valueOf(Json.length()));

                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                System.out.println(Json);
                out.writeBytes(Json);
                out.flush();
                out.close();
            }
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
            }

            conn.disconnect();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new JSONObject();
    }

    private JSONObject HttpGet(String urlStr){
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

    private JSONObject InnerHttpGet(String urlStr){
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
            //System.out.println(e.toString());
        }
        return new JSONObject();
    }

    public class MultiUserException extends RuntimeException {
        public MultiUserException(String message){
            super(message);
        }
    }
}
