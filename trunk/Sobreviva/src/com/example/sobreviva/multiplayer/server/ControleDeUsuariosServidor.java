package com.example.sobreviva.multiplayer.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.example.sobreviva.multiplayer.util.Conexao;
import com.example.sobreviva.multiplayer.util.Const;
import com.example.sobreviva.multiplayer.util.DepoisDeReceberDados;
import com.example.sobreviva.multiplayer.util.Protocolo;

import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;

public class ControleDeUsuariosServidor implements DepoisDeReceberDados {

	// notar que este tipo de hashmap eh sincronizado
	// suportando acessos de multiplos threads
	private ConcurrentHashMap<String, Cliente> jogadores;
	
	
	
	public ConcurrentHashMap<String, Cliente> getJogadoresList(){
		return this.jogadores;
	}
	
	
	public ControleDeUsuariosServidor() {
		jogadores = new ConcurrentHashMap<String, Cliente>();
	}
	
	
	
	// comandos possiveis dos clientes
	// ID,nome do usuario,x,y
	// MOVE,x,y

	public void execute(Conexao origem, String linha) {

		Log.i(Const.TAG, "<<" + linha);

		if (linha.startsWith(Protocolo.PROTOCOL_ID)) {
			adicionaNovoUsuario(origem, linha);
		}
		
		if (linha.startsWith(Protocolo.PROTOCOL_UPDATE)) {
			updateScore(origem, linha);
		}
		
		informaTodosUsuarios(origem);
		
		
	}

	private void informaTodosUsuarios(Conexao origem) {

		//StringBuffer buffer = new StringBuffer();
		Iterator iterator = jogadores.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			Cliente jogador = jogadores.get(key);
			
			origem.write(Protocolo.PROTOCOL_UPDATE + jogador.toStringCSV());
		}

		//origem.write(Protocolo.PROTOCOL_UPDATE + buffer.toString());
	}


	private void adicionaNovoUsuario(Conexao origem, String linha) {
		String[] array = linha.split(",");
		String nome = array[1];

		origem.setId(nome);
		Cliente jogador = new Cliente(nome, 0);
		jogadores.put(nome, jogador);
		
	}
	
	private void updateScore(Conexao origem, String linha){
		String[] array = linha.split(",");
		//String nome = array[1];
		int pontos = Integer.parseInt(array[2]);
		
		jogadores.get(origem.getId()).setPontuacao(pontos);
	}
	
	private void configuracaoPrimarias(Conexao origem) {
		
		//origem.write(Protocolo.PROTOCOL_MAPA + MapaServer.toStringCSV());
		
	}

}
