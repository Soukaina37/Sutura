package in.sutura.services;

import java.util.Optional;

import in.sutura.entities.Etudiant;
import in.sutura.entities.Personne;

public interface EtudiantService {

    Iterable<Etudiant> listAllEtudiants();

    Optional<Personne> getEtudiantById(Long id);

    Etudiant saveEtudiant(Etudiant etudiant);

    void deleteEtudiant(Long id);
    long countEtudiants();

}