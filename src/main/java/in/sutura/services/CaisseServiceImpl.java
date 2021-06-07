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
		public void update(Caisse caisse) {
			Long id = caisse.getId();
			caisseRepository.update(id, caisse);
			
		}

		@Override
		public boolean is_favorable(Caisse caisse) {
			boolean isFavorable = false;
			double montantCaisse = caisse.getMontantCaisse();
			double montantActuel = caisse.getMontantActuel();
			double seuil = caisse.getSeuil();
			double rapport = montantCaisse/montantActuel;
			if (rapport>seuil) {
				isFavorable = true;
			}
			return isFavorable;
		}
		
		//Revoir cette méthode
		@Override
		public double calcul_marge(Caisse caisse) {
			double marge = 0;
			double seuil = caisse.getSeuil();
			marge = caisse.getMontantCaisse() - seuil*caisse.getMontantActuel();
			return marge;
		}
	    
}




