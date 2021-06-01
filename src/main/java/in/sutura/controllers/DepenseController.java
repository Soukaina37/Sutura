package in.sutura.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.sutura.entities.Depense;
import in.sutura.services.DepenseService;

/**
 * Depense controller.
 */
@Controller
public class DepenseController {

    private DepenseService depenseService;

    @Autowired
    public void setDepenseService(DepenseService depenseService) {
        this.depenseService = depenseService;
    }

    /**
     * List all depenses.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/depenses", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("depenses", depenseService.listAllDepenses());
        System.out.println("Returning depenses:");
        return "depenses";
    }

    /**
     * View a specific depense by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("depense/{id}")
    public String showDepense(@PathVariable Long id, Model model) {
    	Optional<Depense> depense = depenseService.getDepenseById(id);
   	 
   	 if( depense.isPresent() ) {
            model.addAttribute("depense", depense.get());
   }
   	 return "depenseshow";
    }

    // Afficher le formulaire de modification du Depense
    @RequestMapping("depense/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
    	Optional<Depense> depense = depenseService.getDepenseById(id);
      	 
      	 if( depense.isPresent() ) {
               model.addAttribute("depense", depense.get());
      }
      	 return "depenseform";
       }

    /**
     * New depense.
     *
     * @param model
     * @return
     */
    @RequestMapping("depense/new")
    public String newDepense(Model model) {
        model.addAttribute("depense", new Depense());
        return "depenseform";
    }

    /**
     * Save depense to database.
     *
     * @param depense
     * @return
     */
    @RequestMapping(value = "depense", method = RequestMethod.POST)
    public String saveDepense(Depense depense) {
        depenseService.saveDepense(depense);
        return "redirect:/depense/" + depense.getId();
    }

    /**
     * Delete depense by its id.
     *
     * @param id
     * @return
     */
    @RequestMapping("depense/delete/{id}")
    public String delete(@PathVariable Long id) {
        depenseService.deleteDepense(id);
        return "redirect:/depenses";
    }

}
