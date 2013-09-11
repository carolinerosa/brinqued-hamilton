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

	private Bitmap BitmapBolinha;
	private Bitmap BitmapBackground;
	private static GerenciadorDeImagens instance;
	private String NomeDaImgBolinha = "BolaTeste.jpg";
	private String NomeDaImgBackground = "BolaTeste.jpg";
	boolean podeMudarBolinha = true;
	boolean podeMudarBackground = true;
	
	
	
	
	
	private GerenciadorDeImagens()
	{
		
		
	} 
	
	public static GerenciadorDeImagens getInstance(){
		if(instance == null)
		{
			instance = new GerenciadorDeImagens();
		}
		return instance;
		
		
	}
	
	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
		 
		bm  = BitmapBolinha;
		
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
	
	public void CarregarImagensBolinha(Context context)
	{
	
		try 
		{
			if(podeMudarBolinha)
			{
			//InputStream stream = context.getAssets().open("BolaTeste.jpg");
			InputStream Nome = context.getAssets().open(NomeDaImgBolinha);
			BitmapBolinha = BitmapFactory.decodeStream(Nome);
			
			podeMudarBolinha = false;
			
			}
		
			
		} 
		catch (IOException e) 
		{
			Log.e("hu3", "Erro carregando ImgBolinha");
		}
	}
	
	public void CarregarImagensBackground(Context context)
	{
	
		try 
		{
			if(podeMudarBackground)
			{
			//InputStream stream = context.getAssets().open("BolaTeste.jpg");
			InputStream Nome = context.getAssets().open(NomeDaImgBackground);
			BitmapBackground = BitmapFactory.decodeStream(Nome);
			
			podeMudarBackground = false;
			
			}
		
			
		} 
		catch (IOException e) 
		{
			Log.e("hu3", "Erro carregando Background");
		}
	}
	
	public Bitmap getImageBolinha(Context context)
	{
		CarregarImagensBolinha(context);
		return BitmapBolinha;
	}
	
	public void setImageBolinha(String NomeDaImg)
	{
		this.NomeDaImgBolinha = NomeDaImg;
		Log.e(NomeDaImg, "nome da img");
		podeMudarBolinha = true;
		
	}
	
	public Bitmap getImagesBackground(Context context)
	{
		CarregarImagensBackground(context);
		return BitmapBackground;
	}
	
	public void setImageBackground(String NomeDaImg)
	{
		this.NomeDaImgBackground = NomeDaImg;
		Log.e(NomeDaImg, "nome da img");
		podeMudarBackground = true;
		
	}
	
	
	
	
	
}
