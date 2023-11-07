
package tn.esprit.spring.khaddem;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Option;
import tn.esprit.spring.khaddem.entities.Specialite;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.services.ContratServiceImpl;
import tn.esprit.spring.khaddem.services.EtudiantServiceImpl;

import java.util.Date;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContratServiceImplJunitTest {

    @Autowired
    private ContratServiceImpl contratService;
    @Autowired
    private EtudiantServiceImpl etudiantService;
    @Autowired
    private ContratRepository contratRepository;

    private Etudiant etudiant;
    private Contrat contrat, contrat2;

    @BeforeAll
    @Test
    public void testAddContrat() {
        contrat = Contrat.builder().montantContrat(8000)
                .dateFinContrat(new Date(125, 7, 1))
                .dateDebutContrat(new Date(120, 7, 1))
                .specialite(Specialite.CLOUD).archived(false)
                .build();

        contrat = contratService.addContrat(contrat);

        Assertions.assertNotNull(contrat);
        Assertions.assertNotNull(contrat.getIdContrat());

        contratRepository.deleteById(contrat.getIdContrat());
    }

    @BeforeAll
    @Test
    public void testRemoveContrat() {
        contrat = Contrat.builder().montantContrat(8000)
                .dateFinContrat(new Date(125, 7, 1))
                .dateDebutContrat(new Date(120, 7, 1))
                .specialite(Specialite.CLOUD).archived(false)
                .build();

        contrat = contratRepository.save(contrat);

        Assertions.assertNotNull(contrat);
        Assertions.assertNotNull(contrat.getIdContrat());

        contratService.removeContrat(contrat.getIdContrat());

        Assertions.assertNull(contratRepository.findById(contrat.getIdContrat()).orElse(null));
    }

    @Test
    @Order(0)
    public void testRetrieveContrat() {
        contrat = contratService.addContrat(Contrat.builder().montantContrat(8000)
                .dateFinContrat(new Date(125, 7, 1))
                .dateDebutContrat(new Date(120, 7, 1))
                .specialite(Specialite.CLOUD).archived(false)
                .build());

        Contrat retrievedContrat = contratService.retrieveContrat(contrat.getIdContrat());

        Assertions.assertNotNull(retrievedContrat);

        contratRepository.deleteById(contrat.getIdContrat());
    }

    @Test
    @Order(1)
    public void testAddAndAffectContratToEtudiant() {
        contrat = Contrat.builder().montantContrat(8000)
                .dateFinContrat(new Date(125, 7, 1))
                .dateDebutContrat(new Date(120, 7, 1))
                .specialite(Specialite.CLOUD).archived(false)
                .build();
        etudiant = etudiantService.addEtudiant(Etudiant.builder().nomE("Reguigui")
                .prenomE("Ibrahim").op(Option.SAE).build());

        contrat = contratService.addAndAffectContratToEtudiant(
                contrat, "Reguigui", "Ibrahim");

        Assertions.assertEquals(etudiant.getIdEtudiant(), contrat.getEtudiant().getIdEtudiant());

        contratService.removeContrat(contrat.getIdContrat());
        etudiantService.removeEtudiant(etudiant.getIdEtudiant());
    }

    @Test
    @Order(2)
    public void retrieveAndUpdateStatusContrat() {
        contrat = contratService.addContrat(Contrat.builder().montantContrat(8000)
                .dateFinContrat(new Date())
                .dateDebutContrat(new Date(120, 7, 1))
                .specialite(Specialite.CLOUD).archived(false)
                .build());

        contratService.retrieveAndUpdateStatusContrat();

        Assertions.assertTrue(contratRepository.findById(contrat.getIdContrat()).get().getArchived());

        contratService.removeContrat(contrat.getIdContrat());
    }

/*    @Test
    @Order(3)
    public void testGetChiffreAffaireEntreDeuxDates() {
        contrat = contratService.addContrat(Contrat.builder().montantContrat(2000)
                .dateFinContrat(new Date(124, 7, 1))
                .dateDebutContrat(new Date(119, 7, 1))
                .specialite(Specialite.SECURITE).archived(false)
                .build());
        contrat2 = contratService.addContrat(Contrat.builder().montantContrat(8000)
                .dateFinContrat(new Date(124, 7, 1))
                .dateDebutContrat(new Date(119, 7, 1))
                .specialite(Specialite.SECURITE).archived(false)
                .build());

        float chiffreAffaire = contratService.getChiffreAffaireEntreDeuxDates(
                new Date(119, 7, 1), new Date(124, 7, 1));

        Assertions.assertEquals(667.032958984375, chiffreAffaire);

        contratRepository.deleteById(contrat.getIdContrat());
        contratRepository.deleteById(contrat2.getIdContrat());
    }*/

}

