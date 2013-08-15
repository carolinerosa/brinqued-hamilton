package com.example.sobreviva;


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

	private final double  TIME_SLEEP = 1*1000;
	private int contadorParaComecar = 0;
	private boolean comecarJogo = false;
	
	private int Recorde = 0;
	private boolean comecar = false;
	private float textSize = 0;
	private Paint paintText;
	
	private Bolinha bola;
	private ControleDeUsuariosCliente tratadorDeDadosDoCliente;
	private DadosDoCliente dadosDoCliente;

	
	public MultiplayerGameView(Context context, Conexao cliente,
			ControleDeUsuariosCliente tratadorDeDadosDoCliente){
		super(context);
		
		this.tratadorDeDadosDoCliente = tratadorDeDadosDoCliente;
		this.tratadorDeDadosDoCliente.iniciarJogo();
		
		// envia estado atual do cliente para o servidor
		dadosDoCliente = new DadosDoCliente(cliente, (int)TIME_SLEEP);
		Thread threadDados = new Thread(dadosDoCliente);
		threadDados.start();
		
		
		bola = new Bolinha(context, getHeight(), getWidth());
		
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
		while (true) {
			try {
				Thread.sleep((long) TIME_SLEEP);
				while(!comecarJogo){
					contadorParaComecar++;
				}
			} catch (InterruptedException e) {
				//Log.e(MainActivity.TAG, "interrupcao do run()");
			}
			while(comecarJogo){
				update();
			}
			postInvalidate();
		}
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
		
		while( 6 -contadorParaComecar >= 1){
			canvas.drawText(6 -contadorParaComecar + "", getWidth()/2, getHeight()/2, paint);
		}
		comecarJogo = true;
	}
}
