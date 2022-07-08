package com.port.DispatcherPortApp.controllers;

import com.port.DispatcherPortApp.models.General;
import com.port.DispatcherPortApp.services.GeneralService;
import com.port.DispatcherPortApp.util.FieldsNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class GeneralController {
    private final GeneralService generalService;

    @Autowired
    public GeneralController(GeneralService generalService) {
        this.generalService = generalService;
    }

    @GetMapping()
    public String generalPage(Model model) {
        model.addAttribute("generals", generalService.generalList());

        return "general/index";
    }

    @GetMapping("/general/new")
    public String newPage(Model model) {
        model.addAttribute("general", new General());

        return "general/new";
    }

    @PostMapping("/general/new")
    public String addNew(@ModelAttribute @Valid General general, BindingResult bindingResult) {
        generalService.saveGeneral(general);

        return "redirect:/";
    }

    @GetMapping("/general/{id}")
    public String certainIdPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("general", generalService.findGeneralById(id));

        return "general/show";
    }

    @GetMapping("/general/{id}/edit")
    public String editPage(@PathVariable("id") long id, Model model, Authentication authentication) {
        model.addAttribute("general", generalService.findGeneralById(id));

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "admin/edit_admin";
        }

        return "general/edit";
    }

    @PatchMapping("/general/{id}/edit")
    public String editRequest(@PathVariable("id") long id, @ModelAttribute General general) {
        generalService.updateById(id, general);

        return "redirect:/";
    }

    @DeleteMapping("/general/{id}")
    public String delete(@PathVariable long id) {
        generalService.deleteById(id);

        return "redirect:/";
    }

    @GetMapping("/general/search")
    public String search(Model model, @ModelAttribute("general") General general) {
        model.addAttribute("generals", generalService.generalList());
        model.addAttribute("searchResult", Collections.emptyList());

        ArrayList<Object> list = new ArrayList<>(Arrays.asList(FieldsNames.fieldsNames().keySet().toArray()));
        model.addAttribute("options", list);

        return "general/search";
    }

    @PostMapping("/general/search")
    public String search(@RequestParam("search") String search,
                         @RequestParam("option") Object option,
                         Model model) {
        ArrayList<Object> list = new ArrayList<>(Arrays.asList(FieldsNames.fieldsNames().keySet().toArray()));
        model.addAttribute("options", list);

        String optionEng = FieldsNames.fieldsNames().get(option.toString());

        List<General> searchResult = generalService.search(optionEng, search);
        model.addAttribute("searchResult", searchResult);

        model.addAttribute("numbers",
                searchResult
                        .stream()
                        .map(General::getId)
                        .collect(Collectors.toList()));

        return "general/search";
    }

    @GetMapping("/general/print")
    public String print(Model model, @ModelAttribute("general") General general) {
        model.addAttribute("generals",
                generalService.generalList()
                .stream()
                .map(General::getCarNumber)
                .collect(Collectors.toList()));

        return "general/print";
    }
}
