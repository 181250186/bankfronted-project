package com.se3.example.Repository;

import com.se3.example.entity.Edge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EdgeRepository extends JpaRepository<Edge,Integer> {

    // @Query("select * from edge where cid = ?1")
    List<Edge> findAllByCid(Integer cid);
    List<Edge> findAllBySourceAndCid(String source,Integer cid);
    List<Edge> findAllByTargetAndCid(String source,Integer cid);
    Edge findByName(String name);
}
