package com.example.service;

import com.example.dto.ArrayDto;
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

@Service
@RequiredArgsConstructor
public class ArrayServiceImpl implements ArrayService {

    private final ArrayRepository arrayRepository;
    private final ArrayValueRepository arrayValueRepository;

    @Override
    public void add(ArrayDto arrayDto, String arrayName, Sort sort) {

        Array arraySaved;

        try {
            arraySaved = arrayRepository.save(new Array(arrayName));
        } catch (DataIntegrityViolationException e) {
            throw new SQLConstraintViolationException("Array with name = " + arrayName + " already exists.");
        }

        switch(sort) {
            case ASC:
                int[] array = sortMinToMax(arrayDto.getArray());
//        int[] array = sortBubble(arrayDto.getArray(), Sort.ASC);

                for (int i : array) {
                    arrayValueRepository.save(new ArrayValue(arraySaved, i, Sort.ASC));
                }
                break;
            case DESC:
                array = sortMaxToMin(arrayDto.getArray());
//        array = sortBubble(arrayDto.getArray(), Sort.DESC);

                for (int i : array) {
                    arrayValueRepository.save(new ArrayValue(arraySaved, i, Sort.DESC));
                }
                break;
        }
    }

    @Override
    public ArrayDto get(String arrayName) {
        List<Integer> arrayValues = arrayValueRepository.findAllByArrayName(arrayName);

        if (arrayValues.isEmpty()) {
            throw new ArrayNotFoundException("Массив с именем " + arrayName + " не найден.");
        }

        int[] array = new int[arrayValues.size()];

        for (int i = 0; i < array.length; i++) {
            array[i] = arrayValues.get(i);
        }

        return new ArrayDto(array);

//        int arraySize = (int) arrayRepository.count();
//
//        int[] array = new int[arraySize];
//
//        for (int i = 0; i < array.length - 1; i++) {
//            array[i] = arrayRepository.findOne(i +  1).getValue();
//        }
//
////        arrayRepository.deleteAll();
//
//        return new ArrayDto(array);
    }

//    private int[] sortBubble(int[] array, Sort sort) {
//        for (int i = 0; i < array.length - 1; i++) {
//            for (int j = 0; j < array.length - 1 - i; j++) {
//                switch (sort) {
//                    case ASC:
//                        if (array[j + 1] < array[j]) {
//                            int tmp = array[j];
//                            array[j] = array[j + 1];
//                            array[j + 1] = tmp;
//                        }
//                    case DESC:
//                        if (array[j] < array[j  + 1]) {
//                            int tmp = array[j];
//                            array[j] = array[j + 1];
//                            array[j + 1] = tmp;
//                        }
//                }
//            }
//        }
//        return array;
//    }

    private int[] sortMinToMax(int[] arrayToSort) {
        int[] array = arrayToSort;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j + 1] < array[j]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        return array;
    }

    private int[] sortMaxToMin(int[] arrayToSort) {
        int[] array = arrayToSort;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] < array[j  + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        return array;
    }
}

