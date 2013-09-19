package com.example.sobreviva.activity;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.example.sobreviva.R;
import com.example.sobreviva.R.layout;
import com.example.sobreviva.R.menu;
import com.example.sobreviva.multiplayer.cliente.ControleDeUsuariosCliente;
import com.example.sobreviva.multiplayer.cliente.DadosDoCliente;
import com.example.sobreviva.multiplayer.server.ControleDeUsuariosServidor;
import com.example.sobreviva.multiplayer.server.GerenteDEConexao;
import com.example.sobreviva.multiplayer.server.TratadorDeRedeECO;
import com.example.sobreviva.multiplayer.util.Conexao;
import com.example.sobreviva.multiplayer.util.DepoisDeReceberDados;
import com.example.sobreviva.multiplayer.util.DialogHelper;
import com.example.sobreviva.multiplayer.util.ElMatador;
import com.example.sobreviva.multiplayer.util.Protocolo;
import com.example.sobreviva.multiplayer.util.RedeUtil;
import com.example.sobreviva.multiplayer.util.ViewUtil;
import com.example.sobreviva.views.Game;
import com.example.sobreviva.views.MultiplayerGameView;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class MultiPlayerActivity extends Activity {

	public static final String TAG = "rede";
	private static final int PORTA_PADRAO = 2221;
	private GerenteDEConexao gerenteDeConexao;

	private EditText editUsuario;
	private EditText editIP;
	private String usuario;
	private boolean escolheuNome;
	ProgressDialog progressDialog;

	private MainActivity gerenteCenas = MainActivity.GetInstance();

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

		setContentView(R.layout.activity_multi_player);

		editIP = (EditText) findViewById(R.id.editIP);
		editUsuario = (EditText) findViewById(R.id.editNome);
		gerenteCenas = MainActivity.GetInstance();

	}

	public void Click_CriarServidor(View sender) {
		this.salvarUsuario();

		if (escolheuNome)

			ViewUtil.closeKeyboard(this);

		if (gerenteDeConexao != null) {
			gerenteDeConexao.killMeSoftly();
		}

		gerenteDeConexao = new GerenteDEConexao(PORTA_PADRAO);

		// gerenteDeConexao.iniciarServidor(new TratadorDeRedeECO());
		gerenteDeConexao.iniciarServidor(new ControleDeUsuariosServidor());

		String serverIp = RedeUtil.getLocalIpAddress();

		if (serverIp == null) {

			DialogHelper.message(this, "Conecte-se a alguma rede");

		} else if (!escolheuNome) {
			DialogHelper.message(this, "Insira um nickname.");
		} else {

			// DialogHelper.message(this, "endereco do servidor : " + serverIp);
			// setTitle("servidor : " + serverIp);

			DepoisDeReceberDados tratadorDeDadosDoCliente = new ControleDeUsuariosCliente();
			ControleDeUsuariosCliente c = (ControleDeUsuariosCliente) tratadorDeDadosDoCliente;
			c.setGameActivity(this);

			final Thread socketThread = new Thread(new Runnable() {

				public void run() {

					try {
						Socket s = null;
						s = new Socket("127.0.0.1", PORTA_PADRAO);
						Conexao connect = new Conexao(s, usuario,
								gerenteCenas.getControle());
						gerenteCenas.setConexao(connect);
						gerenteCenas.setDado(new DadosDoCliente(connect, 30));

					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

			// garante que view possa recuperar a lista de usuarios atual e
			// enviar dados pela rede

			carregando();

			// MultiplayerGameView multiplayerGame = new MultiplayerGameView();
			// setContentView(multiplayerGame);
		}

	}

	public void salvarUsuario() {

		if (getEditName().length() == 0) {
			DialogHelper.message(this, "Insira um nickname.");
		}

		if (getEditName().length() > 10) {
			DialogHelper.message(this, "Insira um nickname menor.");
		}

		if (getEditName().length() >= 1 && getEditName().length() <= 10) {
			ViewUtil.closeKeyboard(this);
			usuario = getEditName();
			Log.i(TAG, "usuario salvo:" + usuario);
			escolheuNome = true;

		}
	}

	public String getEditName() {
		return editUsuario.getText().toString();
	}

	public void Click_Conectar(View sender) {

		this.salvarUsuario();
		String ip = editIP.getText().toString();

		if (!escolheuNome) {
			DialogHelper.message(this, "Insira um nickname.");
		} else if (ip.trim().length() == 0) {
			DialogHelper.message(this,
					"endereco do servidor nao pode ser vazio");

		} else {
			ViewUtil.closeKeyboard(this);

			try {

				DepoisDeReceberDados tratadorDeDadosDoCliente = new ControleDeUsuariosCliente();
				ControleDeUsuariosCliente c = (ControleDeUsuariosCliente) tratadorDeDadosDoCliente;
				c.setGameActivity(this);

				// usuario =
				// GerenciadorActivity.GetInstance().getPlayer().getNome();
				Socket s = new Socket(ip, PORTA_PADRAO);
				Conexao connect = new Conexao(s, usuario,
						tratadorDeDadosDoCliente);
				gerenteCenas.setConexao(connect);
				gerenteCenas.setDado(new DadosDoCliente(connect, 30));

				carregando();

				// garante que view possa recuperar a lista de usuarios atual e
				// enviar dados pela rede

				// MultiplayerGameView multiplayerGame = new
				// MultiplayerGameView();
				//
				// setContentView(multiplayerGame);

			} catch (UnknownHostException e) {
				DialogHelper.error(this, "Erro ao conectar com o servidor",
						this.TAG, e);

			} catch (IOException e) {
				DialogHelper.error(this, "Erro ao comunicar com o servidor",
						this.TAG, e);
			}
		}
	}

	private void carregando() {

		String ip = RedeUtil.getLocalIpAddress();

		progressDialog = new ProgressDialog(this);

		progressDialog
				.setMessage("Aguarde a entrada de outros jogadores \nSeu IP é: "
						+ ip);

		progressDialog.show();
	}

	public void comecar() {
		MainActivity.GetInstance().switchActivity(this,
				MultiPlayerGameActivity.class);
		if (progressDialog != null) {
			progressDialog.cancel();
		}
		finish();
	}

	/**
	 * @see http 
	 *      ://stackoverflow.com/questions/2257963/how-to-show-a-dialog-to-confirm
	 *      -that-the-user-wishes-to-exit-an-android-activity
	 */
	public void onBackPressed() {

		killMeSoftly();
	}

	/**
	 * realiza limpeza dos threads em funcionamento
	 */
	public void killMeSoftly() {

		finish();
	}

}
