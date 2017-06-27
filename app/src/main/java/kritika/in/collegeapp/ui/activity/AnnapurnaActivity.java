package kritika.in.collegeapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import kritika.in.collegeapp.R;

public class AnnapurnaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annapurna);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.annapurna_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);

            }
        });
    }

}
