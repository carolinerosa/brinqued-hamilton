//package com.example.sobreviva.multiplayer.bluetooth;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.UUID;
//
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothDevice;
//import android.bluetooth.BluetoothSocket;
//import android.content.Context;
//import android.content.Intent;
//import android.util.Log;
//
//public class Cliente implements Runnable{
//
//	private Context context;
//	private static final String TAG = "Cliente";
//	
//	// Socket para receber conexao
//	private static BluetoothSocket mSocket;
//
//	// Codigo de validez e identificação individual para conexão
//	private UUID uuid;
//
//	private InputStream mInputStream;
//	private static OutputStream mOutputStream;
//
//	private boolean connect = false;
//	private boolean connected = false;
//
//	private boolean turn = false;
//
//	public Cliente(BluetoothDevice device, Context context, boolean connect) {
//		// Construtor utilizado pelo aparelho que scanea e pede conexão
//
//		// Temos 3 parametros:
//		// - BluetoothDevice device: Classe que representa um dispositivo ja pareado ou scaneado
//		// - Context: A activity atual
//		// - boolean connect: Uma variável que uso para identificar se o cliente criado vai servir como Cliente
//		// ou servidor
//		
//		this.connect = connect;
//		this.context = context;
//		uuid = UUID.fromString(BluetoothConnectionManager.uuid);
//
//		if(device != null)
//		{
//			try {
//				// Aqui nós tentamos criar um socket a partir do device scaneado/pareado
//				// Note que usamos o código de identificação. A conexão só vai funcionar se
//				// dos dois lados o código for o mesmo.
//				mSocket = device.createRfcommSocketToServiceRecord(uuid);
//			} catch (IOException e) {
//
//			}
//		}
//
//		Thread thread = new Thread(this);
//		thread.setPriority(Thread.MIN_PRIORITY);
//		thread.start();
//	
//		Log.i(TAG, "Cliente criado com sucesso");
//
//	}
//
//	public Cliente(BluetoothSocket socket, boolean connect)
//	{
//		// Do servidor
//		
//		// 2 Variaveis:
//		// - BluetoothSocket
//		// - boolean connect: Uma variável que uso para identificar se o cliente criado vai servir como Cliente ou servidor
//		
//		this.connect = connect;
//		uuid = UUID.fromString(BluetoothConnectionManager.uuid);
//		
//		// Apenas pegamos o socket
//		mSocket = socket;
//
//		Thread thread = new Thread(this);
//		thread.setPriority(Thread.MIN_PRIORITY);
//		thread.start();
//		
//		Log.i(TAG, "Cliente criado com sucesso");
//		
//		MinhasCoisas.setClient(this);
//
//
//	}
//
//	@Override
//	public void run() {
//
//		if(BluetoothAdapter.getDefaultAdapter().isDiscovering())
//			BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
//		
//		// conect = true se vem de Conexão Recebida / Cliente
//		if(this.connect){
//			try {
//				mSocket.connect();
//				Log.i(TAG, "Conectado ao dispositivo");
//				BluetoothConnectionManager.Cancel(); 
//				connected = true;
//				Server.getInstance().isServer = false;
//
//			} catch (IOException connectException) {
//
//				connected = false;
//				Log.i(TAG, "Conection failed");
//				
//				try {
//					mSocket.close();
//				} catch (IOException closeException) { }
//				return;
//			}
//		}else
//		{
//			connected = true;
//			// Se vem para cá, a instancia veio da espera do BluetoothConnectionSercer que aceitou um Socket.
//		}
//		
//		Log.i(TAG, String.valueOf(connected));
//
//		if(connected){
//		try{
//
//			this.mInputStream = mSocket.getInputStream();
//			this.mOutputStream = mSocket.getOutputStream();
//
//			Log.i(TAG, " Input/Output Streams pegadas");
//
////			MinhasCoisas.getCurrentActivity().runOnUiThread(new Runnable(){
////				@Override
////				public void run() {
////					// Starts the game scene
////					
////				}
////			});
//
//		}catch(IOException e)
//		{
//			
//		}
//		}
//		while(connected)
//		{
//			try {
//				
//				// Espera para ler alguma mensagem recebida
//				
//				byte[] bytes = new byte[300];
//				mInputStream.read(bytes);
//				Log.i(TAG, "executou o metodo InputStream.read()");
//
//				StringBuilder builder = new StringBuilder();
//				for(int i = 0; i <= bytes.length; i++)
//				{
//					try{ builder.append((char)bytes[i]); }
//					catch(Exception e){ }
//				}
//				String msg = builder.toString();
//
//				// Trabalhe com a mensagem como quiser
//				Log.i(TAG, msg);
//								
//			} catch (IOException e) {
//				Log.i(TAG, "Error: Can't read data from other device");
//				break;
//
//			}
//		}
//	}
//	public void cancel() {
//
//		connected = false;
//	}
//	
//	// Void para enviar mensagem
//	public synchronized void send(String message) {
//		try {
//			byte[] b = message.getBytes();
//			mOutputStream.write(b);
//			mOutputStream.flush();
//			Log.i(TAG, "Message sended");
//		} catch (IOException e) { Log.i(TAG, "Error: Can't send messages"); }
//	}
//
//}
