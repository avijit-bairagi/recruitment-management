package com.akash.recruitment.controller;

import com.akash.recruitment.excepion.CustomException;
import com.akash.recruitment.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("files", fileService.getAll());

        return "file/index";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload() {

        return "file/upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadRequest(@RequestParam("file") MultipartFile file) throws CustomException {

        fileService.upload(file);

        return "redirect:/file/index";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id") Long id) {

        fileService.delete(id);

        return "redirect:/file/index";
    }
}
