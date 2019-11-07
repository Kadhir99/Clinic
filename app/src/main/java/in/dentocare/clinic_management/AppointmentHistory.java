package in.dentocare.clinic_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AppointmentHistory extends AppCompatActivity {

    Boolean f;
    RecyclerView recyclerView;
    ArrayList<String> dateList,timeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_history);

        recyclerView = findViewById(R.id.Recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dateList = new ArrayList<>();
        timeList = new ArrayList<>();
        f = getIntent().getBooleanExtra("flag",false);
        if(f==false)
            getAppointments(FirebaseDatabase.getInstance().getReference("users/"+UserInfo.emailStr.replace(".",",")).child("appointments"));
        else
            getAppointments(FirebaseDatabase.getInstance().getReference("Appointments"));
    }
    public void getAppointments(DatabaseReference reference){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren())
                {
                    DatabaseReference year = data.getRef();
                    year.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot years) {
                            for(DataSnapshot monthN : years.getChildren()) {
                                DatabaseReference month = monthN.getRef();
                                month.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot months) {
                                        for(DataSnapshot dayN : months.getChildren()) {
                                            DatabaseReference day = dayN.getRef();
                                            day.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot days) {
                                                    for(DataSnapshot timeN : days.getChildren())
                                                    {
                                                        final DatabaseReference time = timeN.getRef();
                                                        time.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot times) {
                                                                String lTime = times.getKey();
                                                                char [] d = times.getRef().toString().toCharArray();
                                                                String lDate;
                                                                if(!f)
                                                                    lDate = ""+d[95]+d[96]+d[97]+d[98]+d[99]+d[100]+d[101]+d[102]+d[103]+d[104];
                                                                else
                                                                    lDate = ""+d[60]+d[61]+d[62]+d[63]+d[64]+d[65]+d[66]+d[67]+d[68]+d[69];

                                                                dateList.add(lDate);
                                                                timeList.add(lTime);
                                                                recyclerView.setAdapter(new MyAdapter(AppointmentHistory.this,dateList,timeList));
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                                Toast.makeText(AppointmentHistory.this, "Failed to read value."+databaseError.toException(), Toast.LENGTH_LONG).show();
                                                            }
                                                        });
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                                    Toast.makeText(AppointmentHistory.this, "Failed to read value."+databaseError.toException(), Toast.LENGTH_LONG).show();
                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        Toast.makeText(AppointmentHistory.this, "Failed to read value."+databaseError.toException(), Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(AppointmentHistory.this, "Failed to read value."+databaseError.toException(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AppointmentHistory.this, "Failed to read value."+databaseError.toException(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
