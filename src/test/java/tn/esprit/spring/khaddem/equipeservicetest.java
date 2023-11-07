package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.services.EquipeServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.List;


@SpringBootTest
@ActiveProfiles("test")
public class equipeservicetest {
    @Autowired
    private EquipeServiceImpl equipeService;

    @Test
    public void testRetrieveAllEquipes() {

        List<Equipe> equipes = equipeService.retrieveAllEquipes();
        assertEquals(1, equipes.size()); 

    }



}
