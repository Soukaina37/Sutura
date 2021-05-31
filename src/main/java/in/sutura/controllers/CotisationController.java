package in.sutura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.sutura.entities.Cotisation;
import in.sutura.services.CotisationService;

/**
 * Cotisation controller.
 */
@Controller
public class CotisationController {

    private CotisationService cotisationService;

    @Autowired
    public void setCotisationService(CotisationService cotisationService) {
        this.cotisationService = cotisationService;
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
        model.addAttribute("cotisation", cotisationService.getCotisationById(id));
        return "cotisationshow";
    }

    // Afficher le formulaire de modification du Cotisation
    @RequestMapping("cotisation/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("cotisation", cotisationService.getCotisationById(id));
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
        cotisationService.saveCotisation(cotisation);
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

