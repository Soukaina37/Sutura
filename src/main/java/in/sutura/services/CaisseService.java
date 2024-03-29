package in.sutura.services;

import java.util.Optional;

import in.sutura.entities.Caisse;
import in.sutura.repositories.CaisseRepository;

public interface CaisseService {
	
	 	Iterable<Caisse> listAllCaisses();

	    Optional<Caisse> getCaisseById(Long id);

	    Caisse saveCaisse(Caisse caisse);
	    long  countCaisses();

	    void deleteCaisse(Long id);
	    
	    void setCaisseRepository(CaisseRepository caisseRepository);
	    
	    Caisse recalcul_montant_fromDepense(double montant, Caisse caisse);
	    
	    void update(Caisse caisse);

}
