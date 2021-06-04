package in.sutura.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.sutura.entities.Etudiant;
import in.sutura.services.EtudiantService;

/**
 * Etudiant controller.
 */
@Controller
public class EtudiantController {

    private EtudiantService etudiantService;

    @Autowired
    public void setEtudiantService(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    /**
     * List all etudiants.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/etudiants", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("etudiants", etudiantService.listAllEtudiants());
        System.out.println("Returning etudiants:");
        return "etudiants";
    }

    /**
     * View a specific etudiant by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("etudiant/{id}")
    public String showEtudiant(@PathVariable Long id, Model model) {
    	 Optional<Etudiant> etudiant = etudiantService.getEtudiantById(id);
    	 
    	 if( etudiant.isPresent() ) {
             model.addAttribute("etudiant", etudiant.get());
    }
    	 return "etudiantshow";
    }

    // Afficher le formulaire de modification du Etudiant
    @RequestMapping("etudiant/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
    	Optional<Etudiant> etudiant = etudiantService.getEtudiantById(id);
   	 
   	 if( etudiant.isPresent() ) {
            model.addAttribute("etudiant", etudiant.get());
            model.addAttribute("flag", "il faut afficher l'ID");
   }
   	 return "etudiantform";
   }

    /**
     * New etudiant.
     *
     * @param model
     * @return
     */
    @RequestMapping("etudiant/new")
    public String newEtudiant(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        return "etudiantform";
    }

    /**
     * Save etudiant to database.
     *
     * @param etudiant
     * @return
     */
    @RequestMapping(value = "etudiant", method = RequestMethod.POST)
    public String saveEtudiant(Etudiant etudiant) {
        etudiantService.saveEtudiant(etudiant);
        return "redirect:/etudiant/" + etudiant.getId();
    }

    /**
     * Delete etudiant by its id.
     *
     * @param id
     * @return
     */
    @RequestMapping("etudiant/delete/{id}")
    public String delete(@PathVariable Long id) {
        etudiantService.deleteEtudiant(id);
        return "redirect:/etudiants";
    }

}

