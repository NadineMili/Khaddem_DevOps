package tn.esprit.spring.khaddem.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class UniversiteServiceImpl implements IUniversiteService {
    @Autowired
    UniversiteRepository universiteRepository;
    @Autowired
    DepartementRepository departementRepository;

    @Override
    public List<Universite> retrieveAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite addUniversite(Universite u) {
        log.debug("u: " + u.getNomUniv());
        universiteRepository.save(u);
        return u;
    }

    @Override
    public Universite updateUniversite(Universite u) {
        universiteRepository.save(u);
        return u;
    }

    @Override
    public Universite retrieveUniversite(Integer idUniversite) {
        Optional<Universite> optionalUniversite = universiteRepository.findById(idUniversite);
        if (optionalUniversite.isPresent()) {
            return optionalUniversite.get();
        } else {
            // Handle the case when the Universite with the specified ID is not found.
            // You can throw an exception or return a default value, depending on your use case.
            throw new NoSuchElementException("Universite with ID " + idUniversite + " not found");
        }
    }

    @Transactional
    public void assignUniversiteToDepartement(Integer universiteId, Integer departementId) {
        Optional<Universite> optionalUniversite = universiteRepository.findById(universiteId);
        Optional<Departement> optionalDepartement = departementRepository.findById(departementId);

        if (optionalUniversite.isPresent() && optionalDepartement.isPresent()) {
            Universite universite = optionalUniversite.get();
            Departement departement = optionalDepartement.get();
            universite.getDepartements().add(departement);
            log.info("departements number " + universite.getDepartements().size());
        } else {
            // Handle the case when either the Universite or Departement with the specified IDs are not found.
            // You can throw an exception or handle it as appropriate for your use case.
            throw new NoSuchElementException("Universite or Departement not found");
        }
    }
}

