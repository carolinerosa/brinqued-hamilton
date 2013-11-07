package com.example.sobreviva.jogo;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class BolinhaSimples implements InterfaceBolinha {

	private float cx;
	private float cy;
	private float radius;
	private float coefStep = (float) 0.01;
	private float radiusStep = 1;
	
	private Paint paint;
	private int width;
	private int height;
	private Random rnd;
	
	boolean desenhou = false;
	
	private int red, green, blue;
	
	public BolinhaSimples(Context context) {

		paint = new Paint();
		rnd = new Random();
		red = rnd.nextInt(255);
		green = rnd.nextInt(255);
		blue = rnd.nextInt(255);
		paint.setColor(Color.rgb(red, green, blue));
		
		
	}
	
	public void Action_Down(){
		coefStep += 0.01;
		radiusStep = -1;
		red = rnd.nextInt(255);
		green = rnd.nextInt(255);
		blue = rnd.nextInt(255);
		paint.setColor(Color.rgb(red, green, blue));
	}
	
	public void Action_Move(){
		
	}
	
	public void Action_Up(){
		radiusStep = 1;
		red = rnd.nextInt(255);
		green = rnd.nextInt(255);
		blue = rnd.nextInt(255);
		paint.setColor(Color.rgb(red, green, blue));
	}

	public int getRed(){
		return red;
	}
	
	public int getGreen(){
		return green;
	}
	
	public int getBlue(){
		return blue;
	}
	
	@Override
	public void draw(Canvas canvas, int height, int width) 
	{
		

		this.height = height;
		this.width = width;
		
		if(!desenhou){
			radius = (width/2)/2;
			desenhou = true;
		}
		
		
		/*if(height > width){
			cx = width/2;
			cy = height - cx;
		}else{
			cy = height/2;
			cx = width - cy;
		}*/
		
		cx = width/2;
		cy = height/2;
		
		canvas.drawCircle(cx, cy, radius, paint);
		
	}
	
	public boolean condicaoDerrota(){
		
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
		if(desenhou){
			radius += (radiusStep * coefStep);
		}
	}
	
	public void derrota(){
		desenhou = false;
		this.coefStep = (float) 0.01;
	}

	
}
