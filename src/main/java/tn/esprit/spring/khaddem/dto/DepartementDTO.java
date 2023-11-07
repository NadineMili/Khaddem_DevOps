
package tn.esprit.spring.khaddem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.khaddem.entities.Etudiant;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartementDTO {

    private Integer idDepartement;
    private String nomDepart;
    private List<Etudiant> etudiants;

    public DepartementDTO(Integer idDepartement, String nomDepart) {
        this.idDepartement = idDepartement;
        this.nomDepart = nomDepart;

    }


}
