package com.example.sobreviva;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Bolinha {

	private float cx;
	private float cy;
	private float radius;
	private float coefStep = (float) 0.01;
	private float radiusStep = 1;
	
	private Paint paint;
	private int width;
	private int height;
	
	public Bolinha(Context context, int height, int width) {

		radius = 50;
		paint = new Paint();
		paint.setColor(Color.BLUE);
		
		this.height = height;
		this.width = width;
		
	}
	
	public void Action_Down(){
		coefStep += 0.01;
		radiusStep = -1;
	    paint.setColor(Color.GREEN);
		
	}
	
	public void Action_Move(){
		
	}
	
	public void Action_Up(){
		radiusStep = 1;
		paint.setColor(Color.RED);
	}
	
	public void draw(Canvas canvas) {

		canvas.drawCircle(cx, cy, radius, paint);
	}
	
	public boolean condicaoDerrota(float radius, float cx, float cy){
		
		if(radius <= 0)
			return true;
		if(cx + radius >= width)
			return true;
		if(cy + radius >= height)
			return true;
		if(cx - radius < 0)
			return true;
		if(cy - radius < 0)
			return true;
		
		return false;
	}

	public void update() {
		
		radius += (radiusStep * coefStep);
		
	}
	
	public void derrota(){
		this.radius = 50;
			this.coefStep = (float) 0.01;
	}
	
}
