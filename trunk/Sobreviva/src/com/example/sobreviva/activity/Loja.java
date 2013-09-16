package com.example.sobreviva.activity;

import com.example.sobreviva.GerenciadorDeImagens;
import com.example.sobreviva.R;
import com.example.sobreviva.R.layout;
import com.example.sobreviva.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class Loja extends Activity {
	
	private MainActivity GerenteCenas; 

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loja);
		GerenteCenas = MainActivity.GetInstance();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.loja, menu);
		return true;
	}
	
	public void lojaBolinha(View v)
	{
		GerenteCenas.switchActivity(this, LojaBolinha.class);
	}

}
