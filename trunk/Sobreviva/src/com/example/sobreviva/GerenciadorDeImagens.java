package com.example.sobreviva;

import java.io.IOException;
import java.io.InputStream;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class GerenciadorDeImagens {

	private Bitmap BolaTest;
	private static GerenciadorDeImagens instance;
	private String NomeDaImg = "BolaTeste.jpg";
	boolean podeMudar = true;
	
	
	
	
	private GerenciadorDeImagens(){
		
		
	} 
	
	public static GerenciadorDeImagens getInstance(){
		if(instance == null)
		{
			instance = new GerenciadorDeImagens();
		}
		return instance;
		
		
	}
	
	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
		 
		bm  = BolaTest;
		
		int width = bm.getWidth();
		 
		int height = bm.getHeight();
		 
		//float scaleWidth = ((float) newWidth) / width;
		 
		//float scaleHeight = ((float) newHeight) / height;
		 
		// CREATE A MATRIX FOR THE MANIPULATION
		 
		Matrix matrix = new Matrix();
		 
		// RESIZE THE BIT MAP
		 
		matrix.postScale(newWidth, newHeight);
		 
		// RECREATE THE NEW BITMAP
		 
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
		 
		return resizedBitmap;
		
		
		
		 
		}
	
	public void CarregarImagens(Context context){
	
		try 
		{
			if(podeMudar)
			{
			//InputStream stream = context.getAssets().open("BolaTeste.jpg");
			InputStream Nome = context.getAssets().open(NomeDaImg);
			BolaTest = BitmapFactory.decodeStream(Nome);
			
			//podeMudar = false;
			
			}
		
			
		} 
		catch (IOException e) 
		{
			Log.e("hu3", "Erro carregando imagem");
		}
	}
	
	public Bitmap getImageBolaTest(Context context)
	{
		CarregarImagens(context);
		return BolaTest;
	}
	
	public void setImage(String NomeDaImg)
	{
		this.NomeDaImg = NomeDaImg;
		Log.e(NomeDaImg, "deu pepi");
		//podeMudar = true;
		
	}
	
	
	
	
	
}
