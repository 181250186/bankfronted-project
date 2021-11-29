package com.se3.example.service.Impl;

import com.se3.example.Repository.NodeRepository;
import com.se3.example.entity.Node;
import com.se3.example.service.EdgeService;
import com.se3.example.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Service
public class NodeServiceImpl implements NodeService {
    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private EdgeService edgeService;

    @Override
    public void addNode(Node node){
//        System.out.println("come");
//        System.out.println(node);
        nodeRepository.save(node);
    }

    @Override
    public int getNodeNumber(Integer cid){
        return nodeRepository.findAllByCid(cid).size();
    }

    @Override
    public List<Node> findNodeOfChart(Integer cid){
       return nodeRepository.findAllByCid(cid);
    }

    @Override
    public void delNode(String name, Integer cid){
//        System.out.println("Node");
        Node node=nodeRepository.findNodeByNameAndCid(name,cid);
//       System.out.println(node);
        nodeRepository.deleteById(node.getNid());
//       System.out.println("node over");
        edgeService.delEdgeByNode(name,cid);

    }

    @Override
    public void update(Node node){
//        System.out.println("update");
    }
}
