package tn.esprit.spring.khaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.dto.EquipeDTO;
import tn.esprit.spring.khaddem.services.IEquipeService;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
public class EquipeRestController {
    IEquipeService equipeService;
    // http://localhost:8089/Kaddem/equipe/retrieve-all-equipes
    @GetMapping("/retrieve-all-equipes")
    @ResponseBody
    public List<Equipe> getEquipes() {
        return equipeService.retrieveAllEquipes();
    }


    // http://localhost:8089/Kaddem/equipe/retrieve-equipe/8
    @GetMapping("/retrieve-equipe/{equipe-id}")
    @ResponseBody
    public Equipe retrieveEquipe(@PathVariable("equipe-id") Integer equipeId) {
        return equipeService.retrieveEquipe(equipeId);
    }
    @PostMapping("/add-equipe")
    @ResponseBody
    public EquipeDTO addEquipe(@RequestBody EquipeDTO equipeDTO) {
        Equipe equipe = new Equipe(); // Convert DTO to entity
        equipe.setNomEquipe(equipeDTO.getNomEquipe());
        equipeService.saveEquipe(equipe); // Save the entity
        return new EquipeDTO(equipe.getidEquipe(), equipe.getNomEquipe(),equipe.getNiveau() );
    }
    @PutMapping("/update-equipe")
    @ResponseBody
    public Equipe updateEquipe(@RequestBody EquipeDTO equipeDTO) {
        Equipe equipe = new Equipe();
        equipe.setidEquipe(equipeDTO.getIdEquipe());
        equipe.setNomEquipe(equipeDTO.getNomEquipe());

        equipe.setNiveau(equipeDTO.getNiveau());
        return equipeService.modifierEquipe(equipe);

    }

   // @Scheduled(cron="0 0 13 * * *")
    //@Scheduled(cron="* * 13 * * *")

    @PutMapping("/faireEvoluerEquipes")
    public void faireEvoluerEquipes() {
        equipeService.evoluerEquipes() ;
    }

}
