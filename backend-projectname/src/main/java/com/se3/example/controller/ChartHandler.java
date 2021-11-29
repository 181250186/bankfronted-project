package com.se3.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.se3.example.Repository.ChartRepository;
import com.se3.example.entity.Chart;
import com.se3.example.entity.Edge;
import com.se3.example.entity.Node;
import com.se3.example.result.ResponseVO;
import com.se3.example.service.ChartService;
import com.se3.example.service.EdgeService;
import com.se3.example.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins={"http://106.14.72.92:8002","http://106.14.72.92:8001","http://106.14.72.92","http://localhost:8082","http://localhost:8083","http://localhost"}
, allowCredentials = "true"
)
@RequestMapping("/api/chart")
public class ChartHandler {
    @Autowired
    ChartService chartService;

    @PostMapping("/save")
    public ResponseVO saveChartAPI(@RequestBody JSONObject jsonObject){
        Chart chart=new Chart();
        chart.setName(jsonObject.get("name").toString());
        chartService.saveChart(chart);
        return ResponseVO.buildSuccess(chart);
    }

    @GetMapping("/findChart/{chartId}")
    public ResponseVO findChart(@PathVariable("chartId") Integer chartId){
//        System.out.println("here handler");
//        System.out.println(chartId);

        return ResponseVO.buildSuccess(chartService.findChart(chartId));
    }

    @GetMapping("/withdraw/{nodeId}/{chartId}")
    public ResponseVO withdraw(@PathVariable("chartId")Integer chartId,@PathVariable("nodeId") Integer nodeId){
//        System.out.println(chartId);
//        System.out.println(nodeId);
        //nodeService.delNode(nodeId,chartId);
        return ResponseVO.buildSuccess();
    }
}
