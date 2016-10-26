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

    static LinearLayout inc_main, inc_explorer, inc_bmfh, inc_setting, inc_search, inc_help;
    static View[] views = null; // inc_tts, slider 외의 모든 루트뷰 배열
    static DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // ActionBar actionBar = getSupportActionBar();
        // actionBar.setDisplayShowTitleEnabled(false);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initView();
    }

    // View 가져오기
    private void initView() {
        main_drawer = (DrawerLayout)findViewById(R.id.main_drawer);
        slider = (LinearLayout)findViewById(R.id.slider);

        inc_main = (LinearLayout) findViewById(R.id.inc_main);
        inc_explorer = (LinearLayout) findViewById(R.id.inc_explorer);
        inc_bmfh = (LinearLayout) findViewById(R.id.inc_bmfh);
        inc_setting = (LinearLayout) findViewById(R.id.inc_setting);
        inc_search = (LinearLayout) findViewById(R.id.inc_search);
        inc_help = (LinearLayout) findViewById(R.id.inc_help);

        views = new View[]{inc_explorer, inc_bmfh, inc_setting, inc_search, inc_help};
    }

    // BackPress(백키) 처리
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (slider.getVisibility() == View.VISIBLE) { Util.closeLayer(); }
            else {
                View opened = null;
                for(View page:views) {
                    if(page.getVisibility() == View.VISIBLE) {
                        opened = page;
                        break;
                    }
                }
                if (opened != null) {
                    Util.openPage(inc_main);
                }
                else {
                    // if (mTTS.isSpeaking()) Util.stopTTS();
                    // else finish();
                    finish();
                }
            }
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
                if (slider.getVisibility() == View.VISIBLE) { Util.closeLayer(); } else { Util.openLayer(); }
                break;
            case (R.id.action_speak) :
                if (isSpeaker) { speechMenu.setIcon(R.drawable._speaker_off); } else { speechMenu.setIcon(R.drawable._speaker_on); }
                isSpeaker = ! isSpeaker;
                break;
            case (R.id.action_search) :
                if (inc_search.getVisibility()==View.GONE) { Util.openPage(inc_search); } else { Util.openPage(inc_main); }
                break;
            case (R.id.action_explorer) :
                if (inc_explorer.getVisibility()==View.GONE) { Util.openPage(inc_explorer); } else { Util.openPage(inc_main); }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_select) { if (slider.getVisibility() == View.VISIBLE) { Util.closeLayer(); } else { Util.openLayer(); }
        } else if (id == R.id.action_speak) { if (isSpeaker) { speechMenu.setIcon(R.drawable._speaker_off); } else { speechMenu.setIcon(R.drawable._speaker_on);
            isSpeaker = ! isSpeaker; }
        } else if (id == R.id.action_search) { if (inc_search.getVisibility()==View.GONE) { Util.openPage(inc_search); } else { Util.openPage(inc_main); }
        } else if (id == R.id.action_explorer) { if (inc_explorer.getVisibility()==View.GONE) { Util.openPage(inc_explorer); } else { Util.openPage(inc_main); }
        }
        else if (id == R.id.action_bmSave) { Toast.makeText(this, "action_bmSave", Toast.LENGTH_SHORT).show(); }
        else if (id == R.id.action_setting) { if (inc_setting.getVisibility()==View.GONE) { Util.openPage(inc_setting); } else { Util.openPage(inc_main); } }
        else if (id == R.id.action_bmfh) { if (inc_bmfh.getVisibility()==View.GONE) { Util.openPage(inc_bmfh); } else { Util.openPage(inc_main); } }
        else if (id == R.id.action_help) { if (inc_help.getVisibility()==View.GONE) { Util.openPage(inc_help); } else { Util.openPage(inc_main); } }
        else if (id == R.id.action_install) { Toast.makeText(this, "action_install", Toast.LENGTH_SHORT).show(); }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}