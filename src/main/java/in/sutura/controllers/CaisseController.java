package in.sutura.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.sutura.entities.Caisse;
import in.sutura.entities.Etudiant;
import in.sutura.services.CaisseService;
/**
 * Caisse controller.
 */
@Controller
public class CaisseController {
	 private CaisseService caisseService;

	    @Autowired
	    public void setCaisseService(CaisseService caisseService) {
	        this.caisseService = caisseService;
	    }

	    /**
	     * List all caisses.
	     *
	     * @param model
	     * @return
	     */
	    @RequestMapping(value = "/caisses", method = RequestMethod.GET)
	    public String list(Model model) {
	        model.addAttribute("caisses", caisseService.listAllCaisses());
	        System.out.println("Returning caisses:");
	        return "caisses";
	    }

	    /**
	     * View a specific caisse by its id.
	     *
	     * @param id
	     * @param model
	     * @return
	     */
	    @RequestMapping("caisse/{id}")
	    public String showCaisse(@PathVariable Long id, Model model) {
	    	Optional<Caisse> caisse = caisseService.getCaisseById(id);
	    	 
	    	 if( caisse.isPresent() ) {
	             model.addAttribute("caisse", caisse.get());
	    }
	    	 return "caisseshow";
	    }

	    // Afficher le formulaire de modification de la Caisse
	    @RequestMapping("caisse/edit/{id}")
	    public String edit(@PathVariable Long id, Model model) {
	    	Optional<Caisse> caisse = caisseService.getCaisseById(id);
	    	 
	    	 if( caisse.isPresent() ) {
	             model.addAttribute("caisse", caisse.get());
	    }
	    	 return "caisseform";
	    }

	    /**
	     * New caisse.
	     *
	     * @param model
	     * @return
	     */
	    @RequestMapping("caisse/new")
	    public String newCaisse(Model model) {
	        model.addAttribute("caisse", new Caisse());
	        return "caisseform";
	    }

	    /**
	     * Save caisse to database.
	     *
	     * @param caisse
	     * @return
	     */
	    @RequestMapping(value = "caisse", method = RequestMethod.POST)
	    public String saveCaisse(Caisse caisse) {
	    	caisseService.saveCaisse(caisse);
	        return "redirect:/caisse/" + caisse.getId();
	    }

	    /**
	     * Delete caisse by its id.
	     *
	     * @param id
	     * @return
	     */
	    @RequestMapping("caisse/delete/{id}")
	    public String delete(@PathVariable Long id) {
	    	caisseService.deleteCaisse(id);
	        return "redirect:/caisses";
	    }

}
