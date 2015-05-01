package com.example.aswinvenkat.miniprj;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements Adapter.ClickListner {

    private RecyclerView recyclerView;
    private Adapter adapter;
    public static final String PREF_FILE_NAME = "testpref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View containerView;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readFromPrefrences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState != null) {
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        adapter = new Adapter(getActivity(), getData());
        adapter.setClickListner(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public static List<Manager> getData() {
        List<Manager> data = new ArrayList<>();
        int[] icons = {R.drawable.icon_abtus,R.drawable.icon_faculty ,R.drawable.icon_circular, R.drawable.icon_document_upload, R.drawable.icon_interaction};
        String[] titles = {"About The Department","Faculties of the department", "Circular Feed", "Upload Documents", "Interaction Forum"};
        for (int i = 0; i < titles.length && i < icons.length; i++) {
            Manager current = new Manager();
            current.iconId = icons[i];
            current.title = titles[i];
            data.add(current);
        }
        return data;
    }


    public void setUp(int Fragment_id, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(Fragment_id);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    saveToPrefrences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();

            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }
        };
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });


    }

    public static void saveToPrefrences(Context context, String prefrenceName, String prefrenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(prefrenceName, prefrenceName);
        editor.apply();
    }

    public static String readFromPrefrences(Context context, String prefrenceName, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(prefrenceName, defaultValue);
    }

    @Override
    public void itemClicked(View view, int position) {
        if (position == 0) {
            startActivity(new Intent(getActivity(), SingleViewActivity.class));
        } else if (position == 1) {
            startActivity(new Intent(getActivity(), TabLayout.class));
        } else if (position == 2) {
            startActivity(new Intent(getActivity(), Circular.class));
        } else if (position == 3) {
            startActivity(new Intent(getActivity(), StaticFileUpload.class));
        }
        else if (position == 4){
            startActivity(new Intent(getActivity(), MainActivity.class));

        }
    }
}
