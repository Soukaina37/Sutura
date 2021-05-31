package in.sutura.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sutura.entities.Cotisation;
import in.sutura.repositories.CotisationRepository;

/**
 * Cotisation service implement.
 */
@Service
public class CotisationServiceImpl implements CotisationService {

    private CotisationRepository cotisationRepository;

    @Autowired
    public void setCotisationRepository(CotisationRepository cotisationRepository) {
        this.cotisationRepository = cotisationRepository;
    }

    @Override
    public Iterable<Cotisation> listAllCotisations() {
        return cotisationRepository.findAll();
    }

    @Override
    public Optional<Cotisation> getCotisationById(Long id) {
        return cotisationRepository.findById(id);
    }

    @Override
    public Cotisation saveCotisation(Cotisation cotisation) {
        return cotisationRepository.save(cotisation);
    }

    @Override
    public void deleteCotisation(Long id) {
        cotisationRepository.deleteById(id);
    }
    
    @Override
    public long countCotisations() {
        return cotisationRepository.count();
    }
    
}

