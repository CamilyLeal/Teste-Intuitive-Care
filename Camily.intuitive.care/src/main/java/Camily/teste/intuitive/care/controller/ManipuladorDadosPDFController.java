package Camily.teste.intuitive.care.controller;

import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Camily.teste.intuitive.care.service.ManipuladorDadosPDFService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/pdf")
public class ManipuladorDadosPDFController {
    private final ManipuladorDadosPDFService pdfService;

    @Autowired
    public ManipuladorDadosPDFController(ManipuladorDadosPDFService pdfService) {
        this.pdfService = pdfService;
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/extract")
    public ResponseEntity<String> extractData(HttpServletResponse response) {
        try {
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=Teste_CamilyLeal.zip");

            OutputStream outputStream = response.getOutputStream();

            pdfService.extrairProcessarPDF(outputStream);

            response.flushBuffer();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro durante o processamento e compactação do arquivo ZIP!");
        }
    }
}
