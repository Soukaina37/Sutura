package in.sutura.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.sutura.entities.Parametre;
import in.sutura.services.ParametreService;

@Controller
public class ParametreController {
	
	private ParametreService parametreService;
	
    @Autowired
    public void setParametreService(ParametreService parametreService) {
        this.parametreService = parametreService;
    }
	
	@RequestMapping(value = "/parametres", method = RequestMethod.GET)
    public String afficher(Model model) {
        model.addAttribute("parametre", parametreService.getParametresByPeriode(1));
        return "parametres";
    }
	
	@RequestMapping(value = "/parametres/update", method = RequestMethod.GET)
    public String modifier(Model model) {
		Optional<Parametre> parametre = parametreService.getParametreById((long) 1); 
	   	 
	   	 if( parametre.isPresent() ) {
	            model.addAttribute("parametre", parametre.get());
	   }
        return "parametreform";
    }
	
	/*
	 * enlever save et new, mettre dans la classe Parametre ou dans le main un objet qui sera créé avec 
	 * la création de la base de données 
	 * dans ce cas, enlever l'attribut id de Parametre et laisser période comme clé étrangère
	 */
	
	@RequestMapping("parametres/new")
    public String newParametre(Model model) {
        model.addAttribute("parametres", new Parametre());
        return "parametreform";
    }
	
	 @RequestMapping(value = "parametres/save", method = RequestMethod.POST)
	    public String saveParametres(Parametre parametres) {
		 	parametres.setId((long) 1);
	    	this.parametreService.saveParametre(parametres);
	        return "parametres";
	 }

}
