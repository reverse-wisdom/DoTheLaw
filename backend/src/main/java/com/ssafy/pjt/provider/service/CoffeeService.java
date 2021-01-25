package com.ssafy.pjt.provider.service;

import com.ssafy.pjt.core.service.CoffeeUseCase;
import com.ssafy.pjt.core.service.dto.CoffeeDTO;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CoffeeService implements CoffeeUseCase {

    @Override
    public Optional<List<CoffeeDTO>> findAll(String coffee) {
        return Optional.of(
                Arrays.asList(CoffeeDTO.builder().name(coffee).price(1200).build())
        );
    }
}
