package com.example.sobreviva.activity;



import com.example.sobreviva.GerenciadorDeImagens;
import com.example.sobreviva.R;
import com.example.sobreviva.R.layout;
import com.example.sobreviva.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Canvas;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;


public class LojaBolinha extends Activity 
{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loja_bolinha);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.loja_bolinha, menu);
		return true;
	}
	
	public void tipo1(View v)
	{
		GerenciadorDeImagens.getInstance().setImageBolinha("tipo1.png");
		Toast.makeText(MainActivity.Instance, "Tipo 1 setado", Toast.LENGTH_SHORT).show(); 
		
	}
	
	public void tipo2(View v)
	{
		GerenciadorDeImagens.getInstance().setImageBolinha("tipo2.png");
		Toast.makeText(MainActivity.Instance, "Tipo 2 setado", Toast.LENGTH_SHORT).show(); 
		
	}
	
	public void tipo3(View v)
	{
		GerenciadorDeImagens.getInstance().setImageBolinha("tipo3.jpg");
		Toast.makeText(MainActivity.Instance, "Tipo 3 setado", Toast.LENGTH_SHORT).show(); 
		
	}
	
	public void draw(Canvas canvas)
	{
		
	}

}
