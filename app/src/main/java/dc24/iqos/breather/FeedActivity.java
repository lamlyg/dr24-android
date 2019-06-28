package dc24.iqos.breather;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FeedActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final ConnectInfo info = new ConnectInfo();

    private RecyclerAdapter adapter;
    private DateRecyclerAdapter dateadapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        final TextView howmany = findViewById(R.id.howmany);
        howmany.setText(info.count + "개피");
        init();
        getData();

        Dateinit();
        getDateData();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.mainfeed) {
            Intent intent = new Intent(FeedActivity.this, FeedActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);//화면전환 효과 제거
        } else if (id == R.id.status) {
            Intent intent = new Intent(FeedActivity.this, StatusActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);//화면전환 효과 제거
        } else if (id == R.id.chart) {
            Intent intent = new Intent(FeedActivity.this, ChartActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);//화면전환 효과 제거
        } else if (id == R.id.like) {
            Intent intent=new Intent(FeedActivity.this, LikeActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);//화면전환 효과 제거
        } else if (id == R.id.more) {
            Intent intent = new Intent(FeedActivity.this, MoreActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);//화면전환 효과 제거
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }



    private void getData() {
        // 임의의 데이터입니다.
        int temp = info.count*225;
        int mone = temp;
        int slee = temp*6;
        int coff = temp/2500;
        int chi = temp/5000;

        String money = "저축 "+mone+"원";
        String sleep = "잠 "+slee+"분";
        String coffee = "커피 "+coff+"잔";
        String chicken = "치킨 "+chi+"마리";

        List<String> listContent = Arrays.asList(
                money,
                sleep,
                coffee,
                chicken
        );
        List<Integer> listResId = Arrays.asList(
                R.drawable.money,
                R.drawable.sleep,
                R.drawable.coffee,
                R.drawable.chicken
        );
        for (int i = 0; i < listContent.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setContent(listContent.get(i));
            data.setResId(listResId.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }

    private void Dateinit() {
//        RecyclerView daterecyclerView = findViewById(R.id.daterecyclerView);
        RecyclerView daterecyclerView = findViewById(R.id.daterecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        daterecyclerView.setLayoutManager(linearLayoutManager);


        dateadapter = new DateRecyclerAdapter();
        daterecyclerView.setAdapter(dateadapter);
    }

    private void getDateData() {
        // 임의의 데이터입니다.

        List<String> listMonth = Arrays.asList(
                "6월",
                "6월",
                "6월",
                "6월"
        );

        List<String> listDay1 = Arrays.asList(
                "13일",
                "13일",
                "14일",
                "17일"
        );
        List<String> listDay2 = Arrays.asList(
                "목요일",
                "목요일",
                "금요일",
                "월요일"
        );
        List<String> listTime = Arrays.asList(
                "13:02",
                "17:36",
                "11:21",
                "19:42"
        );

        for (int i = 0; i < listMonth.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            SmokData datedata = new SmokData();
            datedata.setMonth(listMonth.get(i));
            datedata.setDay(listDay1.get(i));
            datedata.setDayoftheweek(listDay2.get(i));
            datedata.setTime(listTime.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            dateadapter.addItem(datedata);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }
}

