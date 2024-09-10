package com.example.futurbe.controller;

import com.example.futurbe.dto.OffreDto;
import com.example.futurbe.services.iservices.IOffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/offres")
public class OffreController {
    @Autowired
    private IOffreService offreService;

    @PostMapping
    public OffreDto createOffre(@RequestBody OffreDto offreDto) {
        return offreService.createOffre(offreDto);
    }

    @GetMapping("/{id}")
    public OffreDto getOffre(@PathVariable Long id) {
        return offreService.getOffre(id);
    }

    @GetMapping
    public List<OffreDto> getAllOffres() {
        return offreService.getAllOffres();
    }

    @PutMapping("/{id}")
    public OffreDto updateOffre(@PathVariable Long id, @RequestBody OffreDto offreDto) {
        return offreService.updateOffre(id, offreDto);
    }

    @DeleteMapping("/{id}")
    public void deleteOffre(@PathVariable Long id) {
        offreService.deleteOffre(id);
    }
}

