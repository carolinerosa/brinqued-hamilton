package com.example.sobreviva.views;


import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import com.example.sobreviva.activity.MainActivity;
import com.example.sobreviva.activity.MultiPlayerActivity;
import com.example.sobreviva.jogo.BolinhaSimples;
import com.example.sobreviva.multiplayer.cliente.ControleDeUsuariosCliente;
import com.example.sobreviva.multiplayer.cliente.DadosDoCliente;
import com.example.sobreviva.multiplayer.util.Conexao;
import com.example.sobreviva.multiplayer.util.Killable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MultiplayerGameView extends View implements Runnable, Killable{

	private final long  LONG_TIME_SLEEP = 1*1000;
	private final long  TIME_SLEEP = 10;
	private int contadorParaComecar = 0;
	private boolean ativo = true;
	private boolean comecarJogo = false;


	private Paint paintText;
	
	private BolinhaSimples bola;
//	private ControleDeUsuariosCliente tratadorDeDadosDoCliente;
//	private DadosDoCliente dadosDoCliente;

	
	public MultiplayerGameView(){
		super(MainActivity.GetInstance());
		//this.tratadorDeDadosDoCliente = (ControleDeUsuariosCliente) MainActivity.GetInstance().getConexao().getTratador();
		
		// envia estado atual do cliente para o servidor
		//dadosDoCliente = MainActivity.GetInstance().getDados();
//		Thread threadDados = new Thread(dadosDoCliente);
//		threadDados.start();
		
		
		bola = new BolinhaSimples(MainActivity.GetInstance());
		
		paintText = new Paint();
		paintText.setColor(Color.BLACK);
		paintText.setTextSize(getWidth()/30);
		
		setFocusableInTouchMode(true);
		setClickable(true);
		setLongClickable(true);

		ativo = true;
		comecarJogo = false;
		contadorParaComecar = 0;
		Thread thread = new Thread(this);
		thread.start();
		
		
	}
	
	

	@Override
	public void run() {
		while(ativo){
			try {
				if (!comecarJogo) {
					Thread.sleep( LONG_TIME_SLEEP);
					contadorParaComecar++;
					if(contadorParaComecar >= 5){
						comecarJogo = true;
					}
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
	}
	
	public void draw(Canvas canvas) {
		super.draw(canvas);
		drawScore(canvas);
		contagemComecar(canvas);
		bola.draw(canvas, getHeight(), getWidth());
	}
	
	
	private void update() {
		bola.update();
		if(bola.condicaoDerrota()){
			Log.i("MultiPlayer", "PERDEU");
		}
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
		
		if( 5 -contadorParaComecar >= 1){
			
			Bitmap cont;
			
			try {
				
				cont = BitmapFactory.decodeStream(MainActivity.GetInstance().getAssets().open(contadorParaComecar+".png"));
				Rect touchdst = new Rect(0, 0, getWidth(), getHeight());

				Rect touchdst2 = new Rect(0, 0, getWidth(), getHeight());

				canvas.drawBitmap(cont, touchdst, touchdst2, paint);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			//canvas.drawText(5 -contadorParaComecar + "", (getWidth()/2), (getHeight()/2), paint);
			//bola.draw(canvas, getHeight(), getWidth());
		}else{
			comecarJogo = true;
		}
	}
	
	public void drawScore(Canvas canvas){
//		if(this.tratadorDeDadosDoCliente.getJogadores() != null){
//			
//			ConcurrentHashMap<String, Integer> j = this.tratadorDeDadosDoCliente.getJogadores();
//			int i = 1;
//			
//			Iterator<String> iterator = j.keySet().iterator();
//			while (iterator.hasNext()) {
//				String key = iterator.next();
//				canvas.drawText("" + key, 10, 10*i, paintText);
//				canvas.drawText("Pontos :" + j.get(key), getHeight() /2, 10*i,
//						paintText);
//				i++;
//			}
		
		canvas.drawText(" Name çsdms " , 10, 10*2, paintText);
		canvas.drawText("Pontos :" + 35, getHeight() /2, 10*3,
				paintText);
	}



	@Override
	public void killMeSoftly() {
		ativo = false;
		
	}
}
