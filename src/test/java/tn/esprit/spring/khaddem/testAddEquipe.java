package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.spring.khaddem.controllers.EquipeRestController;
import tn.esprit.spring.khaddem.dto.EquipeDTO;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.services.EquipeServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class testAddEquipe {

    @InjectMocks
    private EquipeRestController equipeController;

    @Mock
    private EquipeServiceImpl equipeService;

    @Test
    void testAddEquipe() {
        EquipeDTO equipeDTO = new EquipeDTO();
        equipeDTO.setNomEquipe("Sample Equipe");

        // Stubbing the save method of the service
        when(equipeService.saveEquipe(any(Equipe.class))).thenAnswer(invocation -> {
            Equipe equipe = invocation.getArgument(0);
            equipe.setidEquipe(1); // Set a sample ID for testing
            return equipe;
        });

        // Call the controller method
        EquipeDTO result = equipeController.addEquipe(equipeDTO);

        // Verify if the save method is called
        verify(equipeService).saveEquipe(any(Equipe.class));

    }
}
