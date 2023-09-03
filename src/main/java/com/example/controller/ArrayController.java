package com.example.controller;

import com.example.dto.ArrayDtoInput;
import com.example.exceptioin.ArrayIsEmptyException;
import com.example.model.Array;
import com.example.model.Sort;
import com.example.service.ArrayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ArrayController {

    private final ArrayService arrayService;

    @GetMapping
    public String getHomepage(Model model) {
        log.info("Calling GET: /");

        ArrayDtoInput arrayDtoInput = new ArrayDtoInput();
        model.addAttribute("arrayDtoInput", arrayDtoInput);

        List<Array> savedArrays = arrayService.getAllArrays();
        model.addAttribute("selectArrayOptions", savedArrays);

        return "index";
    }

    @PostMapping(path = "/array")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String sendArray(@Valid @ModelAttribute("arrayDto") ArrayDtoInput arrayDto, Model model) {
        log.info("Calling POST: /array with 'arrayDto': {}", arrayDto.toString());

        if (arrayDto.getArray().length() == 0) {
            throw new ArrayIsEmptyException("Array must not be empty.");
        }

        arrayService.add(arrayDto.getArray(), arrayDto.getArrayName(), Sort.valueOf(arrayDto.getSort()));

        ArrayDtoInput arrayDtoInput = new ArrayDtoInput();
        model.addAttribute("arrayDtoInput", arrayDtoInput);

        List<Array> savedArrays = arrayService.getAllArrays();
        model.addAttribute("selectArrayOptions", savedArrays);

        return "index";
    }

    @GetMapping(path = "/array")
    public String getArray(@RequestParam Integer arrayId, Model model) {
        log.info("Calling GET: /array with 'arrayId': {}", arrayId);

        ArrayDtoInput arrayDtoInput = new ArrayDtoInput();
        model.addAttribute("arrayDtoInput", arrayDtoInput);

        List<Array> savedArrays = arrayService.getAllArrays();
        model.addAttribute("selectArrayOptions", savedArrays);

        String result = arrayService.getArrayById(arrayId);
        model.addAttribute("result", result);

        return "index";
    }
}
