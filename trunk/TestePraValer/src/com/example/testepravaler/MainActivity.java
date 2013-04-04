package com.example.testepravaler;

import android.app.Activity;
import android.os.Bundle;
// import android.util.Log;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new Nel(this));
	}

	
	

}
