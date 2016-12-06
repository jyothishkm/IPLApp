package com.example.bridgeit.ipl_app;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bridgeit.ipl_app.fragment.TeamFragment;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String mLogin_name = "ipl";
    private String mLogin_pwd = "ipl";
    private EditText mName, mPassword;
    private Button mLogin;
    ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mName = (EditText)findViewById(R.id.mEdit_name);
        mPassword = (EditText) findViewById(R.id.mEdit_pwd);

        mLogin = (Button) findViewById(R.id.mButton_login);
        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name = mName.getText().toString();
        String pwd = mPassword.getText().toString();

        if ((mLogin_name.equalsIgnoreCase(name)) && (mLogin_pwd.equalsIgnoreCase(pwd))) {
            showProgress();
            getSupportFragmentManager().beginTransaction().replace(R.id.mFrame_layout, new TeamFragment(mDialog)).commit();
            mName.setText("");
            mPassword.setText("");
            Toast.makeText(MainActivity.this, "login Successfully", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(MainActivity.this, "login fail", Toast.LENGTH_SHORT).show();

            mPassword.setText("");
            mName.setText("");
        }
    }
    public void showProgress() {
        mDialog  = new ProgressDialog(this);
        mDialog.setMessage("Downloading Data please wait");
        mDialog.setCancelable(false);
        mDialog.show();
    }
}

