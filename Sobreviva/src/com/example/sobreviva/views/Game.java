package com.example.sobreviva.views;

import java.io.IOException;
import java.io.InputStream;

import com.example.sobreviva.ConfigUsuario;
import com.example.sobreviva.GerenciadorDeImagens;
import com.example.sobreviva.multiplayer.util.ElMatador;
import com.example.sobreviva.multiplayer.util.Killable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Game extends View implements Runnable, Killable {

	private ConfigUsuario config = ConfigUsuario.getInstance();
	private int dinheiro = config.getMoedas();
	private int pontosguardados = 100;
	private int cont = 1;
	private int largura = 10;
	private int timeSleepThread = 1;
	private int velocidade = 100;
	private float coefStep = (float) 0.01;
	private float radiusStep = 1;
	private float pontos = 0;
	private int Recorde = config.getRecorde();
	private boolean comecar = false;
	private boolean podediminuirver = false;
	private float textSize = 0;
	private Bitmap bitieBolinha;
	private Bitmap bitieBackground;
	private Paint paint;
	private Paint paintText;
	private Paint paintTextPontos;
	private boolean ativo = true;
	private Bitmap BitmapTouchRedButton;
	
	

	public Game(Context context) 
	{
		super(context);
		
		ElMatador.getInstance().add(this);

		bitieBolinha = GerenciadorDeImagens.getInstance().getImageBolinha(this.getContext());
		
		bitieBackground = GerenciadorDeImagens.getInstance().getImagesBackground(this.getContext());
		
		InputStream Nome;
		try {
			Nome = getContext().getAssets().open("touch.png");
			BitmapTouchRedButton = BitmapFactory.decodeStream(Nome);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


		//radius = 50;
		paint = new Paint();
		paint.setColor(Color.BLUE);

		paintText = new Paint();
		
		paintText.setColor(Color.BLACK);
		paintText.setTextSize(getWidth() / 25);
		
		paintTextPontos = new Paint();
		paintTextPontos.setColor(Color.BLACK);
		paintTextPontos.setTextSize(getWidth() / 25);
		
		Typeface comp = Typeface.createFromAsset(getContext().getAssets(),
		        "Computerfont.ttf");
		
		Typeface got = Typeface.createFromAsset(getContext().getAssets(),
		        "gotica.ttf");
		
		paintTextPontos.setTypeface(comp);
		paintTextPontos.setColor(Color.GREEN);
		
		paintText.setTypeface(got);
		paintText.setColor(Color.YELLOW);
		
		
		

		setFocusableInTouchMode(true);
		setClickable(true);
		setLongClickable(true);

		Thread thread = new Thread(this);
		thread.start();

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN) {

			
			coefStep += 0.01;
			radiusStep = -1;
			paint.setColor(Color.GREEN);
			if (!comecar)
				comecar = true;

		}

		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			// Log.i(MainActivity.TAG, "SHAKE !!!");
			// cx = event.getRawX();
			// cy = event.getRawY();

		}

		if (event.getAction() == MotionEvent.ACTION_UP) {
			// Log.i(MainActivity.TAG, "nao encoste !!");
			radiusStep = 1;
			paint.setColor(Color.RED);
			
		}

		return super.onTouchEvent(event);
	}

	protected void onSizeChanged(int w, int h, int oldw, int oldh) 
	{
		super.onSizeChanged(w, h, oldw, oldh);

		

	}

	public void draw(Canvas canvas) {

		super.draw(canvas);
		if (comecar) {
			
			 //------------------------------- background ----------------------------------------
			 
			// Como se ele fizesse um rect em cima da img e só desenha o que o rect estiver cubrindo da img
			 Rect srcBackground = new Rect(0,0,450,800 );
			 
			// Determina x,y iniciais onde começa a img e x,y finais onde ela acaba
			 Rect destBackground = new Rect(0,0,getWidth(),getHeight());
		
			// Desenha o bitmap com os rects criados acima;
			 canvas.drawBitmap(bitieBackground, srcBackground, destBackground, paint);
			 
			 if (bitieBackground != null && bitieBackground.isRecycled()) 
             {
                 bitieBackground.recycle();
                 bitieBackground = null;
                 System.gc(); 
             }
			
			//------------------------------ bolinha------------------------------------------------
			
			// Como se ele fizesse um rect em cima da img e só desenha o que o rect estiver cubrindo da img
			 Rect srcBolinha = new Rect(0,0,225,225 );
			 
			// Determina x,y iniciais onde começa a img e x,y finais onde ela acaba
			 Rect destBolinha = new Rect( getWidth()/2  -10- largura  ,getHeight()/2 -10 - largura ,getWidth()/2+10+largura,getHeight()/2+10+largura);
		
			// Desenha o bitmap com os rects criados acima;
			 canvas.drawBitmap(bitieBolinha, srcBolinha, destBolinha, paint);

						
			 if (bitieBolinha != null && bitieBolinha.isRecycled()) 
             {
                 bitieBolinha.recycle();
                 bitieBolinha = null;
                 System.gc(); 
             }
			 
			
			
			
			

			canvas.drawText("" + (int) pontos, getWidth()/2 - 80, 120,paintTextPontos);
			
			
			
			canvas.drawText("" + dinheiro, 90, 750, paintText);
			
			postInvalidate();
		} else {
				Rect touchsrc = new Rect(0, 0, 450, 800);
				Rect touchdst = new Rect(0, 0, getWidth(), getHeight());

				canvas.drawBitmap(BitmapTouchRedButton, touchsrc, touchdst, paint);
				
				canvas.drawText("" + Recorde, getWidth()/2 - 80, 750, paintTextPontos);
			
		}
	}

	public void run() {
		while (ativo) {
			try 
			{
				Thread.sleep(timeSleepThread);
			} catch (InterruptedException e) {
				// Log.e(MainActivity.TAG, "interrupcao do run()");
			}
			update();
			postInvalidate();

		}

	}

	public boolean condicaoDerrota() 
	{
	
		if(largura > 220)
			return true;
		if(largura <= 0)
			return true;
		

		return false;
	}

	private void update() 
	{
		if(velocidade <= 1)
		{
			podediminuirver =  false;
			velocidade = -10;
		}

		// aqui é a velocidade baseada nos pontos, essa velocidade é usada no "metodo" de aumentar e deminuir da bola
		if(pontos > pontosguardados)
		{
			
			velocidade -= 1;
			pontosguardados += 30;
			
			
		}
		
		
		
		//------------------------ velocidade bolinha--------------------
			
		if (comecar) 
		{
			//radius += (radiusStep * coefStep);
			pontos += (0.6 * coefStep);
		}

		
		paintText.setTextSize(50);
		paintTextPontos.setTextSize(80);
		
		
		

		if (Recorde < pontos){
			this.Recorde = (int) pontos;
			config.setRecorde(Recorde);
		}
		
		if (condicaoDerrota()) 
		{
			this.largura = 20;
			this.pontosguardados = 100;
			this.velocidade = 100;
			this.coefStep = (float) 0.01;
			this.pontos = 0;
			this.comecar = false;
		}

		// metodo de aumentar e diminuir a bola de acordo com a velocidade que foi mudada acima
		if (cont > velocidade) 
		{
			largura += radiusStep * 10 ;
			dinheiro++;
			config.setMoedas(dinheiro);
			
			cont = 0;

		} else {
			cont++;
		}
		
		

	}
	
	public void onBackPressed()
	{
		killMeSoftly();
	}

	@Override
	public void killMeSoftly() {
		ativo = false;
		
		if (Recorde < pontos){
			this.Recorde = (int) pontos;
			config.setRecorde(Recorde);
		}
		config.setMoedas(dinheiro);
		
	}

}
