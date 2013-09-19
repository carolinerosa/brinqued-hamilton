package com.example.sobreviva.activity;

import com.example.sobreviva.ConfigUsuario;
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
	private ConfigUsuario config;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Sem Activity e em modo Fullcreen.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_loja);
		GerenteCenas = MainActivity.GetInstance();
		config = ConfigUsuario.getInstance();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.loja, menu);
		return true;
	}

	public void Space(View v) {
		if (config.getMoedas() > 100) {
			config.setMoedas(config.getMoedas() - 100);
			GerenciadorDeImagens.getInstance().setImageBolinha("space.png");
			GerenciadorDeImagens.getInstance()
					.setImageBackground("spacebg.png");
			Toast.makeText(MainActivity.GetInstance(),
					"Você gastou 100 moedas. Você tem " + config.getMoedas(),
					Toast.LENGTH_SHORT).show();
			// GerenteCenas.switchActivity(this, LojaBolinha.class);
		} else {
			Toast.makeText(MainActivity.GetInstance(),
					"Você não tem dinheiro suficiente", Toast.LENGTH_SHORT)
					.show();
			Toast.makeText(
					MainActivity.GetInstance(),
					"Esse tema custa 100 moedas, faltam " + config.getMoedas()
							+ "moedas", Toast.LENGTH_SHORT).show();
		}
	}

	public void Simpsom(View v) {
		if (config.getMoedas() >= 200) {
			config.setMoedas(config.getMoedas() - 200);
			GerenciadorDeImagens.getInstance().setImageBolinha("simpsom.png");
			GerenciadorDeImagens.getInstance().setImageBackground(
					"simpsombg.png");
			Toast.makeText(MainActivity.GetInstance(),
					"Você gastou 200 moedas. Você tem " + config.getMoedas(),
					Toast.LENGTH_SHORT).show();
			// GerenteCenas.switchActivity(this, LojaBolinha.class);
		} else {
			Toast.makeText(MainActivity.GetInstance(),
					"Você não tem dinheiro suficiente", Toast.LENGTH_SHORT)
					.show();
			Toast.makeText(
					MainActivity.GetInstance(),
					"Esse tema custa 200 moedas, faltam " + config.getMoedas()
							+ "moedas", Toast.LENGTH_SHORT).show();
		}
	}

	public void MonstroSA(View v) {
		if (config.getMoedas() >= 300) {
			config.setMoedas(config.getMoedas() - 300);
			GerenciadorDeImagens.getInstance().setImageBolinha("monstrosa.png");
			GerenciadorDeImagens.getInstance().setImageBackground(
					"monstrosabg.png");
			Toast.makeText(MainActivity.GetInstance(),
					"Você gastou 300 moedas. Você tem " + config.getMoedas(),
					Toast.LENGTH_SHORT).show();
			// GerenteCenas.switchActivity(this, LojaBolinha.class);
		} else {
			Toast.makeText(MainActivity.GetInstance(),
					"Você não tem dinheiro suficiente", Toast.LENGTH_SHORT)
					.show();
			Toast.makeText(
					MainActivity.GetInstance(),
					"Esse tema custa 300 moedas, faltam " + config.getMoedas()
							+ "moedas", Toast.LENGTH_SHORT).show();
		}
	}

}
