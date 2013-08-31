package com.example.sobreviva.activity;






import com.example.sobreviva.GerenciadorDeImagens;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	public static MainActivity Instance;
	public static final String TAG = "GerenteCenas";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

	// Sem Activity e em modo Fullcreen.
	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			WindowManager.LayoutParams.FLAG_FULLSCREEN);
    this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

	MainActivity Inst = this;
	Instance = Inst;
	//setContentView(R.layout.activity_main);
	GerenciadorDeImagens.getInstance().CarregarImagens(this);
	
	CenaMenu();
	
	}

	public static MainActivity GetInstance(){
		return Instance;
	}
	
	public void CenaMenu() {
		Intent intent = new Intent(MainActivity.this, MenuActivity.class);
		startActivity(intent);
	}
	
	public void CenaSinglePlayer() {
		Intent intent = new Intent(MainActivity.this, SinglePlayerActivity.class);
		startActivity(intent);
	}
	
	public void CenaMultiPlayer(){
		Intent intent = new Intent(MainActivity.this, MultiPlayerActivity.class);
		startActivity(intent);
	}
	
	public void CenaOpcoes()
	{
		
	}
	
	public void CenaLoja(){
		Intent intent = new Intent(MainActivity.this, Loja.class);
		startActivity(intent);
		
	}
	
	public void CenaLojaBolinha()
	{
		Intent intent = new Intent(MainActivity.this, LojaBolinha.class);
		startActivity(intent);
		
	}
	
	public void CenaInstrucoes(){
		
	}
	
	public void CenaCredito(){
		
	}

}
