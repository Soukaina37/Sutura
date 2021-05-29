package sutura.in.services;

import java.util.Optional;

import in.sutura.entities.Administrateur;
import in.sutura.entities.Personne;

public interface AdministrateurService {

    Iterable<Personne> listAllAdministrateurs();

    Optional<Personne> getAdministrateurById(Long id);

    Administrateur saveAdministrateur(Administrateur administrateur);
    long countAdministrateurs();

    void deleteAdministrateur(Long id);

}

