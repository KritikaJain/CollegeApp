package kritika.in.collegeapp.ui.activity;

import android.app.Application;
import android.content.Context;

import kritika.in.collegeapp.utils.CollegeAppPreference;

/**
 * Created by dell on 23-Jun-17.
 */

public class CollegeApplication extends Application {
    public static Context mAppContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext=getApplicationContext();
        CollegeAppPreference.getInstance();

    }
}
