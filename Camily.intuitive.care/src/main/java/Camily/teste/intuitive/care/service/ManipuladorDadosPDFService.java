package Camily.teste.intuitive.care.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;

import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;
import technology.tabula.extractors.BasicExtractionAlgorithm;

@Service
public class ManipuladorDadosPDFService {
    private final DownloadAnexosService downloadAnexosService;

    @Autowired
    public ManipuladorDadosPDFService(DownloadAnexosService downloadAnexosService) {
        this.downloadAnexosService = downloadAnexosService;
    }

    public void extrairProcessarPDF(OutputStream outputStream) {
        try {
            List<String> pdfLinks = downloadAnexosService.buscarAnexos();
            String anexoI = pdfLinks.stream().filter(link -> link.contains("Anexo_I")).findFirst()
                    .orElseThrow(() -> new RuntimeException("Arquivo Anexo_I não encontrado!"));

            URL url = new URL(anexoI);
            InputStream inputStream = url.openStream();

            gerarCSVNoZIP(inputStream, outputStream);

        } catch (Exception e) {
            System.err.println("Erro durante a extração e processamento do PDF: " + e.getMessage());
        }
    }

    public void gerarCSVNoZIP(InputStream inputStream, OutputStream outputStream) throws Exception {
        PDDocument document = null;
        ObjectExtractor extractor = null;
        boolean startRecording = false;

        try (ZipOutputStream zipOut = new ZipOutputStream(outputStream);
             CSVWriter writer = new CSVWriter(new OutputStreamWriter(zipOut))) {

            ZipEntry zipEntry = new ZipEntry("output.csv");
            zipOut.putNextEntry(zipEntry);

            String[] header = { "PROCEDIMENTO", "RN (alteração)", "VIGÊNCIA", "Seg. Odontológica", "Seg. Ambulatorial", "HCO", "HSO", "REF", "PAC", "DUT", "SUBGRUPO", "GRUPO", "CAPÍTULO" };
            writer.writeNext(header);

            BasicExtractionAlgorithm bea = new BasicExtractionAlgorithm();

            document = PDDocument.load(inputStream);
            extractor = new ObjectExtractor(document);

            for (int i = 1; i <= document.getNumberOfPages(); i++) {
                Page page = extractor.extract(i);
                List<Table> tables = bea.extract(page);

                for (Table table : tables) {
                    List<List<RectangularTextContainer>> rows = table.getRows();
                    for (List<RectangularTextContainer> row : rows) {
                        String[] rowData = row.stream().map(cell -> cell.getText().trim()).toArray(String[]::new);

                        if (rowData.length > 0 && rowData[0].equalsIgnoreCase("ACONSELHAMENTO GENÉTICO")) {
                            startRecording = true;
                        }

                        if (startRecording) {
                            String[] formattedRow = new String[header.length];

                            for (int j = 0; j < formattedRow.length; j++) {
                                formattedRow[j] = (j < rowData.length && !rowData[j].isEmpty()) ? rowData[j] : "";
                            }

                            writer.writeNext(formattedRow);
                        }
                    }
                }
            }

        } catch (IOException e) {
            throw new Exception("Erro ao processar o PDF com Tabula: " + e.getMessage(), e);
        } finally {
            if (extractor != null) {
                extractor.close();
            }
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
