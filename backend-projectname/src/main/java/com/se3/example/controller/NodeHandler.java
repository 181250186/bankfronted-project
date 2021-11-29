package com.se3.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.se3.example.Repository.NodeRepository;
import com.se3.example.result.Result;
import com.se3.example.service.Impl.NodeServiceImpl;
import com.se3.example.service.NodeService;
import org.hibernate.mapping.Map;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.se3.example.entity.Node;
import com.se3.example.result.ResponseVO;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins={"http://106.14.72.92:8002","http://106.14.72.92:8001","http://106.14.72.92","http://localhost:8082","http://localhost:8083","http://localhost"},
allowCredentials = "true")
@RequestMapping("/api/node")
public class NodeHandler {
    @Autowired
    NodeService nodeService;

    @PostMapping("/add")
    public ResponseVO addNodeAPI(@RequestBody JSONObject jsonObject){
        System.out.println("add");
        System.out.println(jsonObject);
        Node node=new Node();
        JSONObject data = (JSONObject) JSONObject.toJSON(jsonObject.get("data"));
        JSONObject postion=(JSONObject) JSONObject.toJSON(jsonObject.get("position"));

        node.setClasses(jsonObject.get("classes").toString());
        String name = data.get("name").toString();
        node.setName(name);
        node.setPositionX((Integer) postion.get("x"));
        node.setPositionY((Integer)postion.get("y"));
        node.setCid((Integer)jsonObject.get("chartId"));
        nodeService.addNode(node);

//        System.out.println("测试");
//        System.out.println(id);
//        System.out.println(jsonObject);

        return ResponseVO.buildSuccess(node);
    }

    @GetMapping("/getNumberNode/{cid}")
    public ResponseVO getNodeNumber(@PathVariable("cid") Integer cid){
        //System.out.println(cid);
        return ResponseVO.buildSuccess(nodeService.getNodeNumber(cid));
    }

    @GetMapping("/delete/{chartId}/{node_id}")
    public ResponseVO delNode(@PathVariable("chartId") String chartId,@PathVariable("node_id") Integer node_Id){
//        System.out.println("del");
//        System.out.println(node_Id);
//        System.out.println(chartId);
//        System.out.println("del");
        nodeService.delNode(chartId,node_Id);
        return ResponseVO.buildSuccess();
    }

    @PostMapping("/update")
    public ResponseVO updateNode(@RequestBody JSONObject jsonObject){
        return ResponseVO.buildSuccess();
    }
}
