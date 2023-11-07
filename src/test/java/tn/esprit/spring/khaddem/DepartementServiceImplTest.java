package tn.esprit.spring.khaddem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;
import tn.esprit.spring.khaddem.services.DepartementServiceImpl;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartementServiceImplTest {

    @Autowired
    DepartementServiceImpl departementService;

    @Autowired
    DepartementRepository departementRepository;

    @Autowired
    private UniversiteRepository universiteRepository;

    @Test
    public void addDepartementSansMock() {
        Departement departement = new Departement();
        departement.setNomDepart("Informatique");
        //ajout departement à la BD
        Departement saveDepartement = departementService.addDepartement(departement);
        //verification si département a étè ajouté avec succé
        assertNotNull(saveDepartement.getIdDepartement());
        // vérification si les détails du département ajouté sont corrects
        Departement retrievedDepartement = departementRepository.findById(saveDepartement.getIdDepartement()).orElse(null);
        assertNotNull(retrievedDepartement);
        assertEquals(departement.getNomDepart(), retrievedDepartement.getNomDepart());
        // néttoyage des données ajoutées dans la base de données après le test
        departementRepository.delete(saveDepartement);
        System.out.println("Le test d'ajout de département s'est terminé avec succès.");
    }

    @Test
    public void testUpdateDepartement() {
        Departement departement = new Departement();
        departement.setNomDepart("Informatique");
        departementRepository.save(departement);
        //mise à jour le département
        departement.setNomDepart("NouveauNom");
        departementService.updateDepartement(departement);
        // récupération le département mis à jour
        Departement updatedDepartement = departementRepository.findById(departement.getIdDepartement()).orElse(null);
        // Vérification si le nom du département a été mis à jour avec succès
        assertNotNull(updatedDepartement);
        assertEquals("NouveauNom", updatedDepartement.getNomDepart());
        // nettoyage des données ajoutées dans la base de données après le test
        departementRepository.delete(departement);
    }

    @Test
    public void testRetrieveDepartement() {
        Departement departement = new Departement();
        departement.setNomDepart("Informatique");
        departementRepository.save(departement);

        Integer departementId = departement.getIdDepartement();

        Departement retrievedDepartement = departementService.retrieveDepartement(departementId);

        assertNotNull("Le département récupéré ne doit pas être null", retrievedDepartement);
        assertEquals("Le nom du département récupéré doit correspondre au nom spécifié", "Informatique", retrievedDepartement.getNomDepart());
        assertTrue("L'ID du département récupéré devrait être supérieur à zéro", retrievedDepartement.getIdDepartement() > 0);
        System.out.println("Le test de récupération de département sans Mockito s'est terminé avec succès.");

        //vérification si l'ID du département récupéré est valide
        assertEquals("L'ID du département récupéré devrait être égal à celui de l'objet original", departementId, retrievedDepartement.getIdDepartement());
    }
//    @Test
//    public void testRetrieveDepartementsByUniversiteWithoutMock() {
//        // Préparation des données
//        Universite universite1 = new Universite();
//        universite1.setNomUniv("Universite 1");
//        universiteRepository.save(universite1);
//
//        Departement departement1 = new Departement();
//        departement1.setNomDepart("Informatique");
//        departementRepository.save(departement1);
//
//        Departement departement2 = new Departement();
//        departement2.setNomDepart("Mathematiques");
//        departementRepository.save(departement2);
//
//        // Associer l'université aux départements
//        departement1 = departementRepository.findById(departement1.getIdDepartement()).orElse(null);
//        departement2 = departementRepository.findById(departement2.getIdDepartement()).orElse(null);
//        if (departement1 != null) {
//            departement1.setUniversite(universite1);
//            departementRepository.save(departement1);
//        }
//        if (departement2 != null) {
//            departement2.setUniversite(universite1);
//            departementRepository.save(departement2);
//        }
//
//        List<Departement> retrievedDepartements = departementService.retrieveDepartementsByUniversite(universite1.getIdUniversite());
//
//        // Aassertions
//        assertNotNull("La liste des départements récupérés ne doit pas être null", retrievedDepartements);
//        assertFalse("La liste des départements récupérés ne doit pas être vide", retrievedDepartements.isEmpty());
//        assertEquals("Le nombre de départements récupérés devrait être égal au nombre attendu", 2, retrievedDepartements.size());
//
//        for (Departement departement : retrievedDepartements) {
//            assertNotNull("L'ID du département ne doit pas être null", departement.getIdDepartement());
//            assert
}
