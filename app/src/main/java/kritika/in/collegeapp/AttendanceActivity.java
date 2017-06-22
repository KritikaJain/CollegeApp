package kritika.in.collegeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class AttendanceActivity extends AppCompatActivity {
    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lm;
    private static String LOG_TAG = "RecyclePage_Attendance";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);


            rv = (RecyclerView) findViewById(R.id.recyclerview_attendancepage);
            rv.setHasFixedSize(true);
            lm = new LinearLayoutManager(this);
            rv.setLayoutManager(lm);
            adapter = new MyAttendancePageAdapter(getDataSet());
            rv.setAdapter(adapter);
            RecyclerView.ItemDecoration itemDecoration =
                    new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
            rv.addItemDecoration(itemDecoration);

            // Code to Add an item with default animation
            //((MyAttendancePageAdapter) adapter).addItem(obj, index);

            // Code to remove an item with default animation
            //((MyAttendancePageAdapter) adapter).deleteItem(index);
        }

        @Override
        protected void onResume() {
            super.onResume();
            ((MyAttendancePageAdapter) adapter).setOnItemClickListener(new
                                                                             MyAttendancePageAdapter.MyClickListener() {
                                                                                 @Override
                                                                                 public void onItemClick(int position, View v) {
                                                                                     Log.i(LOG_TAG, " Clicked on Item " + position);
                                                                                 }
                                                                             });
        }


        private ArrayList<subjects> getDataSet() {
            ArrayList results = new ArrayList<subjects>();

            subjects obj = null;
            results.add(new subjects("english","21"));
            results.add(new subjects("hindi","22"));
            results.add(new subjects("maths","23"));
            results.add(new subjects("Science","24"));
            results.add(new subjects("SST","25"));
            results.add(new subjects("sanskrit","26"));
            results.add(new subjects("French","27"));


            return results;

        }
}
