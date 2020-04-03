package com.itsaky.auth;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import com.itsaky.restdbauth.library.UserManager;
import com.itsaky.restdbauth.library.User;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity 
{
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.mainToolbar);

        setSupportActionBar(toolbar);
        
        User user = UserManager.getCurrentUser();
		
		((TextView)findViewById(R.id.activitymainTextView1)).setText(user.getUsername());
        
    }
}
