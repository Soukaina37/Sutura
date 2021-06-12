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
	    
	    void update(Caisse caisse);

		boolean is_favorable(Caisse caisse);

		double calcul_marge(Caisse caisse);
		
		double calcul_chiffreAffaire(Caisse caisse);

}
