package sutura.in.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sutura.entities.Administrateur;
import in.sutura.entities.Personne;
import in.sutura.repositories.AdministrateurRepository;
import in.sutura.repositories.PersonneRepository;

/**
 * Administrateur service implement.
 */
@Service
public class AdministrateurServiceImpl implements AdministrateurService {

    private PersonneRepository personneRepository;
    
    private AdministrateurRepository administrateurRepository;

    @Autowired
    public void setAdministrateurRepository(AdministrateurRepository administrateurRepository) {
        this.administrateurRepository = administrateurRepository;
    }

    @Override
    public Iterable<Personne> listAllAdministrateurs() {
        return personneRepository.findAll();
    }

    @Override
    public Optional<Personne> getAdministrateurById(Long id) {
        return personneRepository.findById(id);
    }

    @Override
    public Administrateur saveAdministrateur(Administrateur administrateur) {
        return personneRepository.save(administrateur);
    }

    @Override
    public void deleteAdministrateur(Long id) {
    	personneRepository.deleteById(id);
    }
    
    @Override
    public long countAdministrateurs() {
        return administrateurRepository.count();
    }
    
}
