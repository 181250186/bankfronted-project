package com.se3.example.service.Impl;

import com.se3.example.service.ChartService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChartServiceImplTest {

    @Autowired
    ChartService chartService;


    @Test
    void findChart() {
        ArrayList<HashMap<String,Object>> test=chartService.findChart(4);
        System.out.println(test.size());
        Assert.assertNotEquals(0,test.size());
    }
}