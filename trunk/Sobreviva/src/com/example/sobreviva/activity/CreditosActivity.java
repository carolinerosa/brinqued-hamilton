package com.example.sobreviva.activity;

import com.example.sobreviva.R;
import com.example.sobreviva.R.layout;
import com.example.sobreviva.R.menu;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

public class CreditosActivity extends Activity 
{
	private WebView webView1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Sem Activity e em modo Fullcreen.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		setContentView(R.layout.activity_creditos);
	}
	
	public void Emanoel(View v) 
	{
		
		 webView1 = (WebView) findViewById(R.id.creditos);
         webView1.getSettings().setJavaScriptEnabled(true);
         webView1.loadUrl("http://www.google.co.in/");
		
	}
	
	


}
