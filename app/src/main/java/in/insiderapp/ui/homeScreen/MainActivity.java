package in.insiderapp.ui.homeScreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import in.insiderapp.R;
import in.insiderapp.utils.ActivityUtils;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        initToolbar();
        initFragment();
        initPresenter();
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ImageView logoIV = findViewById(R.id.logoIV);
        Glide.with(this).load(R.drawable.insider_text_logo).into(logoIV);
    }



    private EventsFragment mEventsFragment;

    private void initFragment() {
        mEventsFragment = (EventsFragment) getSupportFragmentManager().findFragmentById(R.id
                .frameLayout);
        if (mEventsFragment == null) {
            mEventsFragment = EventsFragment.newInstance();
            ActivityUtils.showFragment(getSupportFragmentManager(), R.id.frameLayout,
                    mEventsFragment);
        }
    }

    private EventsPresenter mEventPresenter;
    private void initPresenter(){
        mEventPresenter = new EventsPresenter(mEventsFragment);
    }
}
