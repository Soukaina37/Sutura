package in.sutura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.sutura.entities.Don;
import in.sutura.services.DonService;

/**
 * Don controller.
 */
@Controller
public class DonController {

    private DonService donService;

    @Autowired
    public void setDonService(DonService donService) {
        this.donService = donService;
    }

    /**
     * List all dons.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/dons", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("dons", donService.listAllDons());
        System.out.println("Returning dons:");
        return "dons";
    }

    /**
     * View a specific don by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("don/{id}")
    public String showDon(@PathVariable Long id, Model model) {
        model.addAttribute("don", donService.getDonById(id));
        return "donshow";
    }

    // Afficher le formulaire de modification du Don
    @RequestMapping("don/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("don", donService.getDonById(id));
        return "donform";
    }

    /**
     * New don.
     *
     * @param model
     * @return
     */
    @RequestMapping("don/new")
    public String newDon(Model model) {
        model.addAttribute("don", new Don());
        return "donform";
    }

    /**
     * Save don to database.
     *
     * @param don
     * @return
     */
    @RequestMapping(value = "don", method = RequestMethod.POST)
    public String saveDon(Don don) {
        donService.saveDon(don);
        return "redirect:/don/" + don.getId();
    }

    /**
     * Delete don by its id.
     *
     * @param id
     * @return
     */
    @RequestMapping("don/delete/{id}")
    public String delete(@PathVariable Long id) {
        donService.deleteDon(id);
        return "redirect:/dons";
    }

}
