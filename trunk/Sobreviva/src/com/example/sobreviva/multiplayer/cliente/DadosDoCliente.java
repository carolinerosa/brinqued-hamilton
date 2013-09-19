package com.example.sobreviva.multiplayer.cliente;

import com.example.sobreviva.multiplayer.util.Conexao;
import com.example.sobreviva.multiplayer.util.ElMatador;
import com.example.sobreviva.multiplayer.util.Killable;
import com.example.sobreviva.multiplayer.util.Protocolo;

import android.graphics.Point;
import android.util.Log;


public class DadosDoCliente implements Runnable, Killable {

	private Conexao cliente;
	private int updateTime;

	private int pontuacao = 0;
	private String nome;
	private boolean ativo = true;
	private boolean comecajogo = false;

	public DadosDoCliente(Conexao cliente, int updateTime) {
		this.cliente = cliente;
		this.nome = cliente.getId();
		this.updateTime = updateTime;
		ElMatador.getInstance().add(this);
		cliente.write(Protocolo.PROTOCOL_ID + "," + nome);
	}

	public void run() {
		while (ativo) {
			try {
				Thread.sleep(updateTime);
			} catch (InterruptedException e) {
				//Log.e(ConectActivity.TAG, "interrupcao do run()");
			}
			if (comecajogo) {
				cliente.write(Protocolo.PROTOCOL_UPDATE + "," + nome + ","
						+ pontuacao);
			}
		}

	}
	
	public void camecar(){
		comecajogo = true;
	}
	
	public String getNome(){
		return nome;
	}
	
	public void setPontos(int pontos){
		this.pontuacao = pontos; 
	}
	

	public void killMeSoftly() {
		ativo = false;
	}
	
}
