package com.itsaky.auth;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import com.itsaky.restdbauth.library.UserManagerConfig;
import com.itsaky.restdbauth.library.UserManager;
import com.itsaky.restdbauth.library.User;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import com.itsaky.restdbauth.library.callbacks.LoginCallback;
import android.widget.Toast;
import android.app.ProgressDialog;

public class LoginActivity extends AppCompatActivity 
{
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = (Toolbar) findViewById(R.id.mainToolbar);

        setSupportActionBar(toolbar);
		
		User user = UserManager.getCurrentUser();
		
		if(user != null){
			startActivity(new Intent(this, MainActivity.class));
		}
    }
	
	public void loginUser(View v){
		EditText et = findViewById(R.id.layoutloginEditText1);
		EditText et2 = findViewById(R.id.layoutloginEditText2);
		
		final String email = et.getText().toString().trim();
		final String pass = et2.getText().toString().trim();
		
		User user = new User();
		user.setEmail(email);
		user.setUsername(email);
		
		user.setPassword(pass);
		
		final ProgressDialog pd = new ProgressDialog(this);
		pd.setMessage("Please wait");
		pd.show();
		
		UserManager.logInUser(this, user, new LoginCallback(){

				@Override
				public void onSuccess() {
					pd.dismiss();
					toast("Logged in");
					startActivity(new Intent(getApplicationContext(), MainActivity.class));
					finish();
				}

				@Override
				public void onFailure(String p1) {
					toast(p1);
					pd.dismiss();
				}
			});
	
	}
	
	public void signUp(View v){
		startActivity(new Intent(this, SignupActivity.class));
	}
	
	private void toast(String s) {
		Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
	}
}
