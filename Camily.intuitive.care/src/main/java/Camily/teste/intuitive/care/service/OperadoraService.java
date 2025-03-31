package Camily.teste.intuitive.care.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Camily.teste.intuitive.care.entity.Operadora;
import Camily.teste.intuitive.care.repository.OperadoraRepository;

@Service
public class OperadoraService {

    @Autowired
    private OperadoraRepository operadoraRepository;

    public List<Operadora> buscarOperadoras(String term) {
        return operadoraRepository.findByRazaoSocialContainingIgnoreCaseOrNomeFantasiaContainingIgnoreCaseOrCnpjContainingIgnoreCase(term, term, term);
    }


}
