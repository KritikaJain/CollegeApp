package kritika.in.collegeapp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import kritika.in.collegeapp.R;

public class AttendanceActivity extends BaseActivity {
    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lm;
    private static String LOG_TAG = "RecyclePage_Attendance";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
    }
}
