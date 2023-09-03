package com.example.service;

import com.example.model.Array;
import com.example.model.Sort;

import java.util.List;

public interface ArrayService {

    void add(String arrayString, String arrayName, Sort sort);

    List<Array> getAllArrays();

    String getArrayById(Integer id);

}