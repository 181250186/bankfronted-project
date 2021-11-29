package com.se3.example.Repository;

import com.se3.example.entity.Chart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChartRepository extends JpaRepository<Chart,Integer> {
}
