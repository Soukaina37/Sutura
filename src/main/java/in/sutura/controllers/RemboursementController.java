package in.sutura.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.sutura.entities.Remboursement;
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
            model.addAttribute("caisse", remboursement.get());
   	 }
   	 return "remboursementshow";
    }

    // Afficher le formulaire de modification du Remboursement
    @RequestMapping("remboursement/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
    	Optional<Remboursement> remboursement = remboursementService.getRemboursementById(id);
      	 
      	 if( remboursement.isPresent() ) {
               model.addAttribute("caisse", remboursement.get());
      	 }
      	 return "remboursementshow";
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
