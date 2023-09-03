package com.example.repository;

import com.example.model.ArrayValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArrayValueRepository extends JpaRepository<ArrayValue, Integer> {

    @Query("SELECT av.value " +
            "FROM ArrayValue as av " +
            "WHERE av.array.id = :id")
    List<Integer> findAllByArrayId(Integer id);

}