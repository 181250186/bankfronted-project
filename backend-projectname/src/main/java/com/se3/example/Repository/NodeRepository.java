package com.se3.example.Repository;

import com.se3.example.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NodeRepository extends JpaRepository<Node,Integer> {
    List<Node> findAllByCid(Integer cid);
    //void deleteByNid(Integer nid);
    Node findNodeByNameAndCid(String name,Integer cid);
    Node findByName(String name);
}
