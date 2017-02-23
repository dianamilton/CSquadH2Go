package com.example.dianamilton.h2go;

import butterknife.Bind;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.dianamilton.h2go.LoginActivity;
import com.example.dianamilton.h2go.R;

/**
 * page for logout screen after login
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LogoutActivity"; //defines activity
    @Bind(R.id.btn_logout) Button _logoutButton; //defines logout button

    /**
     * creating button for logout
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        ButterKnife.bind(this);

        /**
         * sets up Logout Button, calls for switch screen to login page
         */
        _logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}