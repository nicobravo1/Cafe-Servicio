package com.cafeteria.cafe_service.controller;

import com.cafeteria.cafe_service.model.Cafe;
import com.cafeteria.cafe_service.service.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cafes")
public class CafeController {

    @Autowired
    private CafeService cafeService;

    @GetMapping
    public List<Cafe> listarCafes() {
        return cafeService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cafe> obtenerCafe(@PathVariable Long id) {
        return cafeService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cafe crearCafe(@RequestBody Cafe cafe) {
        return cafeService.guardar(cafe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCafe(@PathVariable Long id) {
        cafeService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
