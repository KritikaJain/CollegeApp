package kritika.in.collegeapp.utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import kritika.in.collegeapp.ui.activity.CollegeApplication;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by dell on 23-Jun-17.
 */

public class CollegeAppPreference {
    public static SharedPreferences mPreferences;
    public static Editor mEditor;
    public static CollegeAppPreference mInstance;

    public static CollegeAppPreference getInstance() {
        if(mInstance==null) {
            mInstance = new CollegeAppPreference();
            mPreferences = CollegeApplication.mAppContext.getSharedPreferences("LoginPreferences", MODE_PRIVATE);
            mEditor = mPreferences.edit();
        }
        return mInstance;
    }


    private static final String USERNAME="username";
    private static final String PASSWORD="password";
    private static final String LOGIN_STATUS="loginStatus";


    public static String getUSERNAME() {
        return mPreferences.getString(USERNAME,null);
    }

    public static void setUSERNAME(String username) {
        mEditor.putString(USERNAME,username).apply();
    }

    public static String getPASSWORD() {
        return mPreferences.getString(PASSWORD,null);
    }

    public static void setPASSWORD(String password) {
        mEditor.putString(PASSWORD,password).apply();
    }

    public static boolean getLoginStatus() {
        return mPreferences.getBoolean(LOGIN_STATUS,false);
    }

    public static void setLoginStatus(boolean loginStatus) {
        mEditor.putBoolean(LOGIN_STATUS,loginStatus).apply();
    }
}
