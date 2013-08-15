package com.example.sobreviva.multiplayer.server;

public class Cliente {

	private String nome;
	public int pontuacao;
	
	public Cliente(String nome, int pontuacao){
		this.nome = nome;
		this.pontuacao = pontuacao;
	}
	
	public String getNome(){
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public int getPontuacao(){
		return pontuacao;
	}
	
	public void setPontuacao(int pontuacao){
		this.pontuacao = pontuacao;
	}

	public String toStringCSV() {
		
		return nome+","+pontuacao;
	}
	
}
