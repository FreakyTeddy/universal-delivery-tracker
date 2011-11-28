package ar.com.utreporter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import ar.com.utreporter.config.AppUserData;
import ar.com.utreporter.utils.DataHelper;
import ar.com.utreporter.utils.DialogFactory;

public class LoginActivity extends Activity {

	private Button 	 loginButton;
	private EditText email;
	private EditText password;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.login);
		email = (EditText) findViewById(R.id.email);
		password = (EditText) findViewById(R.id.password);
		loginButton = (Button) findViewById(R.id.login_button);
		loginButton.setOnClickListener(loginClickListener);
		String e = AppUserData.getInstance().lastusername;
		if (e.length() > 0) {
			email.setText(e);
		}
	}

	private final OnClickListener loginClickListener = new OnClickListener() {
		public void onClick(View v) {
			if (v == loginButton) {
				if (DataHelper.login(email.getText().toString(), password
						.getText().toString())) {
					AppUserData.getInstance().esInvitado = false;
					AppDataLocalization app = (AppDataLocalization) getApplication();
					AppUserData.getInstance().lastusername = email.getText()
							.toString();
					app.saveConfig();
					Intent i = new Intent(LoginActivity.this,
							HomeActivity.class);
					startActivity(i);
				} else {
					DialogFactory.getFactory().getDialogAcept(
							LoginActivity.this, "ERROR",
							"Los datos ingresados son incorrectos.");
				}
			} 
		}
	};

}
