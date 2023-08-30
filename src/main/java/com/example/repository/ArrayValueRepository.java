package com.example.repository;

import com.example.model.ArrayValue;
import com.example.model.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArrayValueRepository extends JpaRepository<ArrayValue, Integer> {

    List<Integer> findAllByArrayIdAndSort(Integer id, Sort sort);
}
