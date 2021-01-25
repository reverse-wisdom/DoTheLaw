package com.ssafy.pjt.core.service;

import java.util.List;
import java.util.Optional;

import com.ssafy.pjt.core.service.dto.CoffeeDTO;

public interface CoffeeUseCase {
    Optional<List<CoffeeDTO>> findAll(String coffee);
}
