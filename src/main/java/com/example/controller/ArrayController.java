package com.example.controller;

import com.example.dto.ArrayDto;
import com.example.exceptioin.ArrayIsEmptyException;
import com.example.model.Sort;
import com.example.service.ArrayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ArrayController {

    private final ArrayService arrayService;

    @PostMapping("/array")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@Valid @RequestBody ArrayDto arrayDto, @RequestParam @NotNull String arrayName, @RequestParam @NotNull Sort sort) {
        log.info("Calling POST: /array with 'arrayDto': {}, 'arrayName: {}", arrayDto.toString(), arrayName);
        if (arrayDto.getArray().length == 0) {
            throw new ArrayIsEmptyException("Array must not be empty.");
        }
        arrayService.add(arrayDto, arrayName, sort);
    }

    @GetMapping("/array")
    public ArrayDto get(@RequestParam @NotNull String arrayName) {
        log.info("Calling GET: /array with 'arrayName': {}", arrayName);
        return arrayService.get(arrayName);
    }

}
