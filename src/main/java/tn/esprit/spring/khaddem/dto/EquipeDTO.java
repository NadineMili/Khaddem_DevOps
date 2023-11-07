package tn.esprit.spring.khaddem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.khaddem.entities.DetailEquipe;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Niveau;

import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipeDTO {
    private static final long serialVersionUID = 1L;


    private Integer idEquipe;

    private String nomEquipe;

    private Niveau niveau;

    private List<Etudiant> etudiants;

    private DetailEquipe detailEquipe;
    public EquipeDTO(Integer idEquipe,String nomEquipe, Niveau niveau){
        this.idEquipe = idEquipe;
        this.nomEquipe = nomEquipe;
        this.niveau = niveau;



    }
    public void setIdEquipe(Integer idEquipe) {
        this.idEquipe = idEquipe;
    }

    public Integer getIdEquipe() {
        return idEquipe;
    }



}
