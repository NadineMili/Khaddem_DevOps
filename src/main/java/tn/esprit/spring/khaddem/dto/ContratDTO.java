package tn.esprit.spring.khaddem.dto;

import lombok.*;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Specialite;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContratDTO implements Serializable {

    @Temporal(TemporalType.DATE)
    private Date dateDebutContrat;
    @Temporal(TemporalType.DATE)
    private Date dateFinContrat;
    @Enumerated(EnumType.STRING)
    private Specialite specialite;
    private Boolean archived;
    private Integer montantContrat;
    @ManyToOne
    // @JsonIgnore
    private Etudiant etudiant;

}
