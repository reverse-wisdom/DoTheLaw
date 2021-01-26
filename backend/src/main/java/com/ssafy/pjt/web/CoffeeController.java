package com.ssafy.pjt.web;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.core.service.dto.CoffeeDTO;
import com.ssafy.pjt.provider.service.CoffeeService;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApiModel
@CrossOrigin(origins = { "http://localhost:8080" })
@RestController
@RequestMapping("/api/v1/coffees")
@RequiredArgsConstructor
public class CoffeeController {

    private final CoffeeService coffeeService;
    @ApiOperation(value = "Test")
    @GetMapping
    public List<CoffeeDTO> getAllCoffees(@RequestParam(required = true)  String coffee) {
        return coffeeService.findAll(coffee).orElse(Collections.emptyList());
    }
}