package in.sutura.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.sutura.entities.Caisse;
import in.sutura.entities.Pret;
import in.sutura.services.CaisseService;
import in.sutura.services.PretService;

/**
 * Pret controller.
 */
@Controller
public class PretController {

    private PretService pretService;
    //private CalculPriorite calculPriorite;
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
    	//CALCUL DE LA PRIORITE DU PRET
    	
    	//appel de la méthode globale
    	double priorite = pretService.calcul_priorite(pret);
    	//puis on met la valeur dans l'objet
    	pret.setPriorite(priorite);
    	//ensuite on fait le classement du pret
    	pretService.classement_priorite(pret);
    	//enfin on persiste l'objet
    	pretService.savePret(pret);
        return "redirect:/pret/" + pret.getId();
        
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
        return "redirect:/prets";
    }
    
    @RequestMapping(value = "terminerpret", method = RequestMethod.POST)
    public String terminerPret(Pret pret) {
    	double montantActuelPret = pret.getMontant();
    	Caisse caisse = pret.getCaisse();
    	
    	double nouveauMontantActuel =  caisse.getMontantActuel();
    	nouveauMontantActuel -= montantActuelPret;
    	
    	caisse.setMontantActuel(nouveauMontantActuel);
    	
    	caisseService.update(caisse);
    	
    	//changement de l'état du pret en termine
    	pret.setEtat("termine");
    	pretService.update(pret);
    	
        return "redirect:/pret/" + pret.getId();
        
    }

}

