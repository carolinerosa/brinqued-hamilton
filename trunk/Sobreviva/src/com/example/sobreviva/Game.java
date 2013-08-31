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

	private int cont = 1;
	private int largura = 10;
	private int altura = 10;
	private int timeSleepThread = 1;
	private int cx;
	private int cy;
	private float radius;
	private float coefStep = (float) 0.01;
	private float radiusStep = 1;
	private float pontos = 0;
	static private int Recorde = 0;
	private boolean comecar = false;
	private float textSize = 0;
	private Bitmap bitie;

	private Paint paint;
	private Paint paintText;
	
	

	public Game(Context context) 
	{
		super(context);

		bitie = GerenciadorDeImagens.getInstance().getImageBolaTest(
				this.getContext());

		radius = 50;
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

	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		cx = getWidth() / 2;
		cy = getHeight() / 2;

	}

	public void draw(Canvas canvas) {

		super.draw(canvas);
		if (comecar) {
			
			// Como se ele fizesse um rect em cima da img e s� desenha o que o rect estiver cubrindo da img
			 Rect src = new Rect(10,10,200,200);
			 
			// Desenha x,y iniciais onde come�a a img e x,y finais onde ela acaba
			 Rect dest = new Rect(getWidth()/2-10 - largura ,getHeight()/2-10 - largura,getWidth()/2+10+largura,getHeight()/2+10+largura);
		
			// canvas.drawCircle(cx, cy, radius, paint);
			// canvas.drawBitmap(GerenciadorDeImagens.getInstance().getImageBolaTest(this.getContext()),
			// cx, cy, paint);

			// Bitmap resizedBitmap =
			// Bitmap.createScaledBitmap(GerenciadorDeImagens.getInstance().getImageBolaTest(this.getContext()),200,
			// 30, true);
			 canvas.drawBitmap(GerenciadorDeImagens.getInstance().getImageBolaTest(this.getContext()), src, dest, paint);

			//canvas.drawBitmap(GerenciadorDeImagens.getInstance().getResizedBitmap(GerenciadorDeImagens.getInstance().getImageBolaTest(this.getContext()),largura, altura), 7, 3, paint);
			
			GerenciadorDeImagens.getInstance().getImageBolaTest(this.getContext()).recycle();

			// canvas.drawBitmap(GerenciadorDeImagens.getInstance().getImageBolaTest(this.getContext()),
			// matrixx, paint);
			// invalidate();

			canvas.drawText("pontos : " + (int) pontos, 10, (getHeight() / 5),
					paintText);
			canvas.drawText("Recorde :" + Recorde, 10, (getHeight() / 8),
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
			try {
				Thread.sleep(timeSleepThread);
			} catch (InterruptedException e) {
				// Log.e(MainActivity.TAG, "interrupcao do run()");
			}
			update();
			postInvalidate();

		}

	}

	public boolean condicaoDerrota(float radius, float cx, float cy) {

	//	if (radius <= 0)
	//		return true;
	//	if (cx + radius >= getWidth())
	//		return true;
	//	if (cy + radius >= getHeight())
	//		return true;
	//	if (cx - radius < 0)
	//		return true;
	//	if (cy - radius < 0)
	//		return true;
		if(largura > 230)
			return true;
		

		return false;
	}

	private void update() {

		if (comecar) {
			radius += (radiusStep * coefStep);
			pontos += (0.6 * coefStep);
		}

		this.textSize = (getHeight() / 10) / 3;
		paintText.setTextSize(textSize);

		if (Recorde < pontos)
			this.Recorde = (int) pontos;

		if (condicaoDerrota(this.radius, this.cx, this.cy)) {
			this.radius = 50;
			
			this.coefStep = (float) 0.01;
			this.pontos = 0;
			this.comecar = false;
		}

		if (cont > 80) {
			largura += radiusStep * 10;
			altura+= radiusStep * 10;
			cont = 0;

		} else {
			cont++;
		}
		
		if(largura >= 230)
		{
			largura = 1;
		}
		if(altura >= 230)
		{
			altura =1;
		}

		
		Log.i("valor", largura + " :) " + altura);
	}

}
