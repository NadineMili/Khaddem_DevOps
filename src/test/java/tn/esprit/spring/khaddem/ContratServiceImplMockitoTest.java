package tn.esprit.spring.khaddem;

import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
import tn.esprit.spring.khaddem.services.ContratServiceImpl;
import tn.esprit.spring.khaddem.services.EtudiantServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ContratServiceImplMockitoTest {

    @InjectMocks
    private ContratServiceImpl contratService;
    @InjectMocks
    private EtudiantServiceImpl etudiantService;
    @Mock
    private EtudiantRepository etudiantRepository;
    @Mock
    private ContratRepository contratRepository;

    private Etudiant etudiant;
    private Contrat contrat,contrat2;

    @Test
    public void testRetrieveAllContrats() {
        List<Contrat> contracts = new ArrayList<>();
        contracts.add(new Contrat());
        contracts.add(new Contrat());
        Mockito.when(contratRepository.findAll()).thenReturn(contracts);

        List<Contrat> result = contratService.retrieveAllContrats();
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void testUpdateContrat() {
        Contrat contract = new Contrat();
        Mockito.when(contratRepository.save(contract)).thenReturn(contract);

        Contrat updatedContract = contratService.updateContrat(contract);
        Assertions.assertEquals(contract, updatedContract);
    }

    @Test
    public void testRetrieveContrat() {
        Contrat contract = new Contrat();
        contract.setIdContrat(1);
        Mockito.when(contratRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(contract));

        Contrat retrievedContract = contratService.retrieveContrat(Mockito.anyInt());
        Assertions.assertEquals(contract, retrievedContract);
    }

    @Test
    public void testRemoveContrat() {
        int id = 1;
        contratService.removeContrat(id);
        
        Mockito.verify(contratRepository).deleteById(id);
    }

    @Test
    public void testAddContrat() {
        Contrat contract = new Contrat();
        Mockito.when(contratRepository.save(contract)).thenReturn(contract);

        Contrat addedContract = contratService.addContrat(contract);
        Assertions.assertEquals(contract, addedContract);
    }

    @Test
    public void testAddAndAffectContratToEtudiant() {
        Contrat contract = new Contrat();
        Etudiant etudiant = Etudiant.builder().nomE("Reguigui")
                .prenomE("Ibrahim").contrats(new ArrayList<>()).build();


        Mockito.when(etudiantRepository.findByNomEAndPrenomE("John", "Doe")).thenReturn(etudiant);
        Mockito.when(contratRepository.save(contract)).thenReturn(contract);

        Contrat addedContract = contratService.addAndAffectContratToEtudiant(contract, "John", "Doe");
        Assertions.assertEquals(contract, addedContract);
    }

}
