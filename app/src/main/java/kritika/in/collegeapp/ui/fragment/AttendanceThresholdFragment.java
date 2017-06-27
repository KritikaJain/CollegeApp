package kritika.in.collegeapp.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kritika.in.collegeapp.R;

/**
 * Created by dell on 23-Jun-17.
 */

public class AttendanceThresholdFragment extends Fragment {
    TextView sub_name;
    String title;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_attendance_threshold,container, false);
        title = this.getArguments().getString("Title_sub_name");
        sub_name = (TextView) view.findViewById(R.id.title_sub_name);
        sub_name.setText(title);
        return view;
    }
}
