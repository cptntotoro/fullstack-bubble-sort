package com.example.service;

import com.example.dto.ArrayDto;
import com.example.model.Sort;

public interface ArrayService {

    void add(ArrayDto arrayDto, String arrayName, Sort sort);

    ArrayDto get(String arrayName);
}
