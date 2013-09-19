package com.example.sobreviva.activity;






import com.example.sobreviva.GerenciadorDeImagens;
import com.example.sobreviva.multiplayer.cliente.ControleDeUsuariosCliente;
import com.example.sobreviva.multiplayer.cliente.DadosDoCliente;
import com.example.sobreviva.multiplayer.server.GerenteDEConexao;
import com.example.sobreviva.multiplayer.server.TratadorDeRedeECO;
import com.example.sobreviva.multiplayer.util.Conexao;
import com.example.sobreviva.multiplayer.util.ElMatador;
import com.example.sobreviva.multiplayer.util.Killable;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity implements Killable {

	private static MainActivity Instance;
	public static final String TAG = "GerenteCenas";
	
	private Conexao  conexao;
	private DadosDoCliente dados;
	private ControleDeUsuariosCliente controle;
	private GerenteDEConexao gerenteConexoes;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ElMatador.getInstance().add(this);

		// Sem Activity e em modo Fullcreen.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		MainActivity Inst = this;
		Instance = Inst;
		// setContentView(R.layout.activity_main);
		GerenciadorDeImagens.getInstance().CarregarImagensBolinha(this);

		switchActivity(this, MenuActivity.class);
	
	}
	
	public void switchActivity(Activity De, Class<?> Para)
	{
		Intent intent = new Intent(De, Para);
		De.startActivity(intent);
	}
	
	public void setControle(ControleDeUsuariosCliente cont){
		this.controle = cont;
	}
	public ControleDeUsuariosCliente getControle(){
		return this.controle;
	}
	
	public void setConexao(Conexao conexao){
		this.conexao = conexao;
	}
	
	public Conexao getConexao(){
		return this.conexao;
	}
	
	public void setDado(DadosDoCliente dados){
		this.dados = dados;
	}
	
	public DadosDoCliente getDados(){
		return this.dados;
	}
	
	public void setGerenteConexoes(GerenteDEConexao g){
		this.gerenteConexoes = g;
	}
	
	public void FecharMultiplayer(){
		if(dados != null){
		dados.killMeSoftly();
		dados = null;
		}
		if (conexao != null) {
			conexao.killMeSoftly();
			conexao = null;
		}
		if (gerenteConexoes != null) {
			gerenteConexoes.killMeSoftly();
			gerenteConexoes = null;
		}
		conexao.killMeSoftly();
	}
	

	public static MainActivity GetInstance(){
		return Instance;
	}
	
	

	@Override
	public void killMeSoftly() {
		this.finish();
		
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
