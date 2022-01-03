package com.se3.example.service.Impl;

import com.se3.example.Repository.EdgeRepository;
import com.se3.example.entity.Edge;
import com.se3.example.service.EdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EdgeServiceImpl implements EdgeService {
    @Autowired
    EdgeRepository edgeRepository;

    @Override
    public void addRelation(Edge edge){
//        System.out.println("come");
//        System.out.println(edge);
        edgeRepository.save(edge);
        edgeRepository.flush();
    }

   @Override
    public int getEdgeNumber(Integer cid){
        return edgeRepository.findAllByCid(cid).size();
    }

    @Override
    public List<Edge> findEdgeOfChart(Integer cid){
        return edgeRepository.findAllByCid(cid);
    }

    @Override
    public void delEdge(Integer eid){
        edgeRepository.deleteById(eid);
        edgeRepository.flush();
    }

    @Override
    public void delEdgeByNode(String name,Integer cid){
        List<Edge> edgesS=edgeRepository.findAllBySourceAndCid(name,cid);
        List<Edge> edgesT=edgeRepository.findAllByTargetAndCid(name,cid);
//        System.out.println("边");
//        System.out.println(edgesS);
//        System.out.println(edgesT);
        for(int i=0;i<edgesS.size();i++){
            edgeRepository.deleteById(edgesS.get(i).getEid());
        }
        for(int i=0;i<edgesT.size();i++){
            edgeRepository.deleteById(edgesT.get(i).getEid());
        }
//        System.out.println("edges over");
        edgeRepository.flush();
    }

    @Override
  public void updateEdge(Edge edge){
      edgeRepository.saveAndFlush(edge);
      System.out.println(edge);
    }
}
