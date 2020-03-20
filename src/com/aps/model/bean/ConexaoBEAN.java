package com.aps.model.bean;

import java.util.List;

public class ConexaoBEAN {

	private static String nome;
	private String senhaHash;
	private String cpf;
	private String rg;
	private String dataNasc;
	private String telefone;
	private String email;
	private String tituloElei;
	private String zona;
	private String secao;
	private String escolaridade;
	private String estadoCivil;
	private String filiacao;
	private String consPreten;
	private String consAtual;
	private byte[] imagem;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	private String pai;
	private String mae;
	private String conjugue;
	private String telConjugue;
	private String indiceEstado;
	private List<String> cidades;

	public static String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		ConexaoBEAN.nome = nome;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public String getSenhaHash() {
		return senhaHash;
	}

	public void setSenhaHash(String senhaHash) {
		this.senhaHash = senhaHash;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rG2) {
		this.rg = rG2;
	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String tel) {
		this.telefone = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTituloElei() {
		return tituloElei;
	}

	public void setTituloElei(String titEleit) {
		this.tituloElei = titEleit;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getSecao() {
		return secao;
	}

	public void setSecao(String secao) {
		this.secao = secao;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getFiliacao() {
		return filiacao;
	}

	public void setFiliacao(String filiacao) {
		this.filiacao = filiacao;
	}

	public String getConsPreten() {
		return consPreten;
	}

	public void setConsPreten(String consPreten) {
		this.consPreten = consPreten;
	}

	public String getConsAtual() {
		return consAtual;
	}

	public void setConsAtual(String consAtual) {
		this.consAtual = consAtual;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getConjugue() {
		return conjugue;
	}

	public void setConjugue(String conjugue) {
		this.conjugue = conjugue;
	}

	public String getTelConjugue() {
		return telConjugue;
	}

	public void setTelConjugue(String telConjugue2) {
		this.telConjugue = telConjugue2;
	}

	public String getIndiceEstado() {
		return indiceEstado;
	}

	public void setIndiceEstado(String indiceEstado) {
		this.indiceEstado = indiceEstado;
	}

	public List<String> getCidades() {
		return cidades;
	}

	public void setCidades(List<String> cidades) {
		this.cidades = cidades;
	}
}
