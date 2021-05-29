package sutura.in.services;

import java.util.Optional;

import in.sutura.entities.Personne;

public interface PersonneService {
	
	Iterable<Personne> listAllPersonnes();

	Optional<Personne> getPersonneById(Long id);

	Personne savePersonne(Personne personne);
    long countPersonnes();

    void deletePersonne(Long id);

}
