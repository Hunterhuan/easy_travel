package com.example.mrhan.maketravel;

/**
 * Created by Mr.Han on 2018/5/23.
 */
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class TravelDB {
    private static String urlString = "http://118.25.77.165:8080/api";
    //    private static String urlString = "http://192.168.43.92:8000/api";
    private String CityID;
    private JSONObject cachedData;
    private HttpUtil httpUtil;

    public TravelDB(String cityID){
        CityID = cityID;
        cachedData = new JSONObject();
        httpUtil = new HttpUtil();
        try {
            cachedData.put("SceneInfoList", new JSONObject());
            cachedData.put("DistanceList", new JSONObject());
            cachedData.put("HotelInfoList", new JSONObject());
        } catch (Exception e){
            System.out.println("Init Error!");
        }
    }

    public List<String> getAllScene(){
        JSONArray sceneList;
        try{
            sceneList = cachedData.getJSONArray("SceneList");
        } catch (JSONException e){
            JSONObject response = httpUtil.HttpGet(urlString + "?api=getSceneList&CityID=" + CityID);
            try {
                sceneList = response.getJSONArray("SceneList");
                cachedData.put("SceneList", sceneList);
            } catch (JSONException ex) {
                sceneList = new JSONArray();
            }
        }
        ArrayList<String> sL = new ArrayList<String>();
        try {
            for (int i = 0; i < sceneList.length(); i++) {
                sL.add(sceneList.get(i).toString());
            }
        } catch (JSONException ex){
            ;
        }
        return sL;
    }
    public List<String> getSceneInfo(String sceneId){
        JSONArray sceneInfo;
        try{
            sceneInfo = cachedData.getJSONObject("SceneInfoList").getJSONArray(sceneId);
        } catch (JSONException e){
            JSONObject response = httpUtil.HttpGet(urlString+"?api=getSceneInfo&SceneID="+sceneId);
            try {
                sceneInfo = response.getJSONArray("SceneInfo");
                cachedData.getJSONObject("SceneInfoList").put(sceneId, sceneInfo);
            } catch (JSONException ex) {
                sceneInfo = new JSONArray();
                ;
            }
        }
        ArrayList<String> sI = new ArrayList<>();
        try {
            for (int i = 0; i < sceneInfo.length(); i++) {
                sI.add(sceneInfo.get(i).toString());
            }
        } catch (JSONException ex){
            ;
        }
        return sI;
    }
    public String getType(String sceneId){
        List<String> sceneInfo = getSceneInfo(sceneId);
        String type;
        try {
            type = sceneInfo.get(11);
        } catch (Exception ex){
            type = "";
            ;
        }
        return type;
    }
    public Double getDistance(String placeFrom, String placeTo, String method){
        JSONArray distanceInfo;
        if(placeFrom.equals(placeTo)) return 0.0;
        try{
            distanceInfo = cachedData.getJSONObject("DistanceList").getJSONArray(placeFrom+","+placeTo);
        } catch (JSONException e){
            JSONObject response = httpUtil.HttpGet(urlString+"?api=getDistance&PlaceFrom="+placeFrom+"&PlaceTo="+placeTo);
            try {
                distanceInfo = response.getJSONArray("Distance");
                cachedData.getJSONObject("DistanceList").put(placeFrom+","+placeTo, distanceInfo);
            } catch (JSONException ex) {
                distanceInfo = new JSONArray();
                ;
            }
        }
        Double dist = -1.;
        try {
            if(method.equals("taxi")){
                dist = distanceInfo.getDouble(3) * 3;
            }
            else if(method.equals("bus")){
                dist = distanceInfo.getDouble(5) * 2;
            }
            if(dist < 1.){
                dist = 1.;
            }
        } catch (JSONException ex){
            ex.printStackTrace();
        }

        return dist;
    }
    public Double getTransportPrice(String placeFrom, String placeTo, String method){
        Double distance = getDistance(placeFrom,placeTo,method);
        JSONArray distanceInfo;
        try {
            distanceInfo = cachedData.getJSONObject("DistanceList").getJSONArray(placeFrom + "," + placeTo);
        }catch (JSONException e){
            distanceInfo = new JSONArray();
        }
        Double price = 0.;
        try {
            if(method.equals("taxi")){
                price = distanceInfo.getDouble(2);
            }
            else if(method.equals("bus")){
                price = distanceInfo.getDouble(4);
            }
        } catch (JSONException ex){
            ;
        }
        return price;
    }
    public Double getPop(String sceneId){
        List<String> sceneInfo = getSceneInfo(sceneId);
        Double pop = -5.;
        try {
            pop = Double.parseDouble(sceneInfo.get(6));
        } catch (Exception ex){
            ;
        }
        return pop/5;
    }
    public String getIntro(String sceneId){
        List<String> sceneInfo = getSceneInfo(sceneId);
        String intro = "null";
        try {
            intro = sceneInfo.get(10);
        } catch (Exception ex){
            ;
        }
        return intro;
    }
    public String getImage(String ID){
        List<String> sceneList = getAllScene();
        List<String> hotelList = getAllHotel();
        List<String> Info;
        Integer index;
        if(sceneList.contains(ID)){
            Info = getSceneInfo(ID);
            index = 14;
        }
        else if(hotelList.contains(ID)){
            Info = getHotelInfo(ID);
            index = 12;
        }
        else{
            return "null";
        }
        String imageURL = "null";
        try {
            imageURL = Info.get(index);
        } catch (Exception ex){
            ;
        }
        return imageURL;
    }
    public String getAddr(String ID){
        List<String> sceneList = getAllScene();
        List<String> hotelList = getAllHotel();
        List<String> Info;
        if(sceneList.contains(ID)){
            Info = getSceneInfo(ID);
        }
        else if(hotelList.contains(ID)){
            Info = getHotelInfo(ID);
        }
        else{
            return "null";
        }
        String addr = "null";
        try {
            addr = Info.get(1);
        } catch (Exception ex){
            ;
        }
        return addr;
    }

    public List<String> getAllHotel(){
        JSONArray hotelList;
        try{
            hotelList = cachedData.getJSONArray("HotelList");
        } catch (JSONException e){
            JSONObject response = httpUtil.HttpGet(urlString + "?api=getHotelList&CityID=" + CityID);
            try {
                hotelList = response.getJSONArray("HotelList");
                cachedData.put("HotelList", hotelList);
            } catch (JSONException ex) {
                hotelList = new JSONArray();
                ;
            }
        }
        ArrayList<String> hL = new ArrayList<String>();
        try {
            for (int i = 0; i < hotelList.length(); i++) {
                hL.add(hotelList.get(i).toString());
            }
        } catch (JSONException ex){
            ;
        }
        return hL;
    }
    public List<String> getHotelInfo(String hotelId){
        JSONArray hotelInfo;
        try{
            hotelInfo = cachedData.getJSONObject("HotelInfoList").getJSONArray(hotelId);
        } catch (JSONException e){
            JSONObject response = httpUtil.HttpGet(urlString+"?api=getHotelInfo&HotelID="+hotelId);
            try {
                hotelInfo = response.getJSONArray("HotelInfo");
                cachedData.getJSONObject("HotelInfoList").put(hotelId, hotelInfo);
            } catch (JSONException ex) {
                hotelInfo = new JSONArray();
                ;
            }
        }
        ArrayList<String> hI = new ArrayList<>();
        try {
            for (int i = 0; i < hotelInfo.length(); i++) {
                hI.add(hotelInfo.get(i).toString());
            }
        } catch (JSONException ex){
            ;
        }
        return hI;
    }
    public Double getPrice(String ID){

        List<String> sceneList = getAllScene();
        List<String> Info;
        if(sceneList.contains(ID)){
            Info = getSceneInfo(ID);
        }
        else{
            return -1.;
        }
        Double price = -1.;
        try {
            price = Double.parseDouble(Info.get(8));
        } catch (Exception ex){
            ;
        }
        return price;
    }
    public Double getHotelPrice(String ID){

        List<String> hotelList = getAllHotel();
        List<String> Info;
        if(hotelList.contains(ID)){
            Info = getHotelInfo(ID);
        }
        else{
            return -1.;
        }
        Double price = -1.;
        try {
            price = Double.parseDouble(Info.get(8));
        } catch (Exception ex){
            ;
        }
        return price;
    }
    public Double getVisitTime(String sceneId){
        List<String> sceneInfo = getSceneInfo(sceneId);
        Double time = -1.0;
        try {
            time = Double.parseDouble(sceneInfo.get(9));
        } catch (Exception ex){
            ;
        }
        return time*60;
    }
    public Boolean setVisitTime(String sceneId,Integer time){
        try {
            JSONArray sceneInfo = cachedData.getJSONObject("SceneInfoList").getJSONArray(sceneId);
            sceneInfo.put(9,time/60);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

}