package tn.esprit.spring.khaddem.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.entities.UniversiteDTO;
import tn.esprit.spring.khaddem.services.IUniversiteService;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Gestion des universités")
@RestController
@RequestMapping("/universite")
public class UniversiteRestController {
    @Autowired
    IUniversiteService universiteService;

    @GetMapping("/retrieve-all-universites")
    @Operation(description = "récupérer la liste des universités")
    @ResponseBody
    public List<UniversiteDTO> getUniversites() {
        List<Universite> universites = universiteService.retrieveAllUniversites();
        return universites.stream()
                .map(universite -> new UniversiteDTO(universite.getIdUniversite(), universite.getNomUniv()))
                .collect(Collectors.toList());
    }

    @GetMapping("/retrieve-universite/{universite-id}")
    @Operation(description = "récupérer une université par son id")
    @ResponseBody
    public UniversiteDTO retrieveUniversite(@PathVariable("universite-id") Integer universiteId) {
        Universite universite = universiteService.retrieveUniversite(universiteId);
        return new UniversiteDTO(universite.getIdUniversite(), universite.getNomUniv());
    }

    @PostMapping("/add-universite")
    @Operation(description = "ajouter une université")
    @ResponseBody
    public UniversiteDTO addUniversite(@RequestBody UniversiteDTO universiteDTO) {
        Universite universite = new Universite();
        universite.setIdUniversite(universiteDTO.getIdUniversite());
        universite.setNomUniv(universiteDTO.getNomUniv());

        Universite addedUniversite = universiteService.addUniversite(universite);
        return new UniversiteDTO(addedUniversite.getIdUniversite(), addedUniversite.getNomUniv());
    }


    @PutMapping("/update-universite")
    @Operation(description = "modifier une université")
    @ResponseBody
    public UniversiteDTO updateUniversite(@RequestBody UniversiteDTO universiteDTO) {
        Universite universite = new Universite();
        universite.setIdUniversite(universiteDTO.getIdUniversite());
        universite.setNomUniv(universiteDTO.getNomUniv());
        Universite updatedUniversite = universiteService.updateUniversite(universite);

        return new UniversiteDTO(updatedUniversite.getIdUniversite(), updatedUniversite.getNomUniv());
    }


    @PutMapping("/assignUniversiteToDepartement/{universiteId}/{departementId}")
    @Operation(description = "assigner une université à un département")
    @ResponseBody
    public void assignUniversiteToDepartement(@PathVariable("universiteId") Integer universiteId, @PathVariable("departementId") Integer departementId) {
        universiteService.assignUniversiteToDepartement(universiteId, departementId);
    }
}
