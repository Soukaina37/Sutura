package in.sutura.services;

import java.util.Optional;

import in.sutura.entities.Cotisation;

public interface CotisationService {
	
	   Iterable<Cotisation> listAllCotisations();

	    Optional<Cotisation> getCotisationById(Long id);

	    Cotisation saveCotisation(Cotisation cotisation);
	    
	    long countCotisations();

	    void deleteCotisation(Long id);

}
