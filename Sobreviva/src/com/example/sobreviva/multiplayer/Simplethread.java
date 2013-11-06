package com.example.sobreviva.multiplayer;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.example.sobreviva.activity.MainActivity;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class Simplethread extends Thread
{
	  	private Connection conn = null;
	    private Statement st;
	    private ResultSet rs;
	    private String sql;
	    Handler mHandler;
		
		public void run()
		{
			Looper.prepare();
			  mHandler = new Handler()
			  {
			    public void handleMessage(Message msg){}
			      
			  };
			  //SQLiteDatabase dbmysql = new SQLiteDatabase();
			  dbMySQL dbmysql = new dbMySQL();
//			//dbmysql.conectarMySQL(this,"localhost","3306","usuarios", "root", "1322412345");
//				
		      dbmysql.conectarMySQL(MainActivity.GetInstance(),"db4free.net","3306","survive", "emanoel", "1322412345"); // ip do servidor mysql, porta, banco, usuário, senha
			  
			  mHandler.handleMessage(null); //send ourself a message so the looper can stop itself
			  Looper.loop();
			    
			
//	       // dbmysql.queryMySQL();
	        //dbmysql.desconectarMySQL();
//	       // dbmysql.EnviarDados();

			//Toast.makeText(MainActivity.context, "MySQL conexão feita com sucesso.", Toast.LENGTH_SHORT).show();
			
			
		}


private class dbMySQL{
	   
	    
	    public void conectarMySQL(Context context, String host, String porta, String banco, String usuario, String senha){
	        try
	        {
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	              //Toast.makeText(MainActivity.context, "BUUUUUUUUUUUUUUUUUUUUUUUUUUU", Toast.LENGTH_SHORT).show(); 
	        }
	        catch(Exception erro)
	        {
	            Log.e("MYSQL","Erro: "+erro);
	            //Toast.makeText(MainActivity.context, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", Toast.LENGTH_SHORT).show(); 
	        }
	        
	        try
	        {
	            conn=DriverManager.getConnection("jdbc:mysql://"+ host +":" + porta + "/" + banco, usuario, senha);
	            Log.i("MYSQL","Conectado.");
	            //Toast.makeText(MainActivity.context, "MySQL conexão feita com sucesso.", Toast.LENGTH_SHORT).show(); 
	        }
	        catch(Exception erro)
	        {
	            Log.e("MYSQL","Erro: "+erro);
	            //Toast.makeText(MainActivity.context, "deu ruim menor", Toast.LENGTH_SHORT).show();
	        }
	    }
	    
	    public void desconectarMySQL(){
	        try {
	            conn.close();
	            Log.i("MYSQL","Desconectado.");
	        } catch (Exception erro) {
	            Log.e("MYSQL","Erro: "+erro);
	        }
	    }
	    
	    public void queryMySQL(){
	        try{
	            st=conn.createStatement();
	            sql="SELECT * FROM tabela1";
	            rs=st.executeQuery(sql);
	            rs.first();
	            Log.i("MYSQL","Resultado: "+rs.getString("Name"));
	            
	    
	            //Criar um metodo para setar o recorde do jogador com os codigos abaixo parecido com os acima
	            // sql= " insert" + nome variavel ;
	            // st.executeUpdate(sql);
	            
	            
	        } catch (Exception erro){
	            Log.e("MYSQL","Erro: "+erro);
	        }
	    }
	    
	    public void EnviarDados()
	    {
	    	try
	    	{
	    	st = conn.createStatement();
	    	sql = "CREATE TABLE table1";
	    	st.executeQuery(sql);
	    	}
	    	
	    	catch (Exception erro){
	    		Log.e("MYSQL","Erro: " + erro);
	    	}
	    }
}
}
