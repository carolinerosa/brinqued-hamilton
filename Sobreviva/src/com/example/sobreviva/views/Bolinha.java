package com.example.sobreviva.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Bolinha {

	private float cx;
	private float cy;
	private float radius;
	private float coefStep = (float) 0.01;
	private float radiusStep = 1;
	
	private Paint paint;
	private int width;
	private int height;
	
	public Bolinha(Context context) {

		radius = 50;
		paint = new Paint();
		paint.setColor(Color.BLUE);
		
		
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
	
	public void draw(Canvas canvas, int height, int width) 
	{
		

		this.height = height;
		this.width = width;
		
		Log.i("bolinha", "Height: "+ height + "width:  "+width);
		if(height > width){
			cx = width/2;
			cy = height - cx;
		}else{
			cy = height/2;
			cx = width - cy;
		}
		
		Log.i("bolinha", "cx: "+ cx + "cy:  "+cy);

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
