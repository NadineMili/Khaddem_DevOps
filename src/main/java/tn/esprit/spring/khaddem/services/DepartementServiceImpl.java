package tn.esprit.spring.khaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartementServiceImpl implements IDepartementService{
    @Autowired
    DepartementRepository departementRepository;
    @Autowired
    UniversiteRepository universiteRepository;
    @Override
    public List<Departement> retrieveAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public Departement addDepartement(Departement d) {
        departementRepository.save(d);
        return d;
    }

    @Override
    public Departement updateDepartement(Departement d) {
        if (departementRepository.existsById(d.getIdDepartement())) {
            Departement existingDepartement = departementRepository.findById(d.getIdDepartement()).orElse(null);
            if (existingDepartement != null) {
                existingDepartement.setNomDepart(d.getNomDepart());
                departementRepository.save(existingDepartement);
                return existingDepartement;
            }
        }
        return null;
    }


    @Override
    public Departement retrieveDepartement(Integer idDepart) {
        Optional<Departement> optionalDepartement = departementRepository.findById(idDepart);
        return optionalDepartement.orElse(null);
    }
    @Override
    public List<Departement> retrieveDepartementsByUniversite(Integer idUniversite) {
        Optional<Universite> optionalUniversite = universiteRepository.findById(idUniversite);
        if (optionalUniversite.isPresent()) {
            Universite universite = optionalUniversite.get();
            return universite.getDepartements();
        } else {
            return new ArrayList<>();
        }
    }
}
