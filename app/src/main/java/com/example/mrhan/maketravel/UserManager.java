package com.example.mrhan.maketravel;



import org.json.JSONArray;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;


public class UserManager {
    private static String urlString = "http://118.25.77.165:8080/user";
    private static Integer instances = 0;
    private Boolean valid;
    private Boolean online;
    private String username;
    private String email;
    private HttpUtil httpUtil;


    public UserManager() throws MultiUserException {
        if(instances==0){
            valid = true;
            online = true;
            httpUtil = new HttpUtil();
            instances += 1;
        }
        else{
            valid = false;
            online = false;
            throw new MultiUserException("Trying to create more than one user!");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        instances -= 1;
        super.finalize();
    }

    public Boolean isvalid(){
        return valid;
    }

    public String getUsername(){
        return username;
    }

    public String getEmail(){
        return email;
    }

    public Boolean getLoginStatus(){
        return online;
    }

    public Boolean login(String em, String pw) {
        if(!valid){
            return false;
        }
        String login_url = urlString + "/login";
        Map<String,String> login_info = new HashMap<>();
        try {
            login_info.put("email", em);
            login_info.put("password", pw);
            String data = httpUtil.getRequestData(login_info,"utf-8").toString();
            JSONObject result = httpUtil.HttpPost(login_url,data);
            if(result.getBoolean("Status")) {
                email = em;
                username = result.getString("username");
                online = true;
                return true;
            }
            else{
                return false;
            }
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean logout() {
        if(!valid){
            return false;
        }
        String logout_url = urlString + "/logout";
        try {
            JSONObject result = httpUtil.HttpGet(logout_url);
            if(result.getBoolean("Status")) {
                email = null;
                username = null;
                online = false;
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
            JSONObject result = httpUtil.HttpPost(register_url, register_info.toString());
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

    public Boolean changePassword(String pw) {
        if(!valid){
            return false;
        }
        String user_info_url = urlString + "/info";
        Map<String,String> user_info = new HashMap<>();
        try {
            user_info.put("api", "changePassword");
            user_info.put("password", pw);
            String data = httpUtil.getRequestData(user_info,"utf-8").toString();
            JSONObject result = httpUtil.HttpPost(user_info_url,data);
            if(result.getBoolean("Status")) {
                online = false;
                return true;
            }
            else{
                return false;
            }
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean saveRoute(ArrayList<ArrayList<String>> route) {
        if(!valid){
            return false;
        }
        JSONArray route_array = new JSONArray(route);
        String user_info_url = urlString + "/info";
        Map<String,String> user_info = new HashMap<>();
        try {
            user_info.put("api", "saveRoute");
            user_info.put("route", route_array.toString());
            user_info.put("route_id",new Date().getTime()+username);
            String data = httpUtil.getRequestData(user_info,"utf-8").toString();
            JSONObject result = httpUtil.HttpPost(user_info_url,data);
            if(result.getBoolean("Status")) {
                return true;
            }
            else{
                return false;
            }
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean deleteRoute(String routeId) {
        if(!valid){
            return false;
        }
        String user_info_url = urlString + "/info";
        Map<String,String> user_info = new HashMap<>();
        try {
            user_info.put("api", "deleteRoute");
            user_info.put("route_id",routeId);
            String data = httpUtil.getRequestData(user_info,"utf-8").toString();
            JSONObject result = httpUtil.HttpPost(user_info_url,data);
            if(result.getBoolean("Status")) {
                return true;
            }
            else{
                return false;
            }
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Route> getRouteList() {
        if(!valid){
            return new ArrayList<>();
        }
        String user_info_url = urlString + "/info";
        Map<String,String> user_info = new HashMap<>();
        try {
            user_info.put("api", "getRouteList");
            String data = httpUtil.getRequestData(user_info,"utf-8").toString();
            JSONObject result = httpUtil.HttpPost(user_info_url,data);
            if(result.getBoolean("Status")) {
                ArrayList<Route> routeList = new ArrayList<>();
                JSONArray routeJson = result.getJSONArray("RouteList");
                for(int i = 0; i < routeJson.length(); i++){
                    JSONArray route_jsonarray = routeJson.getJSONArray(i);
                    String route_id = route_jsonarray.getString(0);
                    ArrayList<String> route = new ArrayList<>();
                    for(int j = 0; j < route_jsonarray.getJSONArray(2).length(); j++){
                        route.add(route_jsonarray.getJSONArray(2).getString(j));
                    }
                    ArrayList<String> description = new ArrayList<>();
                    for(int j = 0; j < route_jsonarray.getJSONArray(1).length(); j++){
                        description.add(route_jsonarray.getJSONArray(1).getString(j));
                    }
                    Route route_obj = new Route(route_id,route,description);
                    routeList.add(route_obj);
                }
                return routeList;
            }
            else{
                return new ArrayList<>();
            }
        } catch (Exception ex){
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }


    /**
     * 使用N、e值还原公钥
     *
     * @param modulus
     * @param publicExponent
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static PublicKey getPublicKey(String modulus, String publicExponent)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        BigInteger bigIntModulus = new BigInteger(modulus);
        BigInteger bigIntPrivateExponent = new BigInteger(publicExponent);
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 用公钥加密 <br>
     * 每次加密的字节数，不能超过密钥的长度值减去11
     *
     * @param data
     *            需加密数据的byte数据
     * @param publicKey 公钥
     * @return 加密后的byte型数据
     */
    private static byte[] encryptData(byte[] data, PublicKey publicKey)
    {

        try
        {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            // 编码前设定编码方式及密钥
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            // 传入编码数据并返回编码结果
            int inputLen = data.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > 117) {
                    cache = cipher.doFinal(data, offSet, 117);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * 117;
            }
            byte[] encryptedData = out.toByteArray();
            out.close();
            return encryptedData;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public class MultiUserException extends RuntimeException {
        public MultiUserException(String message){
            super(message);
        }
    }
}
