package com.se3.example.service;

import com.alibaba.fastjson.JSONObject;
import com.se3.example.entity.Chart;
import com.se3.example.entity.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ChartService {
    /*
    * 保存表的信息
    * @Param Chart
    * */
    void saveChart(Chart chart);
    /*
    *返回图谱节点和关系显示
    * @param chartId
    * */
    ArrayList<HashMap<String, Object>> findChart(Integer chartId);
}
