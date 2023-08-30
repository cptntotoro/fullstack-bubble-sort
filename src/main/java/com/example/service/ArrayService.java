package com.example.service;

import com.example.dto.ArrayDto;
import com.example.model.Sort;

public interface ArrayService {

    void add(ArrayDto arrayDto, String arrayName);

    ArrayDto get(Integer id, Sort sort);
}
