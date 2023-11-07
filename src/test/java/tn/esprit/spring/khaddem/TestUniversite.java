package tn.esprit.spring.khaddem;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;
import tn.esprit.spring.khaddem.services.UniversiteServiceImpl;
import javax.transaction.Transactional;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TestUniversite {
    @Autowired
    private UniversiteServiceImpl universiteService;

    @Autowired
    private UniversiteRepository universiteRepository;

    @Test
    public void testAddUniversite() {
        Universite universite = new Universite();
        universite.setNomUniv("Esprit");

        Universite result = universiteService.addUniversite(universite);

        Universite fetchedUniversite = universiteRepository.findById(result.getIdUniversite()).orElse(null);
        assertEquals(universite.getNomUniv(), fetchedUniversite.getNomUniv());
    }

}
