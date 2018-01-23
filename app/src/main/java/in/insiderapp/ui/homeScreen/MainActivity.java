package in.insiderapp.ui.homeScreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import in.insiderapp.R;
import in.insiderapp.utils.ActivityUtils;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        initFragment();
        initPresenter();
        initBottomBar();
    }

    private void initBottomBar(){
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
