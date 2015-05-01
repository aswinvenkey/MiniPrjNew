package com.example.aswinvenkat.miniprj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;

/**
 * Created by aswin venkat on 4/15/2015.
 */
public class AboutDev extends ActionBarActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abtdev);
        TextView view = (TextView) findViewById(R.id.textView);
        String newText="<p style=\"font-family:candara\">Development Team<br><ul style=\"list-style-type:square\"><li>1. Ms.J.Jeyaranjini, Assistant Professor, Guide</li><br>\n" +
        "<li>2. Sarath Kumar, GUI Designer</li><br><li>3. Vishnuvardhan, Activity Architect</li><br><li>4. Kishore Kumar, Intense Service Tester</li><br><li>5. Venkatesh, Coder</li><br>\n"+
        "</ul><p style=\"text-align:center\">You can contact us or write to us below, any bugs we are obliged to hear about it, inbox us.</p></p>";
        view.setText(Html.fromHtml(newText));
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_mail);
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(imageView)
                .build();
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"aswinnrt@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Subject of the mail");
                i.putExtra(Intent.EXTRA_TEXT   , "Body of the mail");
                try {
                    startActivity(Intent.createChooser(i, "Send mail."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(AboutDev.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Development Team");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
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

