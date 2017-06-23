package kritika.in.collegeapp.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import kritika.in.collegeapp.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        TextView warning = (TextView) findViewById(R.id.warning_txt_signup);
        warning.setText("You cannot create a new id without the permission of college");

    }
}
