package tn.esprit.spring.khaddem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
import tn.esprit.spring.khaddem.services.EtudiantServiceImpl;
import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EtudiantServicesTest {


    @Autowired
    private EtudiantServiceImpl etudiantService;

    @Autowired
    private EtudiantRepository etudiantRepository;




    @Test
    public void testAddEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setPrenomE("Sahar");
        Etudiant ajout = etudiantService.addEtudiant(etudiant);
        assertNotNull(ajout.getIdEtudiant());
        etudiantRepository.delete(ajout);

    }
   /* @Test
    public void addEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNomE("sahar");
        Etudiant saveEtudiant = etudiantService.addEtudiant(etudiant);
         assertNotNull(saveEtudiant.getIdEtudiant());
        Etudiant retrievedEtudiant = etudiantRepository.findById(saveEtudiant.getIdEtudiant()).orElse(null);
        assertNotNull(retrievedEtudiant);
        assertEquals(etudiant.getNomE(), retrievedEtudiant.getNomE());
       etudiantRepository.delete(saveEtudiant);
        System.out.println("Le test d'ajout de département s'est terminé avec succès.");
    }*/

   /* @Test
    public void testRetrieveAllEtudiants() {
        EtudiantRepository etudiantRepository = mock(EtudiantRepository.class);
        EtudiantServiceImpl etudiantService = new EtudiantServiceImpl(etudiantRepository, null, null, null);
        List<Etudiant> etudiants = new ArrayList<>();
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();
        assertEquals(etudiants, result);
    }
*/

}
