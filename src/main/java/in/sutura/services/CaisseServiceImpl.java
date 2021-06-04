package in.sutura.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sutura.entities.Caisse;
import in.sutura.repositories.CaisseRepository;

@Service
public class CaisseServiceImpl implements CaisseService {
	 	private CaisseRepository caisseRepository;

	    @Autowired
	    public void setCaisseRepository(CaisseRepository caisseRepository) {
	        this.caisseRepository = caisseRepository;
	    }

	    @Override
	    public Iterable<Caisse> listAllCaisses() {
	        return caisseRepository.findAll();
	    }

	    @Override
	    public Optional<Caisse> getCaisseById(Long id) {
	        return caisseRepository.findById(id);
	    }

	    @Override
	    public Caisse saveCaisse(Caisse caisse) {
	        return caisseRepository.save(caisse);
	    }

	    @Override
	    public void deleteCaisse(Long id) {
	    	caisseRepository.deleteById(id);
	    }
	    
	    @Override
	    public long countCaisses() {
	        return caisseRepository.count();
	    }

		@Override
		public void recalcul_montant_actuel(double montant, Caisse caisse) {
			double montantActuel = caisse.getMontantActuel();
			montantActuel -= montant;
			caisse.setMontantActuel(montantActuel);
			caisseRepository.save(caisse);
			System.out.println(caisse);
		}

		@Override
		public void recalcul_montant_caisse(double montant, Caisse caisse) {
			double montantCaisse = caisse.getMontantCaisse();
			montantCaisse -= montant;
			caisse.setMontantActuel(montantCaisse);
			caisseRepository.save(caisse);
			
		}
	    
}




