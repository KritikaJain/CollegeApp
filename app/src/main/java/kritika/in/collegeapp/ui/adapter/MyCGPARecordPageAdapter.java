package kritika.in.collegeapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import kritika.in.collegeapp.R;
import kritika.in.collegeapp.subjects;

/**
 * Created by dell on 27-Jun-17.
 */

public class MyCGPARecordPageAdapter extends RecyclerView
        .Adapter<MyCGPARecordPageAdapter
        .subjectsHolder> {
    private static String LOG_TAG = "MyAdapter";
    private ArrayList<subjects> dataset;
    private static MyCGPARecordPageAdapter.MyClickListener myClickListener;

    public static class subjectsHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView sem_no;
       

        public subjectsHolder(View itemView) {
            super(itemView);
            sem_no = (TextView) itemView.findViewById(R.id.sem_no);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getPosition(), v);
        }
    }

    public void setOnItemClickListener(MyCGPARecordPageAdapter.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public MyCGPARecordPageAdapter(ArrayList<subjects> myDataset) {
        dataset = myDataset;
    }

    @Override
    public MyCGPARecordPageAdapter.subjectsHolder onCreateViewHolder(ViewGroup parent,
                                                                       int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cgpa_record_single_view, parent, false);

        MyCGPARecordPageAdapter.subjectsHolder p = new MyCGPARecordPageAdapter.subjectsHolder(view);
        return p;
    }

    @Override
    public void onBindViewHolder(MyCGPARecordPageAdapter.subjectsHolder holder, int position) {
        holder.sem_no.setText(dataset.get(position).getSub_name());

    }

    public void addItem(subjects dataObj, int index) {
        dataset.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        dataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);

    }
}


