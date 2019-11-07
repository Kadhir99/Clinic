package in.dentocare.clinic_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> appointDate,appointTime;

    public MyAdapter(){
        super();
    }

    public MyAdapter(Context c, ArrayList<String> p, ArrayList<String> q){
        context = c;
        appointDate = p;
        appointTime = q;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.appointhistory,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.date.setText(appointDate.get(position));
            holder.time.setText(appointTime.get(position));
    }

    @Override
    public int getItemCount() {
        return appointDate.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView date,time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.Adate);
            time = itemView.findViewById(R.id.Atime);
        }
    }
}
