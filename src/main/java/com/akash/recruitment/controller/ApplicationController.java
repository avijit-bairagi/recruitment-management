package com.akash.recruitment.controller;

import com.akash.recruitment.dto.ApplicationDTO;
import com.akash.recruitment.dto.FileStatus;
import com.akash.recruitment.service.ApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("applications", applicationService.getAll());

        return "application/index";
    }

    @RequestMapping(value = "/index/{fileId}", method = RequestMethod.GET)
    public String indexFileId(Model model, @PathVariable(name = "fileId") Long fileId) {

        model.addAttribute("applications", applicationService.getAllByFileId(fileId));

        return "application/index";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable(name = "id") Long id) {

        model.addAttribute("applicationInfo", applicationService.findById(id));
        model.addAttribute("statusList", FileStatus.values());

        return "application/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editRequest(@ModelAttribute ApplicationDTO file) {

        applicationService.update(file);

        return "redirect:/application/index";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id") Long id) {

        applicationService.delete(id);

        return "redirect:/application/index";
    }
}
