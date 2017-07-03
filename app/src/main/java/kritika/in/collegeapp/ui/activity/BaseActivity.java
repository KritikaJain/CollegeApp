package kritika.in.collegeapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kritika.in.collegeapp.utils.CollegeAppPreference;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void logout(){
        CollegeAppPreference.setLoginStatus(false);
        CollegeAppPreference.setUSERNAME(null);
        CollegeAppPreference.setPASSWORD(null);
        Intent i = new Intent(getApplicationContext(),SplashScreen.class);
        startActivity(i);
        finish();
    }
}
