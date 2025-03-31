package Camily.teste.intuitive.care.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "operadora_ativa")
public class Operadora {

    @Id
    @Column(name = "CNPJ", nullable = false, unique = true, length = 14)
    private String cnpj;

    @Column(name = "registro_ANS", nullable = false, length = 20)
    private String registroAns;

    @Column(name = "razao_social", nullable = false, length = 255)
    private String razaoSocial;

    @Column(name = "nome_fantasia", length = 255)
    private String nomeFantasia;

    @Column(name = "modalidade", length = 50)
    private String modalidade;

    @Column(name = "logradouro", length = 255)
    private String logradouro;

    @Column(name = "numero", length = 255)
    private String numero;

    @Column(name = "complemento", length = 255)
    private String complemento;

    @Column(name = "bairro", length = 255)
    private String bairro;

    @Column(name = "cidade", length = 255)
    private String cidade;

    @Column(name = "UF", nullable = false, length = 2)
    private String uf;

    @Column(name = "CEP", length = 8)
    private String cep;

    @Column(name = "DDD", length = 3)
    private String ddd;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "fax", length = 15)
    private String fax;

    @Column(name = "endereco_eletronico", length = 255)
    private String enderecoEletronico;

    @Column(name = "representante", length = 255)
    private String representante;

    @Column(name = "cargo_representante", length = 255)
    private String cargoRepresentante;

    @Column(name = "regiao_de_comercializacao", length = 255)
    private String regiaoDeComercializacao;

    @Column(name = "data_registro_ANS")
    private Date dataRegistroAns;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRegistroAns() {
        return registroAns;
    }

    public void setRegistroAns(String registroAns) {
        this.registroAns = registroAns;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEnderecoEletronico() {
        return enderecoEletronico;
    }

    public void setEnderecoEletronico(String enderecoEletronico) {
        this.enderecoEletronico = enderecoEletronico;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getCargoRepresentante() {
        return cargoRepresentante;
    }

    public void setCargoRepresentante(String cargoRepresentante) {
        this.cargoRepresentante = cargoRepresentante;
    }

    public String getRegiaoDeComercializacao() {
        return regiaoDeComercializacao;
    }

    public void setRegiaoDeComercializacao(String regiaoDeComercializacao) {
        this.regiaoDeComercializacao = regiaoDeComercializacao;
    }

    public Date getDataRegistroAns() {
        return dataRegistroAns;
    }

    public void setDataRegistroAns(Date dataRegistroAns) {
        this.dataRegistroAns = dataRegistroAns;
    }
}
