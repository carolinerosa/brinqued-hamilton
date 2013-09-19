package com.example.sobreviva.activity;

import com.example.sobreviva.R;
import com.example.sobreviva.multiplayer.util.Const;
import com.example.sobreviva.multiplayer.util.Killable;
import com.example.sobreviva.views.MultiplayerGameView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MultiPlayerGameActivity extends Activity implements Killable {

	MultiplayerGameView game;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Sem Activity e em modo Fullcreen.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		game = new MultiplayerGameView();
		setContentView(game);
	}

	@Override
	public void killMeSoftly() {
		MainActivity.GetInstance().FecharMultiplayer();
		game.killMeSoftly();
		finish();
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
								
								
								killMeSoftly();

							}

						})
				.setNegativeButton("Não", null).show();
	}
}
