package com.example.testepravaler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.app.Activity;
import android.os.Bundle;



public class Nel extends View implements Runnable {
	
	private int time = 1;
	private float cx;
	private float cy;
	private float radius;
	private float coefStep = (float) 0.01;
	private float radiusStep = 1;
	private float pontos = 0;
	static private int Recorde = 0;
	private boolean comecar = false;
	private float textSize = 0;
	
	private Paint paint;
	private Paint paintText;

	public Nel(Context context) {
		super(context);

		radius = 50;
		paint = new Paint();
		paint.setColor(Color.BLUE);
		
		paintText = new Paint();
		paintText.setColor(Color.BLACK);
		paintText.setTextSize(getWidth()/30);
		
		
		
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
		    if(!comecar)
		    	comecar = true;
			
		}

		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			//Log.i(MainActivity.TAG, "SHAKE !!!");
			//cx = event.getRawX();
			//cy = event.getRawY();
						
		}

		if (event.getAction() == MotionEvent.ACTION_UP) {
		//	Log.i(MainActivity.TAG, "nao encoste !!");
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
		if(comecar){
			canvas.drawCircle(cx, cy, radius, paint);
			canvas.drawText("pontos : " + (int) pontos, 10, (getHeight() / 5), paintText);
			canvas.drawText( "Recorde :"+ Recorde , 10, (getHeight() / 8), paintText);
		}else{
			canvas.drawText("Nao deixe a bolinha sumir", getWidth() / 4, getHeight() / 2, paintText);
			canvas.drawText("Não deixe ela tocar nos cantos", getWidth() / 4, (getHeight() / 2)-30, paintText);
			canvas.drawText("Toque na tela para começar", getWidth() / 4, getHeight() / 3, paintText);
		}
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				//Log.e(MainActivity.TAG, "interrupcao do run()");
			}
			update();
			postInvalidate();
		}

	}
	
	public boolean condicaoDerrota(float radius, float cx, float cy){
		
		if(radius <= 0)
			return true;
		if(cx + radius >= getWidth())
			return true;
		if(cy + radius >= getHeight())
			return true;
		if(cx - radius < 0)
			return true;
		if(cy - radius < 0)
			return true;
		
		return false;
	}

	private void update() {
		if(comecar){
			radius += (radiusStep * coefStep);
			pontos += (0.6 * coefStep);
		}
		
		this.textSize = (getHeight() / 10)/3;
		paintText.setTextSize(textSize);
		
		if(Recorde < pontos)
			this.Recorde = (int) pontos;
		
		if(condicaoDerrota(this.radius, this.cx, this.cy)){
			this.radius = 50;
			this.coefStep = (float) 0.01;
			this.pontos = 0;
			this.comecar = false;
		}
	}

}