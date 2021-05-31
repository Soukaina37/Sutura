package in.sutura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import in.sutura.services.AdministrateurService;
import in.sutura.services.CotisationService;
import in.sutura.services.DepenseService;
import in.sutura.services.DonService;
import in.sutura.services.EtudiantService;
import in.sutura.services.PretService;
import in.sutura.services.RemboursementService;

/**
 * Homepage controller.
 */
@Controller
public class IndexController {
	
	  private CotisationService cotisationService;

	    @Autowired
	    public void setCotisationService(CotisationService cotisationService) {
	        this.cotisationService = cotisationService;
	    }
	    private DonService donService;

	    @Autowired
	    public void setDonService(DonService donService) {
	        this.donService = donService;
	    }
	    private RemboursementService remboursementService;

	    @Autowired
	    public void setRemboursementService(RemboursementService remboursementService) {
	        this.remboursementService = remboursementService;
	    }
	    private PretService pretService;

	    @Autowired
	    public void setPretService(PretService pretService) {
	        this.pretService = pretService;
	    }
	    private DepenseService depenseService;

	    @Autowired
	    public void setDepenseService(DepenseService depenseService) {
	        this.depenseService = depenseService;
	    }
	    private EtudiantService etudiantService;

	    @Autowired
	    public void setEtudiantService(EtudiantService etudiantService) {
	        this.etudiantService = etudiantService;
	    }
	    private AdministrateurService administrateurService;

	    @Autowired
	    public void setAdministrateurService(AdministrateurService administrateurService) {
	        this.administrateurService = administrateurService;
	    }
    @RequestMapping("/")
    String index(Model model) {
        model.addAttribute("cotisationCount", cotisationService.countCotisations());
        model.addAttribute("donCount", donService.countDons());
        model.addAttribute("depenseCount", depenseService.countDepenses());
        model.addAttribute("pretCount", pretService.countPrets());
        model.addAttribute("remboursementCount", remboursementService.countRemboursements());
        model.addAttribute("etudiantCount", etudiantService.countEtudiants());
        model.addAttribute("administrateurs", administrateurService.listAllAdministrateurs());

        return "index";
    }

}
