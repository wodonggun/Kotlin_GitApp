package tech.thdev.Kotlin_github.view.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import tech.thdev.Kotlin_github.R;
import tech.thdev.Kotlin_github.view.main.search.SearchFragment;
import tech.thdev.Kotlin_github.view.main.user.UserFragment;

public class MainActivity extends AppCompatActivity {

    private SearchFragment searchFragment;
    private UserFragment userFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    loadFragment(searchFragment);
                    return true;

                case R.id.navigation_user_info:
                    loadFragment(userFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchFragment = new SearchFragment();
        userFragment = new UserFragment();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        loadFragment(searchFragment);
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_icon_made_by, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_icon_made_by:
                showChromeTabs();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showChromeTabs() {
        // Use a CustomTabsIntent.Builder to configure CustomTabsIntent.
        // Once ready, call CustomTabsIntent.Builder.build() to create a CustomTabsIntent
        // and launch the desired Url with CustomTabsIntent.launchUrl()
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        // Changes the background color for the omnibox. colorInt is an int
        // that specifies a Color.
        builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));

        builder.setStartAnimations(this, 0, 0);
        builder.setExitAnimations(this, 0, 0);

        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse("https://www.flaticon.com/"));
    }
}
