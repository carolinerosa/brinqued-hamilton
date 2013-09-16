package com.example.sobreviva;


import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import com.example.sobreviva.activity.MainActivity;
import com.example.sobreviva.multiplayer.cliente.ControleDeUsuariosCliente;
import com.example.sobreviva.multiplayer.cliente.DadosDoCliente;
import com.example.sobreviva.multiplayer.util.Conexao;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class MultiplayerGameView extends View implements Runnable{

	private final double  LONG_TIME_SLEEP = 1*1000;
	private final double  TIME_SLEEP = 50;
	private int contadorParaComecar = 0;
	private boolean comecarJogo = false;


	private Paint paintText;
	
	private Bolinha bola;
	private ControleDeUsuariosCliente tratadorDeDadosDoCliente;
	private DadosDoCliente dadosDoCliente;

	
	public MultiplayerGameView(){
		super(MainActivity.GetInstance());
		tratadorDeDadosDoCliente.setGame(this);
		this.tratadorDeDadosDoCliente = (ControleDeUsuariosCliente) MainActivity.GetInstance().getConexao().getTratador();
		this.tratadorDeDadosDoCliente.iniciarJogo();
		
		// envia estado atual do cliente para o servidor
		dadosDoCliente = new DadosDoCliente(MainActivity.GetInstance().getConexao(), (int)TIME_SLEEP);
		Thread threadDados = new Thread(dadosDoCliente);
		threadDados.start();
		
		
		bola = new Bolinha(MainActivity.GetInstance(), getHeight(), getWidth());
		
		paintText = new Paint();
		paintText.setColor(Color.BLACK);
		paintText.setTextSize(getWidth()/30);
		
		setFocusableInTouchMode(true);
		setClickable(true);
		setLongClickable(true);

		comecarJogo = false;
		contadorParaComecar = 0;
		Thread thread = new Thread(this);
		thread.start();
		
		
	}
	
	

	@Override
	public void run() {
		try {
			if (!comecarJogo) {
				Thread.sleep((long) LONG_TIME_SLEEP);
				contadorParaComecar++;
			} else {
				Thread.sleep((long) TIME_SLEEP);
			}
		} catch (InterruptedException e) {
			// Log.e(MainActivity.TAG, "interrupcao do run()");
		}
		if (comecarJogo) {
			update();
		}
		postInvalidate();
	}
	
	public void draw(Canvas canvas) {
		super.draw(canvas);
		contagemComecar(canvas);
		bola.draw(canvas);
	}
	
	
	private void update() {
		bola.update();
	}
	
	
	
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			bola.Action_Down();
		}

		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			bola.Action_Move();			
		}

		if (event.getAction() == MotionEvent.ACTION_UP) {
			bola.Action_Up();
		}

		return super.onTouchEvent(event);
	}
	
	
	public void contagemComecar(Canvas canvas){
	
		Paint paint; 
		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(getWidth()/2);
		if(this.tratadorDeDadosDoCliente.getJogadores() != null){
			
			ConcurrentHashMap<String, Integer> j = this.tratadorDeDadosDoCliente.getJogadores();
		
			Iterator<String> iterator = j.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
					canvas.drawText("" +  key , 10, (getHeight() / 5), paintText);
				canvas.drawText("Pontos :" + j.get(key), 10, (getHeight() / 8), paintText);
				postInvalidate();
			}
		}
		while( 6 -contadorParaComecar >= 1){
			canvas.drawText(6 -contadorParaComecar + "", getWidth()/2, getHeight()/2, paint);
			bola.draw(canvas);
		}
		comecarJogo = true;
	}
}
