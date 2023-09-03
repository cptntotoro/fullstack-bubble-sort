package com.example.service;

import com.example.exceptioin.ArrayNotFoundException;
import com.example.exceptioin.SQLConstraintViolationException;
import com.example.model.Array;
import com.example.model.ArrayValue;
import com.example.model.Sort;
import com.example.repository.ArrayRepository;
import com.example.repository.ArrayValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArrayServiceImpl implements ArrayService {

    private final ArrayRepository arrayRepository;
    private final ArrayValueRepository arrayValueRepository;

    @Override
    public void add(String arrayString, String arrayName, Sort sort) {
        String[] split = arrayString.split(",");

        int[] arrayFromString = new int[split.length];

        for (int i = 0; i < split.length; i++) {
            arrayFromString[i] = Integer.parseInt(split[i].trim());
        }

        Array arraySaved;

        try {
            arraySaved = arrayRepository.save(new Array(arrayName));
        } catch (DataIntegrityViolationException e) {
            throw new SQLConstraintViolationException("Array with name = " + arrayName + " already exists.");
        }

        switch(sort) {
            case ASC:
                int[] array = sortBubble(arrayFromString, Sort.ASC);

                for (int i : array) {
                    arrayValueRepository.save(new ArrayValue(arraySaved, i, Sort.ASC));
                }
                break;
            case DESC:
                array = sortBubble(arrayFromString, Sort.DESC);

                for (int i : array) {
                    arrayValueRepository.save(new ArrayValue(arraySaved, i, Sort.DESC));
                }
                break;
        }
    }

    @Override
    public List<Array> getAllArrays() {
        return arrayRepository.findAll();
    }

    @Override
    public String getArrayById(Integer id) {
        List<Integer> arrayValues = arrayValueRepository.findAllByArrayId(id);

        if (arrayValues.isEmpty()) {
            throw new ArrayNotFoundException("Array with id = " + id + " doesn't exist.");
        }

        return arrayValues.stream().map(String::valueOf).collect(Collectors.joining(", "));
    }

    private int[] sortBubble(int[] array, Sort sort) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (checkNeighbor(array[j + 1], array[j], sort)) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        return array;
    }

    private boolean checkNeighbor(int first, int second, Sort sort) {
        if (sort == Sort.ASC) {
            return first < second;
        }
        return second < first;
    }
}