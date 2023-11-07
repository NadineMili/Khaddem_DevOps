package tn.esprit.spring.khaddem.services;

import tn.esprit.spring.khaddem.entities.Equipe;

import java.util.List;

public interface IEquipeService {

    List<Equipe> retrieveAllEquipes();

    Equipe  saveEquipe(Equipe  e);

    Equipe modifierEquipe (Equipe  e);

    Equipe retrieveEquipe (Integer idEquipe);

    public void evoluerEquipes();

}
