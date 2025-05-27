package com.cafeteria.cafe_service.service;

import com.cafeteria.cafe_service.model.Cafe;
import com.cafeteria.cafe_service.repository.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CafeService {

    @Autowired
    private CafeRepository cafeRepository;

    public List<Cafe> obtenerTodos() {
        return cafeRepository.findAll();
    }

    public Optional<Cafe> obtenerPorId(Long id) {
        return cafeRepository.findById(id);
    }

    public Cafe guardar(Cafe cafe) {
        return cafeRepository.save(cafe);
    }

    public void eliminar(Long id) {
        cafeRepository.deleteById(id);
    }
}
