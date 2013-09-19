//package com.example.sobreviva.multiplayer.bluetooth;
//
//import java.io.IOException;
//import java.util.UUID;
//
//import com.example.sobreviva.activity.MainActivity;
//import com.example.sobreviva.multiplayer.util.DialogHelper;
//
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothServerSocket;
//import android.bluetooth.BluetoothSocket;
//import android.content.Context;
//import android.content.Intent;
//import android.util.Log;
//
//public class BluetoothConnectionManager implements Runnable{
//
//	// Para receber o socket do outro dispositivo
//	private BluetoothServerSocket mBtServerSocket;
//	
//	// O Socket que receberá
//	private BluetoothSocket btSocket;
//	
//	private final String NAME = "LOVE";
//	
//	// Código utilizado para autenticar a conexão. É utilizado em conexões bluetooth.
//	private final UUID mUUID;
//	public static final String uuid = "b4c5bcd0-a9ec-11e2-9e96-0800200c9a67";
//
//	private final String TAG = "Bluetooth Connection Manager";
//	
//	private static boolean alive = true;
//	
//	private Context context;
//	
//	public BluetoothServerSocket getmBtServerSocket() {
//		return mBtServerSocket;
//	}
//
//	public boolean isAlive() {
//		return alive;
//	}
//
//	public void setAlive(boolean alive) {
//		this.alive = alive;
//	}
//
//	public BluetoothConnectionManager(Context context) {
//		
//		this.context = context;
//		mUUID = UUID.fromString(uuid);
//		
//		BluetoothServerSocket tmp = null;
//		
//		try {
//			// Preparamos o BluetoothServerSocket para ouvir uma conexão desta UUID.
//			tmp = BluetoothAdapter.getDefaultAdapter().listenUsingRfcommWithServiceRecord(NAME, mUUID);
//		} catch (IOException e) {
//			DialogHelper.message(MainActivity.GetInstance(), "Erro ao criar servidor Bluetooth");
//		
//		}
//		mBtServerSocket = tmp;
//		
//		Thread thread = new Thread(this);
//		thread.setPriority(Thread.MIN_PRIORITY);
//		thread.start();
//		
//		Log.i(TAG, "Bluetooth Connection Manager criado com sucesso");
//	}
//
//	@Override
//	public void run() {
//		try {
//		
//			// Esperamos para receber um socket de conexão. Bem semelhante ao TCPClient.accept() / ServerSocket.accept() que estudamos ano passado.
//			btSocket = mBtServerSocket.accept();
//			Log.i(TAG, "Aceitou uma nova conexão");
//		} catch (IOException e) {
//			
//			Log.i(TAG, "Erro ao tentar receber socket de conexão");
//			e.printStackTrace();
//		} 
//			
//		if(btSocket != null)
//		{
//			// Instanciamos o Cliente
//			// A variável booleana (false) significa que instanciamos um Cliente que recebemos por accept();
//			Cliente cs = new Cliente(btSocket, false);
//			
//			try {
//				
//				// É importante fechá-lo, já que não usaremos mais.
//				mBtServerSocket.close();
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}					
//			
//		}
//	}		
//	
//	public static void Cancel()
//	{
//		alive = false;
//	}
//}
