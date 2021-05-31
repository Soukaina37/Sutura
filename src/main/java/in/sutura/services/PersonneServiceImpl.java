package in.sutura.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sutura.entities.Personne;
import in.sutura.repositories.PersonneRepository;

/**
 * Personne service implement.
 */
@Service
public class PersonneServiceImpl implements PersonneService {

    private PersonneRepository personneRepository;

    @Autowired
    public void setPersonneRepository(PersonneRepository PersonneRepository) {
        this.personneRepository = PersonneRepository;
    }

    @Override
    public Iterable<Personne> listAllPersonnes() {
        return personneRepository.findAll();
    }

    @Override
    public Optional<Personne> getPersonneById(Long id) {
        return personneRepository.findById(id);
    }

    @Override
    public Personne savePersonne(Personne personne) {
        return personneRepository.save(personne);
    }

    @Override
    public void deletePersonne(Long id) {
    	personneRepository.deleteById(id);
    }
    
    @Override
    public long countPersonnes() {
        return personneRepository.count();
    }
    
}

