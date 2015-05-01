package com.example.aswinvenkat.miniprj;

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
import android.widget.TextView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

/**
 * Created by aswin venkat on 4/7/2015.
 */
public class NewActivity extends ActionBarActivity implements View.OnClickListener{

    private static final String TAG_HELP = "help";
    private static final String TAG_SETTINGS = "settings";
    private static final String TAG_ABTDEV = "abtdev";
    private static final String TAG_EXIT = "exit";
    private static final String TAG_ARTICLE = "post";
    private EditText editText;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Interaction Forum Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editText = (EditText)findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        Button postArticle = (Button)findViewById(R.id.button2);
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
        postArticle.setTag(TAG_ARTICLE);
        buttonAboutDevelopers.setOnClickListener(this);
        buttonClose.setOnClickListener(this);
        buttonHelp.setOnClickListener(this);
        postArticle.setOnClickListener(this);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getTag().equals(TAG_ABTDEV)){
            Intent startDevelopers = new Intent(NewActivity.this,AboutDev.class);
            startActivity(startDevelopers);
        }
        if(v.getTag().equals(TAG_EXIT)){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        if(v.getTag().equals(TAG_HELP)){
            Intent startHelp = new Intent(NewActivity.this,Help.class);
            startActivity(startHelp);
        }
        if (v.getTag().equals(TAG_ARTICLE)){
            String article = editText.getText().toString();
            textView.setText(article);
        }
    }
}
