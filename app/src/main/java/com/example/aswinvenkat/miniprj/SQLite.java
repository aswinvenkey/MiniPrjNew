package com.example.aswinvenkat.miniprj;

import android.app.Dialog;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;


public class SQLite extends ActionBarActivity implements View.OnClickListener {
    Button bReg;
    EditText name, regno, pass, cpass, mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        bReg = (Button) findViewById(R.id.bSignup);
        name = (EditText) findViewById(R.id.etName);
        regno = (EditText) findViewById(R.id.etRegno);
        pass = (EditText) findViewById(R.id.etPass);
        cpass = (EditText) findViewById(R.id.etCpass);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Registration Page");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mail = (EditText) findViewById(R.id.etMail);
        bReg.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sqlite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        boolean work = true;
        try {
            String Name = name.getText().toString();
            String regnu = regno.getText().toString();
            String spass = pass.getText().toString();
            String scpass = cpass.getText().toString();
            String smail = mail.getText().toString();
            Db entry = new Db(SQLite.this);
            entry.open();
            entry.create(Name, regnu, spass, scpass, smail);
            entry.close();
        } catch (Exception e) {
            work = false;
        } finally {
            if (work) {
                Dialog d = new Dialog(this);
                d.setTitle("SQL Result");
                TextView tv = new TextView(this);
                tv.setText("Success");
                d.setContentView(tv);
            }
        }
    }
}
