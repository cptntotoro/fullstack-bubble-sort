package com.example.repository;

import com.example.model.ArrayValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArrayValueRepository extends JpaRepository<ArrayValue, Integer> {

    @Query("SELECT av.value " +
            "FROM ArrayValue as av " +
            "LEFT JOIN Array as a ON av.array.id = a.id " +
            "WHERE a.name LIKE :name")
    List<Integer> findAllByArrayName(@Param("name") String arrayName);
}
