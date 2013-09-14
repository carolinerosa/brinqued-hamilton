package com.example.sobreviva;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Game extends View implements Runnable {

	private int dinheiro = 0;
	private int pontosguardados = 100;
	private int cont = 1;
	private int largura = 10;
	private int altura = 10;
	private int timeSleepThread = 1;
	private int velocidade = 100;
	private float coefStep = (float) 0.01;
	private float radiusStep = 1;
	private float pontos = 0;
	static private int Recorde = 0;
	private boolean comecar = false;
	private boolean podediminuirver = false;
	private float textSize = 0;
	private Bitmap bitieBolinha;
	private Bitmap bitieBackground;
	private Paint paint;
	private Paint paintText;
	
	

	public Game(Context context) 
	{
		super(context);

		bitieBolinha = GerenciadorDeImagens.getInstance().getImageBolinha(this.getContext());
		
		bitieBackground = GerenciadorDeImagens.getInstance().getImagesBackground(this.getContext());
		
		

		//radius = 50;
		paint = new Paint();
		paint.setColor(Color.BLUE);

		paintText = new Paint();
		paintText.setColor(Color.BLACK);
		paintText.setTextSize(getWidth() / 30);
		
		

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
			 
			// Como se ele fizesse um rect em cima da img e s� desenha o que o rect estiver cubrindo da img
			 Rect srcBackground = new Rect(0,0,225,225 );
			 
			// Determina x,y iniciais onde come�a a img e x,y finais onde ela acaba
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
			
			// Como se ele fizesse um rect em cima da img e s� desenha o que o rect estiver cubrindo da img
			 Rect srcBolinha = new Rect(0,0,225,225 );
			 
			// Determina x,y iniciais onde come�a a img e x,y finais onde ela acaba
			 Rect destBolinha = new Rect(getWidth()/2-10 - largura ,getHeight()/2-10 - largura,getWidth()/2+10+largura,getHeight()/2+10+largura);
		
			// Desenha o bitmap com os rects criados acima;
			 canvas.drawBitmap(bitieBolinha, srcBolinha, destBolinha, paint);

						
			 if (bitieBolinha != null && bitieBolinha.isRecycled()) 
             {
                 bitieBolinha.recycle();
                 bitieBolinha = null;
                 System.gc(); 
             }
			 
			

			paintText.setColor(Color.RED);

			canvas.drawText("pontos : " + (int) pontos, 10, (getHeight() / 6),
					paintText);
			canvas.drawText("Recorde :" + Recorde, 10, (getHeight() / 8),
					paintText);
			canvas.drawText("Dinheiro :" + dinheiro, 170, (getHeight() / 8 ),
					paintText);
			postInvalidate();
		} else {
			canvas.drawText("Nao deixe a bolinha sumir", getWidth() / 4,
					getHeight() / 2, paintText);
			canvas.drawText("N�o deixe ela tocar nos cantos", getWidth() / 4,
					(getHeight() / 2) - 30, paintText);
			canvas.drawText("Toque na tela para come�ar", getWidth() / 4,
					getHeight() / 3, paintText);
		}
	}

	public void run() {
		while (true) {
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

		// aqui � a velocidade baseada nos pontos, essa velocidade � usada no "metodo" de aumentar e deminuir da bola
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

		this.textSize = (getHeight() / 10) / 3;
		paintText.setTextSize(textSize);

		if (Recorde < pontos)
			this.Recorde = (int) pontos;

		if (condicaoDerrota()) 
		{
			this.largura = 10;
			this.altura = 10;
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
			altura += radiusStep * 10;
			dinheiro++;
			
			cont = 0;

		} else {
			cont++;
		}
		
		

		
		Log.i("valor", largura + " :) " + altura);
		Log.i("valor", velocidade + " --> ");
	}

}
