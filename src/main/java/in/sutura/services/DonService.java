package in.sutura.services;

import java.util.Optional;

import in.sutura.entities.Don;

public interface DonService {

    Iterable<Don> listAllDons();

    Optional<Don> getDonById(Long id);

    Don saveDon(Don etudiant);

    void deleteDon(Long id);
    long countDons();

}
