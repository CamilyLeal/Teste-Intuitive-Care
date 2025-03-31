package Camily.teste.intuitive.care.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Camily.teste.intuitive.care.entity.Operadora;
import Camily.teste.intuitive.care.service.OperadoraService;

@RestController
public class OperadoraController {

    @Autowired
    private OperadoraService operadoraService;

    @GetMapping("/operadoras/buscar")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<String>> buscarOperadora(@RequestParam(name = "term") String term) {
        List<Operadora> operadoras = operadoraService.buscarOperadoras(term);

        List<String> resultado = operadoras.stream()
            .map(operadora -> {
                StringBuilder sb = new StringBuilder();
                sb.append("Operadora: ").append(operadora.getRazaoSocial()).append("\n")
                  .append("CNPJ: ").append(operadora.getCnpj()).append("\n") 
                  .append("Endereço: ").append(operadora.getLogradouro()).append(", ")
                  .append(operadora.getNumero()).append(" - ")
                  .append(operadora.getBairro()).append(" - ")
                  .append(operadora.getCidade()).append(" - ")
                  .append(operadora.getUf()).append(" - ")
                  .append(operadora.getClass()).append("\n")
                  .append("Telefone: ").append(operadora.getTelefone()).append("\n")
                  .append("E-mail: ").append(operadora.getEnderecoEletronico()).append("\n")
                  .append("Representante: ").append(operadora.getRepresentante()).append("\n")
                  .append("Cargo Representante: ").append(operadora.getCargoRepresentante()).append("\n")
                  .append("Data de Registro ANS: ").append(operadora.getDataRegistroAns()).append("\n")
                  .append("----------------------------------------\n");
                return sb.toString();
            })
            .collect(Collectors.toList());

        // Retorna a lista de resultados encontrados
        return ResponseEntity.ok(resultado);
    }


}
