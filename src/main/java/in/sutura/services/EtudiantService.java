package in.sutura.services;

import java.util.Optional;

import in.sutura.entities.Etudiant;

public interface EtudiantService {

    Iterable<Etudiant> listAllEtudiants();

    Optional<Etudiant> getEtudiantById(Long id);

    Etudiant saveEtudiant(Etudiant etudiant);

    void deleteEtudiant(Long id);
    long countEtudiants();

}