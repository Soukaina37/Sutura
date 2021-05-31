package in.sutura.services;

import java.util.Optional;

import in.sutura.entities.Pret;

public interface PretService {

    Iterable<Pret> listAllPrets();

    Optional<Pret> getPretById(Long id);

    Pret savePret(Pret etudiant);

    void deletePret(Long id);
    long countPrets();


}

