package kritika.in.collegeapp.ui.activity;

import android.content.Intent;
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
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

import kritika.in.collegeapp.R;
import kritika.in.collegeapp.utils.CollegeAppPreference;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
    {
        Animation Fade_in, Fade_out;
        ViewFlipper viewFlipper;
        Button login;
        EditText rollno;
        EditText batch;
        EditText year;
        String roll_no;
        String batch_no;
        String year_no;
        String password_no;
        EditText password;

        boolean loginstatus;
        CheckBox login_check_box;
        private boolean serverLoginSuccess=true;


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
            login_check_box= (CheckBox) findViewById(R.id.login_check_box);


            loginstatus = CollegeAppPreference.getLoginStatus();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(serverLoginSuccess==true){
                    processServerSuccess();
                }




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

        /**
         * this method is used to process server result
         */
        private void processServerSuccess() {

            if (login_check_box.isChecked()) {
                CollegeAppPreference.setUSERNAME(rollno.getText().toString());
                CollegeAppPreference.setPASSWORD(password.getText().toString());
                CollegeAppPreference.setLoginStatus(true);
            }
            startHomeActivity();

        }

        private void startHomeActivity() {
            Intent i = new Intent(getApplicationContext(), HomePageActivity.class);
            startActivity(i);
            finish();
        }


        public void onItemSelected(AdapterView parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();

        }
    public void onNothingSelected(AdapterView arg0) {
        // TODO Auto-generated method stub

    }

}
