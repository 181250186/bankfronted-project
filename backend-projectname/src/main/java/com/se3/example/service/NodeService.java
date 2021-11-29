package com.se3.example.service;


import com.se3.example.entity.Node;

import java.util.List;

public interface NodeService {
    /*
    * 保存node信息
    * @Param Node
    * */
    void addNode(Node node);
    /*
    * 返回节点个数
    * @Param cid
    * */
    int getNodeNumber(Integer cid);
    /*
    * 查找相关图谱的节点
    * @Param cid
    * */
    List<Node> findNodeOfChart(Integer cid);
    /*
    * 删除节点
    * @param nid
    * */
    void delNode(String name,Integer cid);
    /*
    * 更新节点信息
    * @Param Node
    * */

    void update(Node node);
}
