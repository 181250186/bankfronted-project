package com.se3.example.service.Impl;

import com.se3.example.Repository.EdgeRepository;
import com.se3.example.entity.Edge;
import com.se3.example.entity.Node;
import com.se3.example.service.EdgeService;
import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EdgeServiceImplTest {
    @Autowired
    EdgeService edgeService;
    @Autowired
    EdgeRepository edgeRepository;


    @Test
    @Order(1)
    void addRelation1() {
        Edge test=new Edge();
        test.setTarget("155");
        test.setSource("133");
        test.setClasses("RelationB");
        test.setName("小于");
        test.setCid(4);
        edgeService.addRelation(test);
        int edge=edgeRepository.findByName("小于").getEid();
        Assert.assertNotEquals(0,edge);
    }

    @Test
    @Order(2)
    void addRelation2() {
        Edge test=new Edge();
        test.setTarget("155");
        test.setSource("133");
        test.setClasses("RelationB");
        test.setName("小");
        test.setCid(4);
        edgeService.addRelation(test);
        int edge=edgeRepository.findByName("小").getEid();
        Assert.assertNotEquals(0,edge);
    }

    @Test
    @Order(3)
    void addRelation3() {
        Edge test=new Edge();
        test.setTarget("155");
        test.setSource("133");
        test.setClasses("RelationB");
        test.setName("不大");
        test.setCid(4);
        edgeService.addRelation(test);
        int edge=edgeRepository.findByName("不大").getEid();
        Assert.assertNotEquals(0,edge);
    }

    @Test
    @Order(4)
    void getEdgeNumber() {
        int test=edgeService.getEdgeNumber(4);
        Assert.assertNotEquals(0,test);
        edgeService.delEdgeByNode("155",4);
    }

    @Test
    @Order(5)
    void findEdgeOfChart() {
        List<Edge> test=edgeService.findEdgeOfChart(4);
        Assert.assertNotEquals(0,test.size());
    }

    @Test
    @Order(6)
    void delEdge() {
        edgeService.delEdge(edgeRepository.findByName("小于").getEid());
        Assert.assertEquals(null,edgeRepository.findByName("test"));
    }

    @Test
    @Order(9)
    void delEdgeByNode() {
        edgeService.delEdgeByNode("155",4);
        //System.out.println(edgeRepository.findAllBySourceAndCid("155",4));
        Assert.assertEquals(0,edgeRepository.findAllBySourceAndCid("155",4).size());
        Assert.assertEquals(0,edgeRepository.findAllByTargetAndCid("155",4).size());
    }
}