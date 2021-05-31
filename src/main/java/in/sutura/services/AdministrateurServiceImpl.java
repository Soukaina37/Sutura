package in.sutura.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sutura.entities.Administrateur;
import in.sutura.repositories.AdministrateurRepository;

/**
 * Administrateur service implement.
 */
@Service
public class AdministrateurServiceImpl implements AdministrateurService {
    
    private AdministrateurRepository administrateurRepository;

    @Autowired
    public void setAdministrateurRepository(AdministrateurRepository administrateurRepository) {
        this.administrateurRepository = administrateurRepository;
    }

    @Override
    public Iterable<Administrateur> listAllAdministrateurs() {
        return administrateurRepository.findAll();
    }

    @Override
    public Optional<Administrateur> getAdministrateurById(Long id) {
        return administrateurRepository.findById(id);
    }

    @Override
    public Administrateur saveAdministrateur(Administrateur administrateur) {
        return administrateurRepository.save(administrateur);
    }

    @Override
    public void deleteAdministrateur(Long id) {
    	administrateurRepository.deleteById(id);
    }
    
    @Override
    public long countAdministrateurs() {
        return administrateurRepository.count();
    }
    
}
