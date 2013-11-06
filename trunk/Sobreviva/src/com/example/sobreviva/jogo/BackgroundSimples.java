package com.example.sobreviva.jogo;

import android.graphics.Canvas;
import android.graphics.Paint;

public class BackgroundSimples implements InterfaceBackground {

	BolinhaSimples bolinha;
	private int percentNivel;
	Paint paint;
	
	public BackgroundSimples(BolinhaSimples bolinha){
		this.bolinha = bolinha;
		this.paint = new Paint();
	}
	
	@Override
	public void update() {
		if(percentNivel >= 100){
			percentNivel = 0;
		}
	}
	
	public void setPercent(int P){
		percentNivel += P;
	}

	@Override
	public void draw(Canvas canvas, int height, int width) {
		canvas.drawRGB(255 - bolinha.getRed(), 255 - bolinha.getGreen(), 255 - bolinha.getBlue());
		
		int coefNivel = (Math.abs(height/100))*percentNivel;
		
		paint.setARGB(100, Math.abs(bolinha.getRed()/3), Math.abs(bolinha.getGreen()/3), 
				Math.abs(bolinha.getBlue()/3));
		
		canvas.drawRect((int)0, (int)(height - coefNivel), (int)width, (int)height, paint);
	}

}
