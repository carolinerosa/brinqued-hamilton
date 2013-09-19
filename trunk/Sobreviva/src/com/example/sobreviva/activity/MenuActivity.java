package com.example.sobreviva.activity;

import com.example.sobreviva.R;
import com.example.sobreviva.R.layout;
import com.example.sobreviva.multiplayer.util.Const;
import com.example.sobreviva.multiplayer.util.ElMatador;
import com.example.sobreviva.multiplayer.util.Killable;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MenuActivity extends Activity implements Killable {

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
	    
		setContentView(R.layout.activity_menu);
		
		ElMatador.getInstance().add(this);
		
		GerenteCenas = MainActivity.GetInstance();
		
	}

	public void Click_Single(View v){
		GerenteCenas.switchActivity(GerenteCenas, SinglePlayerActivity.class);
	}
	
	public void Click_Multi(View v){
		GerenteCenas.switchActivity(GerenteCenas, MultiPlayerActivity.class);
	}
	
	public void Click_Opcoes(View v){
		
	}
	
	public void Click_Loja(View v){
		GerenteCenas.switchActivity(GerenteCenas, Loja.class);
	}
	
	public void Click_Instrucoes(View v)
	{
		GerenteCenas.switchActivity(GerenteCenas, InstrucoesActivity.class);
	}
	
	public void Click_Creditos(View v)
	{
		GerenteCenas.switchActivity(GerenteCenas, CreditosActivity.class);
	}
	
	public void onBackPressed()
	{
		Log.i(Const.TAG, "--------- back pressed");

		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("Sair?")
				.setMessage(
						"Tem certeza que deseja sair ?")
				.setPositiveButton("Sim",
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog,
									int which)
							{
								
								ElMatador.getInstance().killThenAll();
								killMeSoftly();

							}

						})
				.setNegativeButton("Não", null).show();
	}

	@Override
	public void killMeSoftly() {
		finish();
	}

}
