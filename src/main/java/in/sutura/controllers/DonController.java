package in.sutura.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.sutura.entities.Caisse;
import in.sutura.entities.Don;
import in.sutura.services.CaisseService;
import in.sutura.services.DonService;
import in.sutura.services.PretService;

/**
 * Don controller.
 */
@Controller
public class DonController {

    private DonService donService;

    @Autowired
    public void setDonService(DonService donService) {
        this.donService = donService;
    }
    
    private CaisseService caisseService;
    
    @Autowired
    public void setCaisseService(CaisseService caisseService) {
        this.caisseService = caisseService;
    }
    
    private PretService pretService;

    @Autowired
    public void setPretService(PretService pretService) {
        this.pretService = pretService;
    }

    /**
     * List all dons.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/dons", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("dons", donService.listAllDons());
        System.out.println("Returning dons:");
        return "dons";
    }

    /**
     * View a specific don by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("don/{id}")
    public String showDon(@PathVariable Long id, Model model) {
    	Optional<Don> don = donService.getDonById(id);
   	 
   	 if( don.isPresent() ) {
            model.addAttribute("don", don.get());
   }
   	 return "donshow";
    }

    // Afficher le formulaire de modification du Don
    @RequestMapping("don/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
    	Optional<Don> don = donService.getDonById(id);
      	 
      	 if( don.isPresent() ) {
               model.addAttribute("don", don.get());
               model.addAttribute("flag", "il faut afficher l'ID");
      }
      	 return "donform";
       }

    /**
     * New don.
     *
     * @param model
     * @return
     */
    @RequestMapping("don/new")
    public String newDon(Model model) {
        model.addAttribute("don", new Don());
        return "donform";
    }

    /**
     * Save don to database.
     *
     * @param don
     * @return
     */
    @RequestMapping(value = "don", method = RequestMethod.POST)
    public String saveDon(Don don) {
    	//modification des montants de la caisse lors de l'ajout d'un don
        double montant = don.getMontant();
        Caisse caisse =  don.getCaisse();
        
        double montantActuel = caisse.getMontantActuel();
        double montantCaisse = caisse.getMontantCaisse();
        
        montantActuel += montant;
        montantCaisse += montant;
        
        caisse.setMontantActuel(montantActuel);
        caisse.setMontantCaisse(montantCaisse);
        //mise à jour de la caisse
        caisseService.update(caisse);
        //ajout de la dépense dans la base de données
        donService.saveDon(don);
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
        return "redirect:/don/" + don.getId();
    }

    /**
     * Delete don by its id.
     *
     * @param id
     * @return
     */
    @RequestMapping("don/delete/{id}")
    public String delete(@PathVariable Long id) {
        donService.deleteDon(id);
        return "redirect:/dons";
    }

}
