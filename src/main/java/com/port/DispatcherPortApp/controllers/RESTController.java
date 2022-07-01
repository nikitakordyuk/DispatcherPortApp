package com.port.DispatcherPortApp.controllers;

import com.port.DispatcherPortApp.services.DocxCreation;
import com.port.DispatcherPortApp.services.GeneralService;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.http.HttpResponse;
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

        try(FileOutputStream docx = docxCreation.createDocx(print)) {

            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            response.addHeader("Content-Disposition", "attachment; filename=" + "sample.docx");
            response.setContentLength((int) Files.size(Path.of("output/createdWord2_.docx")));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");

            try (FileInputStream fileInputStream = new FileInputStream("output/createdWord2_.docx")) {
                OutputStream responseOutputStream = response.getOutputStream();
                int bytes;
                while ((bytes = fileInputStream.read()) != -1) {
                    responseOutputStream.write(bytes);
                }

                response.flushBuffer();
            }
        }

    }
}