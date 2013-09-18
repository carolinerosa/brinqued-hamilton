package com.example.sobreviva.activity;






import com.example.sobreviva.GerenciadorDeImagens;
import com.example.sobreviva.multiplayer.util.Conexao;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	private static MainActivity Instance;
	public static final String TAG = "GerenteCenas";
	
	private Conexao  conexao;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

	

	MainActivity Inst = this;
	Instance = Inst;
	//setContentView(R.layout.activity_main);
	GerenciadorDeImagens.getInstance().CarregarImagensBolinha(this);
	
	switchActivity(this, MenuActivity.class);
	
	}
	
	public void switchActivity(Activity De, Class<?> Para)
	{
		Intent intent = new Intent(De, Para);
		De.startActivity(intent);
	}
	
	public void switchView(View view){
		setContentView(view);
	}
	
	public void setConexao(Conexao conexao){
		this.conexao = conexao;
	}
	
	public Conexao getConexao(){
		return this.conexao;
	}

	public static MainActivity GetInstance(){
		return Instance;
	}
	
//	public void CenaMenu() {
//		Intent intent = new Intent(MainActivity.this, MenuActivity.class);
//		startActivity(intent);
//	}
//	
//	public void CenaSinglePlayer() {
//		Intent intent = new Intent(MainActivity.this, SinglePlayerActivity.class);
//		startActivity(intent);
//	}
//	
//	public void CenaMultiPlayer(){
//		Intent intent = new Intent(MainActivity.this, MultiPlayerActivity.class);
//		startActivity(intent);
//	}
//	
//	public void CenaOpcoes()
//	{
//		
//	}
//	
//	public void CenaLoja(){
//		Intent intent = new Intent(MainActivity.this, Loja.class);
//		startActivity(intent);
//		
//	}
//	
//	public void CenaLojaBolinha()
//	{
//		Intent intent = new Intent(MainActivity.this, LojaBolinha.class);
//		startActivity(intent);
//		
//	}
//	
//	public void CenaInstrucoes(){
//		
//	}
//	
//	public void CenaCredito(){
//		
//	}

}
