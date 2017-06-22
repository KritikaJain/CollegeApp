package kritika.in.collegeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
    {
        Animation Fade_in, Fade_out;
        ViewFlipper viewFlipper;
        Button login;
        Button signup;
        EditText rollno;
        EditText batch;
        EditText year;
        String roll_no;
        String batch_no;
        String year_no;
        String password_no;
        EditText password;
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor shared_pref_editor;
        boolean loginstatus;
        CheckBox login_check_box;

        @Override
        protected void onCreate (Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
        viewFlipper = (ViewFlipper) findViewById(R.id.splash_screen_slideshow);
        Fade_in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Fade_out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        viewFlipper.setAnimation(Fade_in);
        viewFlipper.setFlipInterval(5000);
        viewFlipper.startFlipping();
            rollno = (EditText) findViewById(R.id.roll_no_edttxt);
            password = (EditText) findViewById(R.id.password_edttxt);
            batch= (EditText) findViewById(R.id.batch_edttxt);
            year= (EditText) findViewById(R.id.year_edttxt);
            login = (Button) findViewById(R.id.login_btn);
            signup = (Button) findViewById(R.id.signup_btn);
            login_check_box= (CheckBox) findViewById(R.id.login_check_box);

            sharedPreferences=getSharedPreferences("loginPrefs",MODE_PRIVATE);
            shared_pref_editor = sharedPreferences.edit();
            loginstatus = sharedPreferences.getBoolean("loginstatus",false);

            if(loginstatus==true)
            {
                rollno.setText(sharedPreferences.getString("username", ""));
                password.setText(sharedPreferences.getString("password", ""));
                login_check_box.setChecked(true);
            }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent i = new Intent(getApplicationContext(), HomePageActivity.class);
                    roll_no = rollno.getText().toString();
                    batch_no = batch.getText().toString();
                    year_no = year.getText().toString();
                    password_no = password.getText().toString();
                    i.putExtra("Rollno", roll_no);
                    i.putExtra("Batch", batch_no);
                    i.putExtra("Year", year_no);
                if (login_check_box.isChecked()) {
                    shared_pref_editor.putBoolean("loginstatus", true);
                    shared_pref_editor.putString("username", roll_no);
                    shared_pref_editor.putString("password", password_no);
                    shared_pref_editor.commit();
                } else {
                    shared_pref_editor.clear();
                    shared_pref_editor.commit();
                }

                startActivity(i);
                LoginActivity.this.finish();


            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(i);
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.select_inst_spinner);
        spinner.setOnItemSelectedListener(this);
            List<String> list = new ArrayList<String>();
            list.add("Tap to Select Institute");
            list.add("JIIT , 62 ");
            list.add("JIIT , 128");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);

    }

    public void onItemSelected(AdapterView parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView arg0) {
        // TODO Auto-generated method stub

    }
        /*public boolean validate()
        {
            if(rollno.getText().equals("14102186")&& password.getText().equals("646186RI")&&
                    batch.getText().equals("A6")&&year.getText().equals("3"))
            {
                return true;
            }
            else
                return false;
        }*/
}
