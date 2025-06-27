package com.cafeteria.cafe_service.controller;

import com.cafeteria.cafe_service.model.Cafe;
import com.cafeteria.cafe_service.service.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    public ResponseEntity<EntityModel<Cafe>> obtenerCafe(@PathVariable Long id) {
        return cafeService.obtenerPorId(id)
                .map(cafe -> {
                    EntityModel<Cafe> recurso = EntityModel.of(cafe);
                    recurso.add(WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(CafeController.class).obtenerCafe(id)).withSelfRel());
                    recurso.add(WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(CafeController.class).listarCafes()).withRel("todos-los-cafes"));
                    return ResponseEntity.ok(recurso);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cafe crearCafe(@RequestBody Cafe cafe) {
        return cafeService.guardar(cafe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cafe> actualizarCafe(@PathVariable Long id, @RequestBody Cafe cafeActualizado) {
        return cafeService.obtenerPorId(id).map(cafeExistente -> {
            cafeExistente.setNombre(cafeActualizado.getNombre());
            cafeExistente.setTipo(cafeActualizado.getTipo());
            cafeExistente.setTamaño(cafeActualizado.getTamaño());
            cafeExistente.setPrecio(cafeActualizado.getPrecio());
            Cafe actualizado = cafeService.guardar(cafeExistente);
            return ResponseEntity.ok(actualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCafe(@PathVariable Long id) {
        cafeService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

