package com.se3.example.service;

import com.se3.example.entity.Edge;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface EdgeService {
    /*
    * 返回边的个数
    *@Param cid
    * */
   int getEdgeNumber(Integer cid);
    /*
    *添加关系
    * @param Edge
    */
    void addRelation(Edge edge);
    /*
    * 查找相关图谱的关系
    * @Param cid
    * */
    List<Edge> findEdgeOfChart(Integer cid);
    /*
    *删除关系
    * @param  eid
    *  */
    void delEdge(Integer eid);
    /*
    * 删除节点的关系
    * @param nid
    * */
    void delEdgeByNode(String name, Integer cid);

}
