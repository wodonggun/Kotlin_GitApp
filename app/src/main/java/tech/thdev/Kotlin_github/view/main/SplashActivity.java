package tech.thdev.Kotlin_github.view.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import tech.thdev.Kotlin_github.R;

/**
 * Created by User on 2018-07-27.
 */

public class SplashActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

}
