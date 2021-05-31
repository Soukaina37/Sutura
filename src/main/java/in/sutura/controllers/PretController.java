package in.sutura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.sutura.entities.Pret;
import in.sutura.priority.CalculPriorite;
import in.sutura.services.PretService;

/**
 * Pret controller.
 */
@Controller
public class PretController {

    private PretService pretService;
    private CalculPriorite calculPriorite;
    @Autowired
    public void setPretService(PretService pretService) {
        this.pretService = pretService;
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
        model.addAttribute("pret", pretService.getPretById(id));
        return "pretshow";
    }

    // Afficher le formulaire de modification du Pret
    @RequestMapping("pret/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("pret", pretService.getPretById(id));
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
    	
    	int precision = pret.getCommentaire().length();
    	pret = CalculPriorite.Calcul(pret, precision);
    	
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

}

