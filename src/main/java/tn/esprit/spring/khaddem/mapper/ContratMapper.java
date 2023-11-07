package tn.esprit.spring.khaddem.mapper;

import tn.esprit.spring.khaddem.dto.ContratDTO;
import tn.esprit.spring.khaddem.entities.Contrat;

public class ContratMapper {

    private ContratMapper() {
        throw new IllegalStateException("Utility class");
    }
    public static Contrat toContrat(ContratDTO contratDTO) {
        return Contrat.builder()
                .dateDebutContrat(contratDTO.getDateDebutContrat())
                .dateFinContrat(contratDTO.getDateFinContrat())
                .specialite(contratDTO.getSpecialite())
                .archived(contratDTO.getArchived())
                .montantContrat(contratDTO.getMontantContrat())
                .etudiant(contratDTO.getEtudiant())
                .build();
    }
}

