package com.example.sobreviva;

import com.example.sobreviva.activity.MainActivity;
import com.example.sobreviva.multiplayer.util.ElMatador;
import com.example.sobreviva.multiplayer.util.Killable;

import android.content.SharedPreferences;



public class ConfigUsuario implements Killable{

	public static final String SAVE_NAME = "MySave";
	
	private static ConfigUsuario instance;
	
	private MainActivity main = MainActivity.GetInstance();
	private SharedPreferences save;
	//private int QtdAcessos;
	private int Moedas;
	private int Recorde;
	private int Vitorias;
	private int Derrota;
	
	private ConfigUsuario(){
		
		ElMatador.getInstance().add(this);
	       
	    save = main.getSharedPreferences(SAVE_NAME, 0);
	    
	}
	
	public static ConfigUsuario getInstance(){
		
		if(instance == null){
			instance = new ConfigUsuario();
		}
		
		return instance;
	}

	@Override
	public void killMeSoftly() {
		
		SharedPreferences.Editor editor = save.edit();
		editor.putInt("Moedas", Moedas);
		editor.putInt("Recorde", Recorde);
		editor.putInt("Vitorias", Vitorias);
		editor.putInt("Derrota", Derrota);

		editor.commit();
	      
	}
	
	public void setMoedas (int value){
		Moedas = value;
		SharedPreferences.Editor editor = save.edit();
		editor.putInt("Moedas", Moedas);
		editor.commit();
	}
	public void setRecorde (int value){
		Recorde= value;
		SharedPreferences.Editor editor = save.edit();
		editor.putInt("Recorde", Recorde);
		editor.commit();
	}
	public void setVitorias (int value){
		Vitorias= value;
		SharedPreferences.Editor editor = save.edit();
		editor.putInt("Vitorias", Vitorias);
		editor.commit();
	}
	public void setDerrota (int value){
		Derrota= value;
		SharedPreferences.Editor editor = save.edit();
		editor.putInt("Derrota", Derrota);
		editor.commit();
	}
	
	public int getMoedas (){
		Moedas = save.getInt("Moedas", 0);
		return Moedas ;
		
	}
	public int getRecorde (){
		Recorde = save.getInt("Recorde", 0);
		return Recorde ;
	}
	public int getVitorias (){
		Vitorias = save.getInt("Vitorias", 0);
		return Vitorias ;
	}
	public int getDerrota (){
		Derrota = save.getInt("Derrota", 0);
		return Derrota ;
	}
	
	
}
