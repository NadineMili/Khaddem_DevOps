package tn.esprit.spring.khaddem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.services.DepartementServiceImpl;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DepartementServiceTest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    @Test
    public void testAddDepartementWithMock() {
        Departement departement = new Departement();
        departement.setNomDepart("Informatique");
        Mockito.when(departementRepository.save(Mockito.any(Departement.class))).thenAnswer(invocation -> {
            Departement d = invocation.getArgument(0);
            if (d == null) {
                throw new IllegalArgumentException("Entity must not be null");
            }
            d.setIdDepartement(1);
            return d;
        });
        Departement savedDepartement = departementService.addDepartement(departement);
        assertNotNull("L'ID du département sauvegardé ne doit pas être null", savedDepartement.getIdDepartement());
        Mockito.when(departementRepository.findById(savedDepartement.getIdDepartement())).thenReturn(Optional.ofNullable(savedDepartement));
        Departement retrievedDepartement = departementService.retrieveDepartement(savedDepartement.getIdDepartement());
        assertNotNull("Le département récupéré ne doit pas être null", retrievedDepartement);
        assertEquals("Le nom du département récupéré devrait correspondre au nom du département ajouté", departement.getNomDepart(), retrievedDepartement.getNomDepart());

        System.out.println("Le test d'ajout de département avec mock s'est terminé avec succès.");
    }
  }
