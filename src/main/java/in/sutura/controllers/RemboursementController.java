package in.sutura.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.sutura.entities.Caisse;
import in.sutura.entities.Remboursement;
import in.sutura.services.CaisseService;
import in.sutura.services.PretService;
import in.sutura.services.RemboursementService;

/**
 * Remboursement controller.
 */
@Controller
public class RemboursementController {

    private RemboursementService remboursementService;

    @Autowired
    public void setRemboursementService(RemboursementService remboursementService) {
        this.remboursementService = remboursementService;
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
     * List all remboursements.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/remboursements", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("remboursements", remboursementService.listAllRemboursements());
        System.out.println("Returning remboursements:");
        return "remboursements";
    }

    /**
     * View a specific remboursement by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("remboursement/{id}")
    public String showRemboursement(@PathVariable Long id, Model model) {
    	Optional<Remboursement> remboursement = remboursementService.getRemboursementById(id);
   	 
   	 if( remboursement.isPresent() ) {
            model.addAttribute("remboursement", remboursement.get());
   	 }
   	 return "remboursementshow";
    }

    // Afficher le formulaire de modification du Remboursement
    @RequestMapping("remboursement/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
    	Optional<Remboursement> remboursement = remboursementService.getRemboursementById(id);
      	 
      	 if( remboursement.isPresent() ) {
               model.addAttribute("remboursement", remboursement.get());
               model.addAttribute("flag", "il faut afficher l'ID");
      	 }
      	 return "remboursementform";
       }

    /**
     * New remboursement.
     *
     * @param model
     * @return
     */
    @RequestMapping("remboursement/new")
    public String newRemboursement(Model model) {
        model.addAttribute("remboursement", new Remboursement());
        return "remboursementform";
    }

    /**
     * Save remboursement to database.
     *
     * @param remboursement
     * @return
     */
    @RequestMapping(value = "remboursement", method = RequestMethod.POST)
    public String saveRemboursement(Remboursement remboursement) {
    	//modification des montants de la caisse lors de l'ajout d'un remboursement
        double montant = remboursement.getMontant();
        Caisse caisse =  remboursement.getCaisse();
        
        double montantActuel = caisse.getMontantActuel();
        double montantCaisse = caisse.getMontantCaisse();
        
        montantActuel += montant;
        montantCaisse += montant;
        
        caisse.setMontantActuel(montantActuel);
        caisse.setMontantCaisse(montantCaisse);
        //mise à jour de la caisse
        caisseService.update(caisse);
        //Ajout de la cotisation dans la base de données
        remboursementService.saveRemboursement(remboursement);
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
        
        //On met etat_remboursement du dernier prêt à true
        //après faire une recherche concernant l'étudiant
        Pret pret = remboursement.getPret();
        pret.setEtatRemboursement(true);
*/
        remboursementService.saveRemboursement(remboursement);
        return "redirect:/remboursement/" + remboursement.getId();
    }

    /**
     * Delete remboursement by its id.
     *
     * @param id
     * @return
     */
    @RequestMapping("remboursement/delete/{id}")
    public String delete(@PathVariable Long id) {
        remboursementService.deleteRemboursement(id);
        return "redirect:/remboursements";
    }

}
