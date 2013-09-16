package com.example.sobreviva.multiplayer.cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.example.sobreviva.MultiplayerGameView;
import com.example.sobreviva.activity.MainActivity;
import com.example.sobreviva.multiplayer.util.Conexao;
import com.example.sobreviva.multiplayer.util.DepoisDeReceberDados;
import com.example.sobreviva.multiplayer.util.Protocolo;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;


public class ControleDeUsuariosCliente implements DepoisDeReceberDados {

	private ConcurrentHashMap<String, Integer> jogadores;
	private MultiplayerGameView game;

	public ConcurrentHashMap<String, Integer> getJogadores() {
		return jogadores;
	}
	

	public ControleDeUsuariosCliente() {
		jogadores = new ConcurrentHashMap<String, Integer>();
		
	}


	public void execute(Conexao origem, String linha) {
		
		Log.e("ReciboCliente", linha);
		
		
		if (linha.startsWith(Protocolo.PROTOCOL_UPDATE)) {
			
			String[] lista = linha.split(":");
			updateScore(origem, lista[1]);
		}
		
		if (linha.startsWith(Protocolo.PROTOCOL_START)) {
			iniciarJogo();
		}
		
	}
	
	// recebe do servidor no formato : nome,x,y;nome,x,y
	public void updateScore(Conexao origem, String linha) {
		
		String[] separado = linha.split(",");
		String nome = separado[0];
		int pontos = Integer.parseInt(separado[1]);

		if (jogadores.get(nome) == null) {
			
			jogadores.put(nome, pontos);
		} else {
			jogadores.replace(nome, pontos);
		}
	}
	
	public void setGame(MultiplayerGameView game){
		this.game = game;
	}
	
	public void iniciarJogo(){
		
		MainActivity.GetInstance().switchView(new MultiplayerGameView());
	}
	
}

