package in.sutura.services;

import java.util.Optional;

import in.sutura.entities.Depense;

public interface DepenseService {

    Iterable<Depense> listAllDepenses();

    Optional<Depense> getDepenseById(Long id);

    Depense saveDepense(Depense etudiant);

    void deleteDepense(Long id);
    
    long countDepenses();


}