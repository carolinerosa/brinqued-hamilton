package com.example.sobreviva.views;

import java.io.IOException;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.sobreviva.ConfigUsuario;
import com.example.sobreviva.jogo.BackgroundSimples;
import com.example.sobreviva.jogo.BolinhaSimples;
import com.example.sobreviva.multiplayer.util.Killable;

public class SinglePlayerGameView extends View implements Runnable, Killable {

	
	private final long  TIME_SLEEP = 10;
	private BolinhaSimples bolinha;
	private BackgroundSimples background;
	private boolean ativo;
	private boolean podeComecar = false;
	
	private Paint paintGold, paintScore, paintGenerica;
	private TextView ScorePos, GoldPos;
	
	private Bitmap imgTouch, imgPlacar, imgGold;
	
	private static int width, height;

	private float horizontalProportion = 100, verticalProportion = 100;

	
	public SinglePlayerGameView(Context context) {
		super(context);
		podeComecar = false;
		bolinha = new BolinhaSimples(context);
		background = new BackgroundSimples(bolinha);
		
		Typeface compute = Typeface.createFromAsset(getContext().getAssets(),
				"Computerfont.ttf");

		Typeface gotica = Typeface.createFromAsset(getContext().getAssets(),
				"gotica.ttf");

		
		paintGenerica = new Paint();
		paintGold = new Paint();
		paintScore = new Paint();
		
		paintGold.setColor(Color.YELLOW);
		paintGold.setTypeface(gotica);
		
		paintScore.setColor(Color.GREEN);
		paintScore.setTypeface(compute);

		
		try {
			imgTouch = BitmapFactory.decodeStream(getContext().getAssets().open("touch.png"));
			
			imgGold = BitmapFactory.decodeStream(context.getAssets().open("moedas.png"));
			
			imgPlacar = BitmapFactory.decodeStream(context.getAssets().open("placar.png"));
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
			if(podeComecar){
				update();
			}
			postInvalidate();
		}
	}

	private void update() {
		
		if(bolinha.condicaoDerrota()){
			podeComecar = false;
			bolinha.derrota();
		}
		
		background.update();
		bolinha.update();
	}
	
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
		if(!podeComecar){
			
			Rect srcBackground = new Rect(0, 0, imgTouch.getWidth(), imgTouch.getHeight());

			// Determina x,y iniciais onde começa a img e x,y finais onde ela
			// acaba
			Rect destBackground = new Rect(0, 0, getWidth(), getHeight());

			// Desenha o bitmap com os rects criados acima;
			canvas.drawBitmap(imgTouch, srcBackground, destBackground,
					paintGenerica);
			
			String Rec = "" + ConfigUsuario.getInstance().getRecorde();
			canvas.drawText(Rec, getWidth() / 2 - Rec.length()
					* getWidth() / 10,
					getHeight() - paintScore.getTextSize() / 2.5f,
					paintScore);
			
			
			
		}else{
			
			background.draw(canvas, getHeight(), getWidth());
			bolinha.draw(canvas, getHeight(), getWidth());
			
			float xImg = (imgGold.getWidth() / getWidth())*20; 
			float yImg = (imgGold.getHeight() / getHeight())*100;
			canvas.drawBitmap(imgGold, xImg, yImg, paintGenerica);
			canvas.drawText("234", xImg + 5 , yImg + 10, paintGold);
			
			String pont = "" + ConfigUsuario.getInstance().getRecorde();
			canvas.drawText(pont, getWidth() / 2 - pont.length()
					* 8, getHeight() / 6, paintScore);
			
			
		}
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
	
		paintGold.setTextSize((float) (getHeight() / 10.8));
		paintScore.setTextSize((float) (getHeight() / 6.7));

		
		horizontalProportion = horizontalProportion / w;
		verticalProportion = verticalProportion / h;

		width = getWidth();
		height = getHeight();
		
		
		
	}
	

	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			bolinha.Action_Down();
			podeComecar = true;
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
