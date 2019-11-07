package in.dentocare.clinic_management;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import static in.dentocare.clinic_management.UserInfo.ap;

public class Fragment3 extends Fragment {

    Button btn;
    EditText mDate;
    String date,time;
    String appointment;
    boolean c = false;
    DatabaseReference appointBase = FirebaseDatabase.getInstance().getReference("users");
    DatabaseReference allAppoint = FirebaseDatabase.getInstance().getReference();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3,container,false);
        //final Spinner spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.time_slot, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);
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
                        month++;
                        String d = (day<10)?"0"+day:""+day;
                        String m = (month<10)?"0"+month:""+month;
                        //String setDate = d + "/" + m + "/" + year;
                        String setDate = year + "/" + m + "/" + d;
                        mDate.setText(setDate);
                        setButtons(setDate);
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
                 if(!date.matches("") && c==true) {
                     UserInfo.dateStr = date;
                     UserInfo.timeStr = time;
                     appointment = "Date: " + date + "   " + "Time: " + time;
                     String id = appointBase.push().getKey();
                     allAppoint.child("Appointments").child(date).child(time).setValue(UserInfo.emailStr.replace('.', ','));
                     appointBase.child(UserInfo.emailStr.replace('.', ',')).child("appointments").child(date).child(time).setValue(id);
                     new AsyncMailer(getActivity()).execute(UserInfo.emailStr, date + " at " + time);
                 }
                 else{
                     Toast.makeText(getContext(),"Choose date and time",Toast.LENGTH_LONG).show();
                 }
            }
        });

        return view;
    }

    void setButtons(final String date)
    {
        final Button b6 = getView().findViewById(R.id.b6);
        final Button b63 = getView().findViewById(R.id.b63);
        final Button b7 = getView().findViewById(R.id.b7);
        final Button b73 = getView().findViewById(R.id.b73);
        final Button b8 = getView().findViewById(R.id.b8);
        final Button b83 = getView().findViewById(R.id.b83);

        b6.setBackgroundColor(Color.GREEN);
        b63.setBackgroundColor(Color.GREEN);
        b7.setBackgroundColor(Color.GREEN);
        b73.setBackgroundColor(Color.GREEN);
        b8.setBackgroundColor(Color.GREEN);
        b83.setBackgroundColor(Color.GREEN);

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(((ColorDrawable)b6.getBackground()).getColor() != Color.RED) {
                    setButtons(date);
                    b6.setBackgroundColor(Color.BLUE);
                    time = b6.getText().toString();
                    c=true;
                }
            }
        });
        b63.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(((ColorDrawable)b63.getBackground()).getColor() != Color.RED) {
                    setButtons(date);
                    b63.setBackgroundColor(Color.BLUE);
                    time = b63.getText().toString();
                    c=true;
                }
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(((ColorDrawable)b7.getBackground()).getColor() != Color.RED) {
                    setButtons(date);
                    b7.setBackgroundColor(Color.BLUE);
                    time = b7.getText().toString();
                    c=true;
                }
            }
        });
        b73.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(((ColorDrawable)b73.getBackground()).getColor() != Color.RED) {
                    setButtons(date);
                    b73.setBackgroundColor(Color.BLUE);
                    time = b73.getText().toString();
                    c=true;
                }
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(((ColorDrawable)b8.getBackground()).getColor() != Color.RED) {
                    setButtons(date);
                    b8.setBackgroundColor(Color.BLUE);
                    time = b8.getText().toString();
                    c=true;
                }
            }
        });
        b83.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(((ColorDrawable)b83.getBackground()).getColor() != Color.RED) {
                    setButtons(date);
                    b83.setBackgroundColor(Color.BLUE);
                    time = b83.getText().toString();
                    c=true;
                }
            }
        });
        char[] d = date.toCharArray();
        char[] a = ap.toCharArray();
        int t1= 0,t2 = 9;
        for(int i = 0; i<ap.length()-10; i++)
        {
            if(a[i]==d[0] && a[i+1]==d[1] && a[i+2]==d[2] && a[i+3]==d[3] && a[i+4]==d[4] && a[i+5]==d[5] && a[i+6]==d[6] && a[i+7]==d[7] && a[i+8]==d[8] && a[i+9]==d[9])
            {
                t1 = a[i + 10] - '0';
                t2 = a[i + 12] - '0';
            }
            if(t1!=0&&t2!=9)
            {
                if(t1==6&&t2==0)
                {
                    b6.setBackgroundColor(Color.RED);
                }
                if(t1==6&&t2==3)
                {
                    b63.setBackgroundColor(Color.RED);
                }
                if(t1==7&&t2==0)
                {
                    b7.setBackgroundColor(Color.RED);
                }
                if(t1==7&&t2==3)
                {
                    b73.setBackgroundColor(Color.RED);
                }
                if(t1==8&&t2==0)
                {
                    b8.setBackgroundColor(Color.RED);
                }
                if(t1==8&&t2==3)
                {
                    b83.setBackgroundColor(Color.RED);
                }
            }


        }
    }
    boolean isNum(char a)
    {
        if(a>=48 && a<=57)
            return true;
        else
            return false;
    }

}
