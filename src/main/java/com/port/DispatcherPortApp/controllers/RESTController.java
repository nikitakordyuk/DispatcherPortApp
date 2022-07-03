package com.port.DispatcherPortApp.controllers;

import com.port.DispatcherPortApp.services.GeneralService;
import com.port.DispatcherPortApp.util.DocxCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
public class RESTController {
    private final GeneralService generalService;

    @Autowired
    public RESTController(GeneralService generalService) {
        this.generalService = generalService;
    }

    @PostMapping("/general/print")
    public void print(@RequestParam("carNumber") List<String> print, HttpServletResponse response) throws IOException {
        DocxCreation docxCreation = new DocxCreation(generalService);

        docxCreation.createDocx(print);
        createResponse(response);
    }

    @PostMapping("/general/print-all")
    public void printAll(HttpServletResponse response) throws IOException {
        DocxCreation docxCreation = new DocxCreation(generalService);

        docxCreation.createDocxFromAll(generalService.generalList());
        createResponse(response);
    }

    @PostMapping("/general/print-result-from-search")
    public void printResultFromSearch(@RequestParam("res") List<Long> printFromSearch,
                                      HttpServletResponse response) throws IOException {
        DocxCreation docxCreation = new DocxCreation(generalService);

        docxCreation.createDocxFromLong(printFromSearch);

        createResponse(response);
    }

    private static void createResponse(HttpServletResponse response) throws IOException {
        Path generatedWordPath = Path.of("output/createdWord2_.docx");

        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        response.addHeader("Content-Disposition", "attachment; filename=" + "sample.docx");
        response.setContentLength((int) Files.size(generatedWordPath));
        response.setHeader("Set-Cookie", "fileDownload=true; path=/");

        Files.copy(generatedWordPath, response.getOutputStream());

        response.flushBuffer();
    }
}
