package com.se3.example.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.se3.example.Repository.ChartRepository;
import com.se3.example.Repository.EdgeRepository;
import com.se3.example.Repository.NodeRepository;
import com.se3.example.entity.Chart;
import com.se3.example.entity.Edge;
import com.se3.example.entity.Node;
import com.se3.example.service.ChartService;
import com.se3.example.service.EdgeService;
import com.se3.example.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ChartServiceImpl implements ChartService {
    @Autowired
    private ChartRepository chartRepository;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private EdgeService edgeService;

    @Override
    public void saveChart(Chart chart){
        chartRepository.save(chart);
    }

    @Override
    public ArrayList<HashMap<String, Object>> findChart(Integer chartId){
//        System.out.println("here function");
//        System.out.println(chartId);

        List<Node> nodes =nodeService.findNodeOfChart(chartId);
        List<Edge> edges=edgeService.findEdgeOfChart(chartId);
        ArrayList<HashMap<String, Object>> result =  new ArrayList<>();

        for(int i=0;i< nodes.size();i++){
            HashMap<String, Object> jsonObject=new HashMap<>();
            jsonObject.put("group","nodes");

            JSONObject data=new JSONObject();
            data.put("id",nodes.get(i).getNid());
            data.put("name",nodes.get(i).getName());
            jsonObject.put("data",data);

            jsonObject.put("classes",nodes.get(i).getClasses());

            JSONObject position=new JSONObject();
            position.put("x",nodes.get(i).getPositionX());
            position.put("y",nodes.get(i).getPositionY());
            jsonObject.put("position",position);

            //System.out.println(jsonObject);
            result.add(jsonObject);
        }

        for (int i=0;i<edges.size();i++){
            HashMap<String, Object> jsonObject=new HashMap<>();
            jsonObject.put("group","edges");

            JSONObject data=new JSONObject();
            data.put("id",edges.get(i).getEid());
            data.put("name",edges.get(i).getName());
            data.put("source",edges.get(i).getSource());
            data.put("target",edges.get(i).getTarget());
            jsonObject.put("data",data);

            jsonObject.put("classes",edges.get(i).getClasses());

            //System.out.println(jsonObject);
            result.add(jsonObject);
        }
        //System.out.println(result);
        return result;
    }
}
