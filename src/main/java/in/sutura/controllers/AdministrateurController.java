package in.sutura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.sutura.entities.Administrateur;
import sutura.in.services.AdministrateurService;

/**
 * Administrateur controller.
 */
@Controller
public class AdministrateurController { 

    private AdministrateurService administrateurService;

    @Autowired
    public void setAdministrateurService(AdministrateurService administrateurService) {
        this.administrateurService = administrateurService;
    }

    /**
     * List all administrateurs.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/administrateurs", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("administrateurs", administrateurService.listAllAdministrateurs());
        System.out.println("Returning administrateurs:");
        return "administrateurs";
    }

    /**
     * View a specific administrateur by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("administrateur/{id}")
    public String showAdministrateur(@PathVariable Long id, Model model) {
        model.addAttribute("administrateur", administrateurService.getAdministrateurById(id));
        return "administrateurshow";
    }

    // Afficher le formulaire de modification du Administrateur
    @RequestMapping("administrateur/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("administrateur", administrateurService.getAdministrateurById(id));
        return "administrateurform";
    }

    /**
     * New administrateur.
     *
     * @param model
     * @return
     */
    @RequestMapping("administrateur/new")
    public String newAdministrateur(Model model) {
        model.addAttribute("administrateur", new Administrateur());
        return "administrateurform";
    }

    /**
     * Save administrateur to database.
     *
     * @param administrateur
     * @return
     */
    @RequestMapping(value = "administrateur", method = RequestMethod.POST)
    public String saveAdministrateur(Administrateur administrateur) {
        administrateurService.saveAdministrateur(administrateur);
        return "redirect:/administrateur/" + administrateur.getId();
    }

    /**
     * Delete administrateur by its id.
     *
     * @param id
     * @return
     */
    @RequestMapping("administrateur/delete/{id}")
    public String delete(@PathVariable Long id) {
        administrateurService.deleteAdministrateur(id);
        return "redirect:/administrateurs";
    }

}
