package com.example.aswinvenkat.miniprj;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;

/**
 * Created by aswin venkat on 3/10/2015.
 */
public class HomeScreen extends ActionBarActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private static final String TAG_HELP = "help";
    private static final String TAG_SETTINGS = "settings";
    private static final String TAG_ABTDEV = "abtdev";
    private static final String TAG_EXIT = "exit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.dlay), toolbar);
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
            Intent startDevelopers = new Intent(HomeScreen.this,AboutDev.class);
            startActivity(startDevelopers);
        }
        if(v.getTag().equals(TAG_EXIT)){
            finish();
        }
        if(v.getTag().equals(TAG_HELP)){
            Intent startHelp = new Intent(HomeScreen.this,Help.class);
            startActivity(startHelp);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}