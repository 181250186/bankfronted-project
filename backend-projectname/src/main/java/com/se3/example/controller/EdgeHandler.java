package com.se3.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.se3.example.Repository.EdgeRepository;
import com.se3.example.entity.Edge;
import com.se3.example.result.ResponseVO;
import com.se3.example.service.EdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins={"http://106.14.72.92:8002","http://106.14.72.92:8001","http://106.14.72.92","http://localhost:8082","http://localhost:8083","http://localhost"}
, allowCredentials = "true")
@RequestMapping("/api/edge")
public class EdgeHandler {
    @Autowired
     private  EdgeService edgeService;

    @PostMapping("/add")
    public ResponseVO saveRelationAPI(@RequestBody JSONObject jsonObject){
        System.out.println(jsonObject);
        Edge edge=new Edge();
        JSONObject data=(JSONObject) JSONObject.toJSON(jsonObject.get("data"));

        edge.setClasses(jsonObject.get("classes").toString());
        edge.setName(data.get("name").toString());
        edge.setSource(data.get("source").toString());
        edge.setTarget(data.get("target").toString());
        edge.setCid((Integer)jsonObject.get("chartId"));
        edgeService.addRelation(edge);
        return ResponseVO.buildSuccess(edge);
    }

    @GetMapping("/getEdgeNumber/{cid}")
    public ResponseVO getEdgeNumber(@PathVariable("cid") Integer cid){
        //System.out.println(cid);
        return ResponseVO.buildSuccess(edgeService.getEdgeNumber(cid));
    }//前端多给了个数据

    @PostMapping("/delete/{chartId}/{edge_id}")
    public ResponseVO delEdge(@PathVariable("chartId") Integer chartId,@PathVariable("edge_id") Integer edge_Id){
        System.out.println("here "+edge_Id);
        edgeService.delEdge(edge_Id);
        return ResponseVO.buildSuccess();
    }

    @PostMapping("/update")
    public ResponseVO update(@RequestBody JSONObject jsonObject){
      System.out.println(jsonObject);
      Edge edge=new Edge();
      JSONObject data=(JSONObject) JSONObject.toJSON(jsonObject.get("data"));

      edge.setClasses(jsonObject.get("classes").toString());
      edge.setName(data.get("name").toString());
      edge.setSource(data.get("source").toString());
      edge.setTarget(data.get("target").toString());
      edge.setCid((Integer)jsonObject.get("chartId"));
      edgeService.updateEdge(edge);
      return ResponseVO.buildSuccess(edge);
    }
}
