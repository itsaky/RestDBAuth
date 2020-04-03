package com.itsaky.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.itsaky.restdbauth.library.User;
import com.itsaky.restdbauth.library.UserManager;
import com.itsaky.restdbauth.library.callbacks.SignUpCallback;
import com.itsaky.restdbauth.library.callbacks.LoginCallback;
import android.app.ProgressDialog;

public class SignupActivity extends AppCompatActivity 
{
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        toolbar = (Toolbar) findViewById(R.id.mainToolbar);

        setSupportActionBar(toolbar);
    }
	
	public void signUpUser(View v){
		EditText et = findViewById(R.id.layoutloginEditText1);
		EditText et2 = findViewById(R.id.layoutloginEditText2);
		EditText et3 = findViewById(R.id.layoutloginEditText3);
		EditText et4 = findViewById(R.id.layoutloginEditText4);
		
		final String name = et.getText().toString().trim();
		final String email = et2.getText().toString().trim();
		final String pass = et3.getText().toString().trim();
		final String username = et4.getText().toString().trim();
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setName(name);
		user.setPassword(pass);
		user.setIsEmailVerified(false);
		
		final ProgressDialog pd = new ProgressDialog(this);
		pd.setMessage("Please wait");
		pd.show();
		
		UserManager.signUpUser(this, user, new SignUpCallback(){

				@Override
				public void onSuccess(User p1) {
					pd.dismiss();
					toast("User created");
					login(p1);
				}

				@Override
				public void onFailure(String p1, String p2) {
					pd.dismiss();
					toast(p1);
				}
			});
	}
	
	private void toast(String s) {
		Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
	}
	
	private void login(User user){
		UserManager.logInUser(this, user, new LoginCallback(){

				@Override
				public void onSuccess() {
					startActivity(new Intent(getApplicationContext(), MainActivity.class));
					finish();
				}

				@Override
				public void onFailure(String p1) {
					toast(p1);
				}
			});
	}
	
}
