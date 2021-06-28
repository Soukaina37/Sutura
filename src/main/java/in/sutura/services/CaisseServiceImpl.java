package in.sutura.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sutura.entities.Caisse;
import in.sutura.entities.Cotisation;
import in.sutura.entities.Don;
import in.sutura.repositories.CaisseRepository;
import in.sutura.repositories.CotisationRepository;
import in.sutura.repositories.DonRepository;


@Service
public class CaisseServiceImpl implements CaisseService {
	 	private CaisseRepository caisseRepository;

	    @Autowired
	    public void setCaisseRepository(CaisseRepository caisseRepository) {
	        this.caisseRepository = caisseRepository;
	    }
	    
	    private CotisationRepository cotisationRepository;
	    
	    @Autowired
	    public void setCotisationRepository(CotisationRepository cotisationRepository) {
	    	this.cotisationRepository = cotisationRepository;
	    }
	    
	    private DonRepository donRepository;
	    
	    @Autowired
	    public void setDonRepository(DonRepository donRepository){
	    	this.donRepository = donRepository;
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
			double chiffreAffaire = calcul_chiffreAffaire(caisse);
			double seuil = caisse.getSeuil();
			double rapport = (chiffreAffaire-montantCaisse)/chiffreAffaire;
			if (rapport>=(1-seuil)) {
				isFavorable = true;
			}
			return isFavorable;
		}
		
		//Revoir cette méthode
		@Override
		public double calcul_marge(Caisse caisse) {
			double chiffreAffaire = calcul_chiffreAffaire(caisse);
			double montantCaisse = caisse.getMontantCaisse();
			/*
			 * Rapport = montantPrété/chiffreAffaire;
			 * montantPrété =  chiffreAffaire - montantCaisse;
			 */
			double rapport = (chiffreAffaire-montantCaisse)/chiffreAffaire;
			double seuil = caisse.getSeuil();
			double marge = Math.round((seuil-rapport)*chiffreAffaire);
			return marge;
		}

		@Override
		public double calcul_chiffreAffaire(Caisse caisse) {
			Long id = caisse.getId();
			double chiffreAffaire = 0;
			List<Cotisation> cotisations = cotisationRepository.findCotisationByCaisse(id);
			List<Don> dons = donRepository.findDonByCaisse(id);
			for(Cotisation c : cotisations) {
				chiffreAffaire += c.getMontant();
			}
			for(Don d : dons) {
				chiffreAffaire += d.getMontant();
			}
			System.out.println("chiffre affaire= "+chiffreAffaire);
			return chiffreAffaire;
		}
	    
}




