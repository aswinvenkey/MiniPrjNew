package com.example.aswinvenkat.miniprj;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.sql.SQLException;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG_HELP = "help";
    private static final String TAG_SETTINGS = "settings";
    private static final String TAG_ABTDEV = "abtdev";
    private static final String TAG_EXIT = "exit";
    private Toolbar toolbar;
    Button btnSignIn,btnSignUp;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Interaction Forum");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_more);
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(imageView)
                .build();

        ImageView iconAboutDevelopers = new ImageView(this);
        iconAboutDevelopers.setImageResource(R.drawable.ic_abtdev);
        ImageView iconHelp = new ImageView(this);
        iconHelp.setImageResource(R.drawable.ic_help);
        ImageView iconClose = new ImageView(this);
        iconClose.setImageResource(R.drawable.ic_close);
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        SubActionButton buttonAboutDevelopers = itemBuilder.setContentView(iconAboutDevelopers).build();
        SubActionButton buttonHelp = itemBuilder.setContentView(iconHelp).build();
        SubActionButton buttonClose = itemBuilder.setContentView(iconClose).build();
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(buttonAboutDevelopers)
                .addSubActionView(buttonHelp)
                .addSubActionView(buttonClose)
                .attachTo(actionButton)
                .build();
        buttonAboutDevelopers.setTag(TAG_ABTDEV);
        buttonClose.setTag(TAG_EXIT);
        buttonHelp.setTag(TAG_HELP);
        buttonAboutDevelopers.setOnClickListener(this);
        buttonClose.setOnClickListener(this);
        buttonHelp.setOnClickListener(this);

        // create a instance of SQLite Database
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        try {
            loginDataBaseAdapter=loginDataBaseAdapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Get The Refference Of Buttons
        btnSignIn=(Button)findViewById(R.id.buttonSignIN);
        btnSignUp=(Button)findViewById(R.id.buttonSignUP);

        // Set OnClick Listener on SignUp button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  and Start The Activity
                Intent intentSignUP=new Intent(getApplicationContext(),SignUPActivity.class);
                startActivity(intentSignUP);
            }
        });
    }
    public void signIn(View V)
    {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.login);
        dialog.setTitle("Login");

        // get the Refferences of views
        final  EditText editTextUserName=(EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
        final  EditText editTextPassword=(EditText)dialog.findViewById(R.id.editTextPasswordToLogin);

        Button btnSignIn=(Button)dialog.findViewById(R.id.buttonSignIn);

        // Set On ClickListener
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();

                // fetch the Password form database for respective user name
                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);

                // check if the Stored password matches with  Password entered by user
                if(password.equals(storedPassword))
                {
                    Toast.makeText(MainActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(),
                            NewActivity.class);
                    startActivity(i);
                    dialog.dismiss();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });

        dialog.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if(v.getTag().equals(TAG_ABTDEV)){
            Intent startDevelopers = new Intent(MainActivity.this,AboutDev.class);
            startActivity(startDevelopers);
        }
        if(v.getTag().equals(TAG_EXIT)){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        if(v.getTag().equals(TAG_HELP)){
            Intent startHelp = new Intent(MainActivity.this,Help.class);
            startActivity(startHelp);
                }
    }
}
