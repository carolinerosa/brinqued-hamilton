package com.example.sobreviva.multiplayer.server;



import com.example.sobreviva.multiplayer.util.Conexao;
import com.example.sobreviva.multiplayer.util.Const;
import com.example.sobreviva.multiplayer.util.DepoisDeReceberDados;

import android.util.Log;

public class TratadorDeRedeECO implements DepoisDeReceberDados {

	public void execute(Conexao origem, String linha) {

		Log.i(Const.TAG, "<<" + linha);
		if (linha != null) {
			origem.write("eco : " + linha);
		}

	}

}
