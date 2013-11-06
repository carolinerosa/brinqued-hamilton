package com.example.sobreviva.jogo;

import android.graphics.Canvas;

public interface InterfaceBolinha {


	public void Action_Down();
	public void Action_Move();
	public void Action_Up();
	public void update();
	void draw(Canvas canvas, int height, int width);
	
}
