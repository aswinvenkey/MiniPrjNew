package com.example.aswinvenkat.miniprj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

/**
 * Created by aswin venkat on 3/16/2015.
 */
public class SingleViewActivity extends ActionBarActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private static final String TAG_HELP = "help";
    private static final String TAG_SETTINGS = "settings";
    private static final String TAG_ABTDEV = "abtdev";
    private static final String TAG_EXIT = "exit";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);
        TextView view = (TextView) findViewById(R.id.textView);
        String fText = "\n" +
                "<p style=\"font-family:candara\">Department of Computer Science and Engineering was established in the year 1984.<br>\n" +
                "The department has well equipped laboratory facilities including <strong>OS, DBMS, Computer Graphics, Multimedia, Internet Programming, Entry Level laboratories</strong> to provide the practical training to the students.<br>\n" +
                "Also, the students are given the opportunity to work in the Network Technology and Network Security Laboratories attached with the TIFAC-CORE in Network Engineering.<br>\n" +
                "Highly qualified and experienced faculty with specialization in <strong>Data Mining, Image Processing, Software Engineering, Natural language processing, Web Technology, Embedded systems, Evolutionary computing, Network Technology and Distributed Computing</strong> are working in the department.<br>\n" +
                "The Students are given exposure to latest trends in technology including <strong>Embedded system design, Big Data Analytics, Image Processing, Extreme Programming, Software Testing, Sensor Networks, Cloud Computing, Soft Computing</strong> etc. through workshops, guest lectures and seminars.<br>\n" +
                "Many projects were carried out in association with leading software industries and research laboratories including Naturesoft, Pentasoft, Hexaware, CTS, HCL, Sathyam, IGCAR, CECRI, CLRI, Ramco Systems, Wipro, TCS, Flextronics, etc.<br></p> \n";
        view.setText(Html.fromHtml(fText));
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("About The Department");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
    }

    @Override
    public void onClick(View v) {
        if(v.getTag().equals(TAG_ABTDEV)){
            Intent startDevelopers = new Intent(SingleViewActivity.this, AboutDev.class);
            startActivity(startDevelopers);
        }
        if(v.getTag().equals(TAG_EXIT)){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        if(v.getTag().equals(TAG_HELP)){
            Intent startHelp = new Intent(SingleViewActivity.this,Help.class);
            startActivity(startHelp);
        }
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

}
