package in.sutura.services;

import java.util.Optional;

import in.sutura.entities.Administrateur;
import in.sutura.repositories.AdministrateurRepository;

public interface AdministrateurService {

    Iterable<Administrateur> listAllAdministrateurs();

    Optional<Administrateur> getAdministrateurById(Long id);

    Administrateur saveAdministrateur(Administrateur administrateur);
    
    long countAdministrateurs();

    void deleteAdministrateur(Long id);
    
    void setAdministrateurRepository(AdministrateurRepository administrateurRepository);

}

