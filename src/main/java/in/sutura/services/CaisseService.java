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
	    
	    void recalcul_montant_actuel(double montant, Caisse caisse);
	    
	    void recalcul_montant_caisse(double montant,Caisse caisse);
}
