package com.example.sobreviva;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class GerenciadorDeImagens {

	private Bitmap BolaTest;
	private static GerenciadorDeImagens instance;
	
	private GerenciadorDeImagens(){} 
	
	public static GerenciadorDeImagens getInstance(){
		if(instance == null)
		{
			instance = new GerenciadorDeImagens();
		}
		return instance;
	}
	
	public void CarregarImagens(Context context){
		
		try {
			InputStream stream = context.getAssets().open("BolaTest.jpg");
			BolaTest = BitmapFactory.decodeStream(stream);
		} catch (IOException e) {
			Log.e("hu3", "Erro carregando imagem");
		}
	}
	
	public Bitmap getImageBolaTest(){
		return BolaTest;
	}
	
}
