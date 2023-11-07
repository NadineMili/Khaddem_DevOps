package tn.esprit.spring.khaddem.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Niveau;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class EquipeServiceImpl implements IEquipeService{

    EquipeRepository equipeRepository;

    ContratRepository contratRepository;

    @Override
    public List<Equipe> retrieveAllEquipes() {
        return equipeRepository.findAll();
    }



    @Transactional
    public Equipe saveEquipe(Equipe e) {
        equipeRepository.save(e);
        return e;
    }

    @Override
    public Equipe modifierEquipe(Equipe e) {

        Optional<tn.esprit.spring.khaddem.entities.Equipe> existingEquipeOptional = equipeRepository.findById(e.getidEquipe());

        if(existingEquipeOptional.isPresent()) {
            Equipe existingEquipe = existingEquipeOptional.get();

            // Update the existing Equipe with the properties of e
            existingEquipe.setNomEquipe(e.getNomEquipe());
            existingEquipe.setNiveau(e.getNiveau());
            // Set other properties as needed

            // Save the modified existing Equipe
            equipeRepository.save(existingEquipe);

            return existingEquipe; // Return the modified Equipe
        } else {
            // Handle the case where the Equipe to modify is not found
            return null;
        }
    }


    @Override
    public Equipe retrieveEquipe(Integer idEquipe) {
        return equipeRepository.findById(idEquipe).get();

    }

    public void evoluerEquipes() {
        log.info("Début de la méthode evoluerEquipes");

        List<Equipe> equipes = equipeRepository.findAll();
        log.debug("Nombre d'équipes : " + equipes.size());

        for (Equipe equipe : equipes) {
            if (isEquipeEligibleForLevelUpdate(equipe)) {
                int nbEtudiantsAvecContratsActifs = countActiveContractStudents(equipe);

                if (nbEtudiantsAvecContratsActifs >= 3) {
                    updateEquipeLevel(equipe);
                }
            }
        }

        log.info("Fin de la méthode evoluerEquipes");
    }

    private boolean isEquipeEligibleForLevelUpdate(Equipe equipe) {
        return equipe.getEtudiants() != null && !equipe.getEtudiants().isEmpty() &&
                (equipe.getNiveau().equals(Niveau.JUNIOR) || equipe.getNiveau().equals(Niveau.SENIOR));
    }


    private int countActiveContractStudents(Equipe equipe) {
        List<Etudiant> etudiants = equipe.getEtudiants();
        int nbEtudiantsAvecContratsActifs = 0;

        for (Etudiant etudiant : etudiants) {
            int activeContractsCount = countActiveContractsForEtudiant(etudiant);
            nbEtudiantsAvecContratsActifs += activeContractsCount;

            if (nbEtudiantsAvecContratsActifs >= 3) {
                break;
            }
        }

        log.info("Nombre d'étudiants avec contrats actifs pour l'équipe " + equipe.getNomEquipe() + " : " + nbEtudiantsAvecContratsActifs);
        return nbEtudiantsAvecContratsActifs;
    }

    private int countActiveContractsForEtudiant(Etudiant etudiant) {
        List<Contrat> contrats = contratRepository.findByEtudiantIdEtudiant(etudiant.getIdEtudiant());
        int activeContractsCount = 0;

        for (Contrat contrat : contrats) {
            if (isContractActiveAndOlderThanOneYear(contrat)) {
                activeContractsCount++;
            }
        }

        return activeContractsCount;
    }



    private boolean isContractActiveAndOlderThanOneYear(Contrat contrat) {
        if (contrat.getArchived() == null) {
            long differenceInTime = contrat.getDateFinContrat().getTime() - contrat.getDateDebutContrat().getTime();
            long diffInYears = differenceInTime / (1000L * 60 * 60 * 24 * 365);
            return diffInYears > 1;
        }
        return false;
    }

    private void updateEquipeLevel(Equipe equipe) {
        if (equipe.getNiveau().equals(Niveau.JUNIOR)) {
            log.info("Mise à jour du niveau de l'équipe " + equipe.getNomEquipe() + " du niveau " + equipe.getNiveau() + " vers le niveau supérieur SENIOR");
            equipe.setNiveau(Niveau.SENIOR);
            equipeRepository.save(equipe);
        } else if (equipe.getNiveau().equals(Niveau.SENIOR)) {
            log.info("Mise à jour du niveau de l'équipe " + equipe.getNomEquipe() + " du niveau " + equipe.getNiveau() + " vers le niveau supérieur EXPERT");
            equipe.setNiveau(Niveau.EXPERT);
            equipeRepository.save(equipe);
        }
    }

}
