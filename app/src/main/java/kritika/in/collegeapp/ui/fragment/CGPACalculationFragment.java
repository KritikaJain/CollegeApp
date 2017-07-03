package kritika.in.collegeapp.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import kritika.in.collegeapp.R;
import kritika.in.collegeapp.subjects;
import kritika.in.collegeapp.ui.adapter.MyCGPACalculationPageAdapter;

/**
 * Created by dell on 27-Jun-17.
 */

public class CGPACalculationFragment extends Fragment {
    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lm;
    private static String LOG_TAG = "AttendanceMainPage";
    Context mcontext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_cgpa_calculation,container,false);
       

            rv = (RecyclerView) view.findViewById(R.id.cgpa_calulation_recycler);
            rv.setHasFixedSize(true);
            lm = new LinearLayoutManager(mcontext);
            rv.setLayoutManager(lm);
            adapter = new MyCGPACalculationPageAdapter(getDataSet());
            rv.setAdapter(adapter);
            RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mcontext, LinearLayoutManager.VERTICAL);
            rv.addItemDecoration(itemDecoration);
            registerForContextMenu(rv);
        return view;
        }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Call");//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "SMS");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Call"){
            Toast.makeText(mcontext,"calling code",Toast.LENGTH_LONG).show();
        }
        else if(item.getTitle()=="SMS"){
            Toast.makeText(mcontext,"sending sms code",Toast.LENGTH_LONG).show();
        }else{
            return false;
        }
        return true;
    }

    @Override
        public void onResume() {
            super.onResume();
            ((MyCGPACalculationPageAdapter) adapter).setOnItemClickListener(new
                                                                               MyCGPACalculationPageAdapter.MyClickListener() {
                                                                                   @Override
                                                                                   public void onItemClick(int position, View v) {

                                                                                       Log.i(LOG_TAG, " Clicked on Item " + position);
                                                                                   }
                                                                               });
        }


        public ArrayList<subjects> getDataSet() {
            ArrayList results = new ArrayList<subjects>();

            subjects obj = null;
            results.add(new subjects("JAVA"));
            results.add(new subjects("C++"));
            results.add(new subjects("Android"));
            results.add(new subjects("Big data"));
            results.add(new subjects("Html"));
            results.add(new subjects("PHP"));
            results.add(new subjects("French"));
            return results;

        }
        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            mcontext = context;
        }
    }
