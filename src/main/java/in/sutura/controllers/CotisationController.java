package in.sutura.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.sutura.entities.Caisse;
import in.sutura.entities.Cotisation;
import in.sutura.services.CaisseService;
import in.sutura.services.CotisationService;
import in.sutura.services.PretService;

/**
 * Cotisation controller.
 */
@Controller
public class CotisationController {

    private CotisationService cotisationService;
    
    private CaisseService caisseService;
    
    private PretService pretService;

    @Autowired
    public void setCotisationService(CotisationService cotisationService) {
        this.cotisationService = cotisationService;
    }
    
    @Autowired
    public void setCaisseService(CaisseService caisseService) {
        this.caisseService = caisseService;
    }
    
    @Autowired
    public void setPretService(PretService pretService) {
        this.pretService = pretService;
    }

    /**
     * List all cotisations.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/cotisations", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("cotisations", cotisationService.listAllCotisations());
        System.out.println("Returning cotisations:");
        return "cotisations";
    }

    /**
     * View a specific cotisation by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("cotisation/{id}")
    public String showCotisation(@PathVariable Long id, Model model) {
    	Optional<Cotisation> cotisation = cotisationService.getCotisationById(id);
   	 
   	 if( cotisation.isPresent() ) {
            model.addAttribute("cotisation", cotisation.get());
   }
   	 return "cotisationshow";
    }

    // Afficher le formulaire de modification du Cotisation
    @RequestMapping("cotisation/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
    	Optional<Cotisation>cotisation = cotisationService.getCotisationById(id);
        if( cotisation.isPresent() ) {
            model.addAttribute("cotisation", cotisation.get());
            model.addAttribute("flag", "il faut afficher l'ID");
   }
        
        return "cotisationform";
    }

    /**
     * New cotisation.
     *
     * @param model
     * @return
     */
    @RequestMapping("cotisation/new")
    public String newCotisation(Model model) {
        model.addAttribute("cotisation", new Cotisation());
        return "cotisationform";
    }

    /**
     * Save cotisation to database.
     *
     * @param cotisation
     * @return
     */
    @RequestMapping(value = "cotisation", method = RequestMethod.POST)
    public String saveCotisation(Cotisation cotisation) {
    	//modification des montants de la caisse lors de l'ajout d'une cotisation
        double montant = cotisation.getMontant();
        Caisse caisse =  cotisation.getCaisse();
        
        double montantActuel = caisse.getMontantActuel();
        double montantCaisse = caisse.getMontantCaisse();
       
        montantActuel += montant;
        montantCaisse += montant;
        
        caisse.setMontantActuel(montantActuel);
        caisse.setMontantCaisse(montantCaisse);
        //mise à jour de la caisse
        caisseService.update(caisse);
        //ajout de la cotisation dans la base de données
        cotisationService.saveCotisation(cotisation);
/*        
        //ON VERIFIE SI LA SITUATION EST FAVORABLE
        boolean isFavorable = caisseService.is_favorable(caisse);
        
        //calcul de la marge
        if (isFavorable) {
        	//On calcule la marge
        	double marge = caisseService.calcul_marge(caisse);
        	
        	//On récupère la liste de prêts de la même période et qui ne sont pas termine par ORDRE DECROISSANTE
        	List<Pret> liste = pretService.getList(caisse);
        	
        	//On fait le recalcule des priorités et le reclassement
        	for(Pret p : liste) {
        		pretService.recalcul_priorite(p);
        		double priorite = pretService.recalcul_priorite(p);
        		p.setPriorite(priorite);
        		pretService.classement_priorite(p);
        	}
        	
        	//On fait appel à la même méthode pour récupérer la liste avec les nouvelles valeurs de priorité
        	List<Pret> nouvelleListe = pretService.getList(caisse);
        	
        	//En commençant par le premier, on cherche si il peut être satisfait par la marge et ainsi de suite
        	for (Pret p : nouvelleListe) {
        		if(p.getMontant()<marge) {
        			//Passage à l'état élu
        			p.setEtat("elu");
        			pretService.update(p);
        			marge -= p.getMontant();
        			//Pour chaque passage, on diminue le montantCaisse
        			double montantCaisse1 = caisse.getMontantCaisse();
        			montantCaisse1 -= p.getMontant();
        			caisse.setMontantCaisse(montantCaisse1);
        			caisseService.update(caisse);
        		}
        		else {
        			break;
        		}
        	}
        	
        }
*/        
        
        return "redirect:/cotisation/" + cotisation.getId();
    }

    /**
     * Delete cotisation by its id.
     *
     * @param id
     * @return
     */
    @RequestMapping("cotisation/delete/{id}")
    public String delete(@PathVariable Long id) {
        cotisationService.deleteCotisation(id);
        return "redirect:/cotisations";
    }

}

