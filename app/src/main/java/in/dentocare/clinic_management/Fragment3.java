package in.dentocare.clinic_management;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Fragment3 extends Fragment {

    Button btn;
    EditText mDate;
    String date,time;
    String appointment;
    DatabaseReference appointBase = FirebaseDatabase.getInstance().getReference("users");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3,container,false);
        final Spinner spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.time_slot, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //final DatePickerDialog.OnDateSetListener mDatePicker;
        mDate = view.findViewById(R.id.Date);
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getContext(),new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {
                        String setDate = day + "/" + (month+1) + "/" + year;
                        mDate.setText(setDate);
                    }
                },year,month,day);
                dialog.setCancelable(false);

                dialog.show();
            }
        });

        btn = view.findViewById(R.id.book);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 date = mDate.getText().toString();
                 time = spinner.getSelectedItem().toString();

                UserInfo.dateStr = date;
                UserInfo.timeStr = time;
                appointment = date + " " + time;
                String id = appointBase.push().getKey();
                appointBase.child(UserInfo.emailStr.replace('.',',')).child("appointments").child("appointment"+id).setValue(appointment);
                new AsyncMailer(getActivity()).execute(UserInfo.emailStr,date+" at "+time);

            }
        });

        return view;
    }


}
