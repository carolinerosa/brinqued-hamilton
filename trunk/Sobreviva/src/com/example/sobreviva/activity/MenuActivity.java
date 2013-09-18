package com.example.sobreviva.activity;

import com.example.sobreviva.R;
import com.example.sobreviva.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MenuActivity extends Activity {

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
	
	public void Click_Instrucoes(View v){
		
	}
	
	public void Click_Creditos(View v){
		
	}
	
	

}
