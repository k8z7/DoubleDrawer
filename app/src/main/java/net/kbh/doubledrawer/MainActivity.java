package net.kbh.doubledrawer;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static LinearLayout slider;
    static DrawerLayout main_drawer;

    static MenuItem speechMenu, explorerMenu, searchMenu;
    boolean isSpeaker = false, isSearch = false, isExplorer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // ActionBar actionBar = getSupportActionBar();
        // actionBar.setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        main_drawer = (DrawerLayout)findViewById(R.id.main_drawer);
        slider = (LinearLayout)findViewById(R.id.slider);
    }

    // ContentsView 열기
    static void openLayer() {
        main_drawer.openDrawer(slider);
        slider.setVisibility(View.VISIBLE);
    }

    // ContentsView 닫기
    static void closeLayer() {
        main_drawer.closeDrawer(slider);
        slider.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (slider.getVisibility() == View.VISIBLE) { closeLayer(); }
            else { super.onBackPressed(); }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        speechMenu = menu.findItem(R.id.action_speak);
        explorerMenu = menu.findItem(R.id.action_explorer);
        searchMenu = menu.findItem(R.id.action_search);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case (R.id.action_select) :
                if (slider.getVisibility() == View.VISIBLE) { closeLayer(); } else { openLayer(); }
                break;
            case (R.id.action_speak) :
                if (isSpeaker) { speechMenu.setIcon(R.drawable._speaker_off); } else { speechMenu.setIcon(R.drawable._speaker_on); }
                isSpeaker = ! isSpeaker;
                break;
            case (R.id.action_search) :
                if (isSearch) { searchMenu.setIcon(R.drawable._search_off); } else { searchMenu.setIcon(R.drawable._search_on); }
                isSearch = ! isSearch;
                break;
            case (R.id.action_explorer) :
                if (isExplorer) { explorerMenu.setIcon(R.drawable._ex_off); } else { explorerMenu.setIcon(R.drawable._ex_on); }
                isExplorer = ! isExplorer;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_select) { if (slider.getVisibility() == View.VISIBLE) { closeLayer(); } else { openLayer(); }
        } else if (id == R.id.action_speak) { if (isSpeaker) { speechMenu.setIcon(R.drawable._speaker_off); } else { speechMenu.setIcon(R.drawable._speaker_on);
            isSpeaker = ! isSpeaker; }
        } else if (id == R.id.action_search) { if (isSearch) { searchMenu.setIcon(R.drawable._search_off); } else { searchMenu.setIcon(R.drawable._search_on);
            isSearch = ! isSearch; }
        } else if (id == R.id.action_explorer) { if (isExplorer) { explorerMenu.setIcon(R.drawable._ex_off); } else { explorerMenu.setIcon(R.drawable._ex_on);
            isExplorer = ! isExplorer; }
        }
        else if (id == R.id.action_bmSave) { Toast.makeText(this, "action_bmSave", Toast.LENGTH_SHORT).show(); }
        else if (id == R.id.action_setSpeech) { Toast.makeText(this, "action_setSpeech", Toast.LENGTH_SHORT).show(); }
        else if (id == R.id.action_bmfh) { Toast.makeText(this, "action_bmfh", Toast.LENGTH_SHORT).show(); }
        else if (id == R.id.action_help) { Toast.makeText(this, "action_help", Toast.LENGTH_SHORT).show(); }
        else if (id == R.id.action_install) { Toast.makeText(this, "action_install", Toast.LENGTH_SHORT).show(); }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}