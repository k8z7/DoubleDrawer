package net.kbh.doubledrawer;

import android.support.v4.view.GravityCompat;
import android.view.View;

public class Util {

    // ContentsView 열기
    static void openLayer() {
        if (MainActivity.drawer.isDrawerOpen(GravityCompat.START)) { MainActivity.drawer.closeDrawer(GravityCompat.START); }
        MainActivity.main_drawer.openDrawer(MainActivity.slider);
        MainActivity.slider.setVisibility(View.VISIBLE);
    }

    // ContentsView 닫기
    static void closeLayer() {
        MainActivity.main_drawer.closeDrawer(MainActivity.slider);
        MainActivity.slider.setVisibility(View.GONE);
    }

    // 페이지 레이아웃 열기
    static void openPage(View view) {
        // 서랍(drawer) 닫기
        if (MainActivity.drawer.isDrawerOpen(GravityCompat.START)) { MainActivity.drawer.closeDrawer(GravityCompat.START); }
        if (MainActivity.slider.getVisibility() == View.VISIBLE) { closeLayer(); }
        // 모든 페이지 닫기
        int id = view.getId();
        for(View page: MainActivity.views) page.setVisibility(View.GONE);
        if (id==R.id.action_search) { MainActivity.inc_search.setVisibility(View.VISIBLE); MainActivity.searchMenu.setIcon(R.drawable._search_on); } else { MainActivity.searchMenu.setIcon(R.drawable._search_off); }
        if (id==R.id.action_explorer) { MainActivity.inc_explorer.setVisibility(View.VISIBLE); MainActivity.explorerMenu.setIcon(R.drawable._ex_on); } else { MainActivity.explorerMenu.setIcon(R.drawable._ex_off); }
        if (id==R.id.action_bmfh) { MainActivity.inc_bmfh.setVisibility(View.VISIBLE); } else { }
        if (id==R.id.action_help) { MainActivity.inc_help.setVisibility(View.VISIBLE); } else { }
        if (id==R.id.action_setting) { MainActivity.inc_setting.setVisibility(View.VISIBLE); } else { }
        // 파라미터 페이지 열기
        view.setVisibility(View.VISIBLE);
    }

}