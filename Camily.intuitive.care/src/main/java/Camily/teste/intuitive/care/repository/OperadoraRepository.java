package Camily.teste.intuitive.care.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Camily.teste.intuitive.care.entity.Operadora;

@Repository
public interface OperadoraRepository extends JpaRepository<Operadora, String> {

    List<Operadora> findByRazaoSocialContainingIgnoreCaseOrNomeFantasiaContainingIgnoreCaseOrCnpjContainingIgnoreCase(
            String razaoSocial, String nomeFantasia, String cnpj);
}
