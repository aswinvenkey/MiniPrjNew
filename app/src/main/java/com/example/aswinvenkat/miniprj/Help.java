package com.example.aswinvenkat.miniprj;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;


public class Help extends ActionBarActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        TextView view = (TextView) findViewById(R.id.textView);
        String newText="<p> There are basically 5 modules in the application each module has a different usage, explained as follows<br><br>\n" +
                "1. ABOUT THE DEPARTMENT<br>\n" +
                "Here the you can view all the details about the department. If you is not satisfied with the information received, you can inbox us from the about developers page to your down right<br> <br>\n" +
                "2. FACULTIES OF THE DEPARTMENT<br>\n" +
                "You can check all the faculties of the department by rank, with a swipe.<br><br>\n" +
                "3. CIRCULAR FEED<br>\n" +
                "You can check all the circulars related to the department here. Please ensure that there is a working data connection so that the circulars can fetched from server. If a data connection is not available you will not be able to use this feature.<br> <br>\n" +
                "4. UPLOAD DOCUMENTS<br>\n" +
                "You can upload your important documents, assignments for submission and also pending tutorials to the department server. Please enter the path of the file in the first text box and the file name with extension in the second and upload the file. Make sure that you have a working data plan.<br><br>\n" +
                "5. INTERACTION FORUM<br>\n" +
                "Here you can interact with other students from our department and not only that you can share an informative article, a blog post you liked, a tweet you re tweeted etc. As long it is with in 140 words, if not you can post that in your blog, of you don't have one then inbox us the article. We will post it for you. Make sure again that you have a working data plan.<br><br>\n" +
                "Note : A student has to register before the student starts to post articles in the forum. After he has registered he can post articles and interact with other students<br>\n" +
                "Hope you have fun. Ciao!\n" +
                "</p>";
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
                    Toast.makeText(Help.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Help");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
}
