package com.example.sobreviva.views;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.sobreviva.jogo.BackgroundSimples;
import com.example.sobreviva.jogo.BolinhaSimples;
import com.example.sobreviva.multiplayer.util.Killable;

public class SinglePlayerGameView extends View implements Runnable, Killable {

	
	private final long  TIME_SLEEP = 10;
	private BolinhaSimples bolinha;
	private BackgroundSimples background;
	private boolean ativo;
	
	private Paint paintGold, paintScore, paintGenerica;
	private TextView ScorePos, GoldPos;
	
	public SinglePlayerGameView(Context context) {
		super(context);

		bolinha = new BolinhaSimples(context);
		background = new BackgroundSimples(bolinha);
		
		Typeface compute = Typeface.createFromAsset(getContext().getAssets(),
				"Computerfont.ttf");

		Typeface gotica = Typeface.createFromAsset(getContext().getAssets(),
				"gotica.ttf");

		
		paintGenerica = new Paint();
		paintGold = new Paint();
		paintScore = new Paint();
		
		paintGold.setTextSize(getWidth() /30);
		paintGold.setColor(Color.YELLOW);
		paintGold.setTypeface(gotica);
		
		paintScore.setTextSize(getWidth() /25);
		paintScore.setColor(Color.GREEN);
		paintScore.setTypeface(compute);

		
		setFocusableInTouchMode(true);
		setClickable(true);
		setLongClickable(true);

		ativo = true;
		Thread thread = new Thread(this);
		thread.start();

	}
	

	@Override
	public void run() {
		while(ativo){
			try {
				Thread.sleep((long) TIME_SLEEP);
			} catch (InterruptedException e) {
				// Log.e(MainActivity.TAG, "interrupcao do run()");
			}
			update();
			
			postInvalidate();
		}
	}

	private void update() {
		background.update();
		bolinha.update();
	}
	
	public void draw(Canvas canvas) {
		super.draw(canvas);
		background.draw(canvas, getHeight(), getWidth());
		bolinha.draw(canvas, getHeight(), getWidth());
	}
	

	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			bolinha.Action_Down();
		}

		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			bolinha.Action_Move();			
		}

		if (event.getAction() == MotionEvent.ACTION_UP) {
			bolinha.Action_Up();
			background.setPercent(6);
		}

		return super.onTouchEvent(event);
	}


	@Override
	public void killMeSoftly() {
		ativo = false;
	}
	
}
