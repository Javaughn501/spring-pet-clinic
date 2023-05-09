package udemy.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import udemy.spring.model.Owner;
import udemy.spring.services.OwnerService;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

//    @RequestMapping({"", "/index"})
//    public String listOwners(Model model) {
//
//
//        model.addAttribute("owners", ownerService.findAll());
//
//
//        return "owners/index";
//    }

    @GetMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processingFindForm(Owner owner, BindingResult result, Model model) {
        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        List<Owner> results = ownerService.findAllByLastNameLike("%"+ owner.getLastName() + "%");

        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            // 1 owner found
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            // multiple owners found
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }

    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {

        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        Owner owner = Owner.builder().build();
        model.addAttribute("owner", owner);
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/new")
    public String processCreationForm(@Validated Owner owner, BindingResult result) {

        if (result.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        } else {
            Owner saved = ownerService.save(owner);
            return "redirect:/owners/" + saved.getId();
        }

    }

    @GetMapping("{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
//        Owner owner = ownerService.findById(ownerId);
        model.addAttribute(ownerService.findById(ownerId));
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("{ownerId}/edit")
    public String processUpdateForm(@Validated Owner owner, BindingResult result, @PathVariable Long ownerId) {
        if (result.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        } else {
            owner.setId(ownerId);
            Owner saved = ownerService.save(owner);
            return "redirect:/owners/" + saved.getId();
        }
    }

}
