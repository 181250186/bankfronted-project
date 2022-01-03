package com.se3.example.service.Impl;

import com.se3.example.Repository.NodeRepository;
import com.se3.example.entity.Node;
import com.se3.example.service.NodeService;
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
class NodeServiceImplTest {
    @Autowired
    NodeService nodeService;
    @Autowired
    NodeRepository nodeRepository;

    @Test
    @Order(1)
    void addNode() {
        Node node=new Node();
        node.setCid(4);
        node.setName("test");
        node.setClasses("classes-A");
        node.setPositionX(0.0);
        node.setPositionY(0.0);
        nodeService.addNode(node);
        int test=nodeRepository.findByName("test").getNid();
        System.out.println(test);
        Assert.assertNotEquals(0,test);
    }

    @Test
    @Order(2)
    void getNodeNumber() {
        int test=nodeService.getNodeNumber(4);
        Assert.assertNotEquals(0,test);
    }

    @Test
    @Order(3)
    void findNodeOfChart() {
        List<Node> test=nodeService.findNodeOfChart(4);
        Assert.assertNotEquals(0,test.size());
    }

    @Test
    @Order(4)
    void delNode() {
        nodeService.delNode("test",4);
        Assert.assertEquals(null,nodeRepository.findByName("test"));
    }

}
