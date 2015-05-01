package com.example.aswinvenkat.miniprj;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.poliveira.parallaxrecycleradapter.ParallaxRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aswin venkat on 4/5/2015.
 */
public class RView extends ActionBarActivity implements View.OnClickListener {
    private ViewPager mPager;
    private SlidingTabLayout mTabs;
    private static final String TAG_HELP = "help";
    private static final String TAG_SETTINGS = "settings";
    private static final String TAG_ABTDEV = "abtdev";
    private static final String TAG_EXIT = "exit";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Faculties");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPager = (ViewPager)findViewById(R.id.pager);
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabs.setViewPager(mPager);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_more);
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(imageView)
                .build();

        ImageView iconSettings = new ImageView(this);
        iconSettings.setImageResource(R.drawable.ic_settings);
        ImageView iconAboutDevelopers = new ImageView(this);
        iconAboutDevelopers.setImageResource(R.drawable.ic_abtdev);
        ImageView iconHelp = new ImageView(this);
        iconHelp.setImageResource(R.drawable.ic_help);
        ImageView iconClose = new ImageView(this);
        iconClose.setImageResource(R.drawable.ic_close);
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        SubActionButton buttonSettings = itemBuilder.setContentView(iconSettings).build();
        SubActionButton buttonAboutDevelopers = itemBuilder.setContentView(iconAboutDevelopers).build();
        SubActionButton buttonHelp = itemBuilder.setContentView(iconHelp).build();
        SubActionButton buttonClose = itemBuilder.setContentView(iconClose).build();
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(buttonSettings)
                .addSubActionView(buttonAboutDevelopers)
                .addSubActionView(buttonHelp)
                .addSubActionView(buttonClose)
                .attachTo(actionButton)
                .build();
        buttonAboutDevelopers.setTag(TAG_ABTDEV);
        buttonClose.setTag(TAG_EXIT);
        buttonHelp.setTag(TAG_HELP);
        buttonSettings.setTag(TAG_SETTINGS);
        buttonAboutDevelopers.setOnClickListener(this);
        buttonClose.setOnClickListener(this);
        buttonHelp.setOnClickListener(this);
        buttonSettings.setOnClickListener(this);
    }
    class MyPagerAdapter extends FragmentPagerAdapter {
        String[] tabs;
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabs = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int position) {
            MyFragment myFragment = MyFragment.getInstance(position);
            return myFragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public static class MyFragment extends Fragment{
        private TextView textView;
        public static MyFragment getInstance(int position){
            MyFragment myFragment = new MyFragment();
            Bundle args = new Bundle();
            args.putInt("position",position);
            myFragment.setArguments(args);
            return myFragment;
        }
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View layout = inflater.inflate(R.layout.fragment,container, false);
            textView = (TextView)layout.findViewById(R.id.position);
            Bundle bundle = getArguments();
            if(bundle!=null){
                textView.setText("The page selected is " +bundle.getInt("position"));

            }
            return layout;
        }
    }


    @Override
    public void onClick(View v) {

        if(v.getTag().equals(TAG_ABTDEV)){
            Intent startDevelopers = new Intent(RView.this,AboutDev.class);
            startActivity(startDevelopers);
        }
        if(v.getTag().equals(TAG_EXIT)){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        if(v.getTag().equals(TAG_HELP)){
            Toast.makeText(this, "Help was clicked", Toast.LENGTH_SHORT).show();
        }
        if(v.getTag().equals(TAG_SETTINGS)){
            Toast.makeText(this,"Settings was clicked",Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
