package in.sutura.services;

import java.util.Optional;

import in.sutura.entities.Remboursement;

public interface RemboursementService {

    Iterable<Remboursement> listAllRemboursements();

    Optional<Remboursement> getRemboursementById(Long id);

    Remboursement saveRemboursement(Remboursement remboursement);

    void deleteRemboursement(Long id);
    
    long countRemboursements();
    
    //méthode appelée lors du calcul de la priorite
   // double montant_remboursement_proches();

}
