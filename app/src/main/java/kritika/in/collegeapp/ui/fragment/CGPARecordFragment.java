package kritika.in.collegeapp.ui.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import kritika.in.collegeapp.R;
import kritika.in.collegeapp.subjects;
import kritika.in.collegeapp.ui.adapter.MyCGPARecordPageAdapter;

/**
 * Created by dell on 27-Jun-17.
 */

public class CGPARecordFragment extends Fragment {
    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lm;
    private static String LOG_TAG = "AttendanceMainPage";
    Context mcontext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cgpa_record, container, false);

        rv = (RecyclerView) view.findViewById(R.id.cgpa_record_recycler);
        rv.setHasFixedSize(true);
        lm = new LinearLayoutManager(mcontext);
        rv.setLayoutManager(lm);
        adapter = new MyCGPARecordPageAdapter(getDataSet());
        rv.setAdapter(adapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mcontext, LinearLayoutManager.VERTICAL);
        rv.addItemDecoration(itemDecoration);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MyCGPARecordPageAdapter) adapter).setOnItemClickListener(new MyCGPARecordPageAdapter.MyClickListener() {
        @Override
             public void onItemClick(int position, View v) {
               Log.i(LOG_TAG, " Clicked on Item " + position);
             CGPACalculationFragment ccf = new CGPACalculationFragment();
      FragmentTransaction transaction = getFragmentManager().beginTransaction();
       Fragment fragment = getFragmentManager().findFragmentById(R.id.cgpa_record_fragment);
                 if (fragment != null) {
                     transaction.hide(fragment);
          }
              transaction.add(R.id.activity_cgpa, ccf);
             transaction.addToBackStack(CGPACalculationFragment.class.getSimpleName());
              transaction.commit();
          }
         });
    }


    public ArrayList<subjects> getDataSet() {
        ArrayList results = new ArrayList<subjects>();

        subjects obj = null;
        results.add(new subjects("Semester 1"));
        results.add(new subjects("Semester 2"));
        results.add(new subjects("Semester 3"));
        results.add(new subjects("Semester 4"));
        results.add(new subjects("Semester 5"));
        results.add(new subjects("Semester 6"));
        return results;

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mcontext = context;
    }
}

