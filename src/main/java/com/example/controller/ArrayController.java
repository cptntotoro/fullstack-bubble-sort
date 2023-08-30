package com.example.controller;

import com.example.dto.ArrayDto;
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
    public void add(@Valid @RequestBody ArrayDto arrayDto, @RequestParam @NotNull String arrayName) {
        log.info("Calling POST: /array with 'arrayDto': {}, 'arrayName: {}", arrayDto.toString(), arrayName);
        arrayService.add(arrayDto, arrayName);
    }

    @GetMapping("/array")
    public ArrayDto get(@RequestParam @NotNull Integer id, @RequestParam @NotNull Sort sort) {
        log.info("Calling GET: /array with 'id': {}, 'sort: {}", id, sort.toString());
        return arrayService.get(id, sort);
    }

}
