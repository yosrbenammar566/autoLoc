package tn.esprit.autoloc.autoloc.service;

import tn.esprit.autoloc.autoloc.domain.Employe;

import java.util.List;

public interface IEmploye {
    List<Employe> retrieveAllEmployes();
    Employe addEmploye(Employe employe);
    Employe updateEmploye(Employe employe);
    Employe retrieveEmploye(Long idEmploye);
    void removeEmploye(Long idEmploye);
    List<Employe> addEmployes(List<Employe> employes);
}
