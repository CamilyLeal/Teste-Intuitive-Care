package Camily.teste.intuitive.care.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class DownloadAnexosService {

	private final String URL_ANS = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";

	public List<String> buscarAnexos() {

		List<String> pdfLinks = new ArrayList<>();

		try {
			Document doc = Jsoup.connect(URL_ANS).get();

			Elements links = doc.select("a[href]");

			for (Element link : links) {
				String href = link.attr("abs:href");
				String text = link.text();

				if ((text.contains("Anexo I") || text.contains("Anexo II")) && href.endsWith(".pdf")) {
					pdfLinks.add(href);
				}
			}
		} catch (IOException e) {
			System.err.println("Erro ao acessar a página: " + e.getMessage());
		}

		return pdfLinks;
	}

	public void downloadZIP(List<String> pdfLinks, HttpServletResponse response) {
	    response.setContentType("application/zip");

	    response.setHeader("Content-Disposition", "attachment; filename=\"anexos.zip\"");

	    try (OutputStream out = response.getOutputStream(); ZipOutputStream zipOut = new ZipOutputStream(out)) {
	        for (String pdfUrl : pdfLinks) {
	            String fileName = pdfUrl.substring(pdfUrl.lastIndexOf("/") + 1);

	            URL url = new URL(pdfUrl);
	            try (InputStream inputStream = url.openStream()) {
	                ZipEntry zipEntry = new ZipEntry(fileName);
	                zipOut.putNextEntry(zipEntry);

	                byte[] buffer = new byte[1024];
	                int bytesRead;
	                while ((bytesRead = inputStream.read(buffer)) != -1) {
	                    zipOut.write(buffer, 0, bytesRead);
	                }
	                zipOut.closeEntry();
	            } catch (IOException e) {
	                System.err.println("Erro ao baixar o arquivo: " + pdfUrl);
	                e.printStackTrace();
	            }
	        }

	        zipOut.finish();
	        System.out.println("Arquivo ZIP enviado");
	    } catch (IOException e) {
	        System.err.println("Erro ao criar ou enviar o arquivo ZIP: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

}
