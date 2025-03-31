package Camily.teste.intuitive.care.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Camily.teste.intuitive.care.service.DownloadAnexosService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/download")
public class DownloadAnexosController {

	@Autowired
	private DownloadAnexosService downloadService;

	@GetMapping("/zip")
	@CrossOrigin(origins = "http://localhost:8080")
	public void downloadZip(HttpServletResponse response) {

		List<String> pdfLinks = downloadService.buscarAnexos();
		downloadService.downloadZIP(pdfLinks, response);
	}
}
