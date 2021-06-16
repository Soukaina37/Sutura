package in.sutura.controllers;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.sutura.entities.Caisse;
import in.sutura.entities.Etudiant;
import in.sutura.entities.Pret;
import in.sutura.services.CaisseService;
import in.sutura.services.PretService;

/**
 * Pret controller.
 */
@Controller
public class PretController {

    private PretService pretService;
    
    @Autowired
    public void setPretService(PretService pretService) {
        this.pretService = pretService;
    }
    
    private CaisseService caisseService;
    //private CalculPriorite calculPriorite;
    @Autowired
    public void setCaisseService(CaisseService caisseService) {
        this.caisseService = caisseService;
    }

    /**
     * List all prets.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/prets", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("prets", pretService.listAllPrets());
        System.out.println("Returning prets:");
        return "prets";
    }

    /**
     * View a specific pret by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("pret/{id}")
    public String showPret(@PathVariable Long id, Model model) {
    	Optional<Pret> pret = pretService.getPretById(id);
   	 
   	 if( pret.isPresent() ) {
            model.addAttribute("pret", pret.get());
   }
   	 return "pretshow";
    }

    // Afficher le formulaire de modification du Pret
    @RequestMapping("pret/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
    	Optional<Pret> pret = pretService.getPretById(id);
      	 
      	 if( pret.isPresent() ) {
               model.addAttribute("pret", pret.get());
               model.addAttribute("flag", "il faut afficher l'ID");
      	 }
      	 return "pretform";
     }

    /**
     * New pret.
     *
     * @param model
     * @return
     */
    @RequestMapping("pret/new")
    public String newPret(Model model) {
    	Pret pret= new Pret();
    	pret.setStatutTraitement(false);

        model.addAttribute("pret",pret);
        return "pretform";
    }

    /**
     * Save pret to database.
     *
     * @param pret
     * @return
     */
    @RequestMapping(value = "pret", method = RequestMethod.POST)
    public String savePret(Pret pret) {
    	
    	//On vérifie si l'étudiant a déja un pret en cours
    	Etudiant etudiant = pret.getEtudiant();
    	boolean aDejaUnPretEnCours = pretService.aDejaUnPretEnCours(etudiant);
    		
	    	if(!aDejaUnPretEnCours) {
	    	//CALCUL DE LA PRIORITE DU PRET
	    	double priorite = pretService.calcul_priorite(pret);
	    	//SI LA PRIORITE = 0, ALORS L'ETUDIANT N'A PAS ENCORE COTISE
		    //DANS CE CAS, IL N'A PAS LE DROIT DE DEMANDER UN PRET
		    	if(priorite!=0) {
			    	//puis on met la valeur dans l'objet
			    	pret.setPriorite(priorite);
			    	//ensuite on fait le classement du pret
			    	pretService.classement_priorite(pret);
			    	//enfin on persiste l'objet
			    	pretService.savePret(pret);
			    	
			    	Caisse caisse =  pret.getCaisse();
			    	
			    	//lorsqu'on ajoute un nouveau prêt
			    	//ON VERIFIE SI LA SITUATION EST FAVORABLE
			        boolean isFavorable = caisseService.is_favorable(caisse);
			        //calcul de la marge
				        if (isFavorable) {
				        	//On met à jour l'état de la caisse
				        	caisse.setIsFavorable(true);
				        	//On calcule la marge
				        	double marge = caisseService.calcul_marge(caisse);
				        	
				        	//On récupère la liste de prêts de la même période et qui ne sont pas termine par ORDRE DECROISSANTE DE PRIORITE
				        	List<Pret> liste = pretService.getList(caisse);
				        	
				        	//On fait le recalcule des priorités et le reclassement
				        	for(Pret p : liste) {
				        		pretService.recalcul_priorite(p);
				        		double nouvellePriorite = pretService.recalcul_priorite(p);
				        		p.setPriorite(nouvellePriorite);
				        		pretService.classement_priorite(p);
				        		System.out.println(nouvellePriorite);
				        	}
				        	
				        	//On fait appel à la même méthode pour récupérer la liste avec les nouvelles valeurs de priorité
				        	List<Pret> nouvelleListe = pretService.getList(caisse);
				        	
				        	//En commençant par le premier, on cherche si il peut être satisfait par la marge et ainsi de suite
				        	for (Pret p : nouvelleListe) {
				        		if(p.getMontant()<marge) {
				        			//Passage à l'état élu
				        			System.out.println("passage à l'état élu");
				        			//Passage à l'état élu
				        			p.setEtat("elu");
				        			p.setDateModification(new Date(System.currentTimeMillis()));
				        			pretService.update(p);
				        			marge -= p.getMontant();
				        			//Pour chaque passage, on diminue le montantCaisse
				        			double montantCaisse1 = caisse.getMontantCaisse();
				        			montantCaisse1 -= p.getMontant();
				        			caisse.setMontantCaisse(montantCaisse1);
				        			caisseService.update(caisse);
				        		}
				        		else {
				        			//On met à jour l'état de la caisse
				        			caisse.setIsFavorable(false);
				        			break;
				        		}
				        	}
				        }
				        
				        return "redirect:/pret/" + pret.getId();
			    }
		    	else {
		    		System.out.println("l'étudiant n'a pas encore cotisé");
		    		return "redirect:/prets";
		    	}
	    	}
	    	else {
	    		System.out.println("l'étudiant a déja un prêt en cours de traitement");
	    		return "redirect:/prets";
	    	}
        	
    }

    /**
     * Delete pret by its id.
     *
     * @param id
     * @return
     */
    @RequestMapping("pret/delete/{id}")
    public String delete(@PathVariable Long id) {
        pretService.deletePret(id);
        return "supprimes";
    }
    
    /**
     * List all prets Ordered by etat.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/decisions", method = RequestMethod.GET)
    public String listDecision(Model model) {
        model.addAttribute("elus", pretService.listAllPretsElu());
        model.addAttribute("prets", pretService.listAllPretsPret());
        model.addAttribute("autres", pretService.listAllPretsAutres());
        model.addAttribute("termines", pretService.listAllPretsTermine());
        System.out.println("Returning prets:");
        return "decision";
    }
    
    @RequestMapping(value = "pret/terminer/{id}", method = RequestMethod.GET)
    public String terminerPret(Optional<Pret> pret) {
    	//on récupère l'objet pret
    	if (pret.isPresent()) {
    		Pret lePret = pret.get();
    		Long id = lePret.getId();
    		
    		Optional<Pret> nouveauPret =  pretService.getPretById(id);
    		if (nouveauPret.isPresent()) {
    			Pret nouveauPret1 = nouveauPret.get();
    		
	    		//on met à jour l'état actuel de la caisse car l'étudiant a reçu la somme demandée
	        	double montantPret = nouveauPret1.getMontant();
	        	
	        	Caisse caisse = nouveauPret1.getCaisse();
	        	
	        	double nouveauMontantActuel =  caisse.getMontantActuel();
	        	nouveauMontantActuel -= montantPret;
	        	
	        	caisse.setMontantActuel(nouveauMontantActuel);
	        	
	        	caisseService.update(caisse);
	        	
	        	//changement de l'état du pret en termine
	        	nouveauPret1.setEtat("termine");
	        	pretService.update(nouveauPret1);
	        	
	        	
	        	return "redirect:/pret/" + nouveauPret1.getId();
    		}
    	}
		return "prets";
    	
        
    }
    
    //Supprimer un prêt, on ne change que son état 
    //Pour supprimer dans la base de données, on va utiliser: pret/delete/{id}
    @RequestMapping(value = "pret/supprimer/{id}")
    public String supprimerPret(Pret pret) {
    	Long id = pret.getId();
    	Optional<Pret> nouveauPret = pretService.getPretById(id);
    	Pret leNouveauPret = nouveauPret.get();
    	//changement de l'état du pret en supprime
    	leNouveauPret.setEtat("supprime");
    	pretService.update(leNouveauPret);
        return "redirect:/pret/" + leNouveauPret.getId();
        
    }
    
    @RequestMapping(value = "pret/restaurer/{id}")
    public String restaurerPret(Pret pret) {
    	Long id = pret.getId();
    	Optional<Pret> nouveauPret = pretService.getPretById(id);
    	Pret leNouveauPret = nouveauPret.get();
    	//changement de l'état du pret en supprime
    	leNouveauPret.setEtat("restaure");
    	pretService.update(leNouveauPret);
        return "redirect:/pret/" + leNouveauPret.getId();
        
    }
    
    @RequestMapping(value = "corbeille")
    public String corbeille(Model model) {
    	model.addAttribute("supprimes", pretService.listAllPretSupprimes());
        System.out.println("Returning prets supprimés:");
        return "supprimes";
        
    }

}

