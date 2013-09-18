package com.example.sobreviva.activity;

import com.example.sobreviva.GerenciadorDeImagens;
import com.example.sobreviva.R;
import com.example.sobreviva.R.layout;
import com.example.sobreviva.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class Loja extends Activity {
	
	private MainActivity GerenteCenas; 

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		// Sem Activity e em modo Fullcreen.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
	
	public void Space(View v)
	{
		GerenciadorDeImagens.getInstance().setImageBolinha("space.png");
		GerenciadorDeImagens.getInstance().setImageBackground("spacebg.png");
		Toast.makeText(MainActivity.GetInstance(), "Agora seu tema é SPACE", Toast.LENGTH_SHORT).show(); 
		//GerenteCenas.switchActivity(this, LojaBolinha.class);
	}
	
	public void Simpsom(View v)
	{
		GerenciadorDeImagens.getInstance().setImageBolinha("simpsom.png");
		GerenciadorDeImagens.getInstance().setImageBackground("simpsombg.png");
		Toast.makeText(MainActivity.GetInstance(), "Agora seu tema é Homer e a Rosca", Toast.LENGTH_SHORT).show(); 
		//GerenteCenas.switchActivity(this, LojaBolinha.class);
	}
	
	public void MonstroSA(View v)
	{
		GerenciadorDeImagens.getInstance().setImageBolinha("monstrosa.png");
		GerenciadorDeImagens.getInstance().setImageBackground("monstrosabg.png");
		Toast.makeText(MainActivity.GetInstance(), "Agora seu tema é Monstros S.A.", Toast.LENGTH_SHORT).show(); 
		//GerenteCenas.switchActivity(this, LojaBolinha.class);
	}

}
