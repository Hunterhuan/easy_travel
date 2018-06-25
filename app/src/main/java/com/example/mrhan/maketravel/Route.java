package com.example.mrhan.maketravel;

import java.util.ArrayList;
import java.util.Date;

public class Route {
    private ArrayList<String> route;
    private ArrayList<String> description;
    private String routeId;

    public Route(String id, ArrayList<String> rt, ArrayList<String> ds){
        Date date = new Date();
        routeId =  id;
        route = rt;
        description = ds;
    }

    public ArrayList<ArrayList<String>> getList(){
        ArrayList<ArrayList<String>> array = new ArrayList<>();
        array.add(description);
        array.add(route);
        return array;
    }

    public String getRouteId() {
        return routeId;
    }
}
