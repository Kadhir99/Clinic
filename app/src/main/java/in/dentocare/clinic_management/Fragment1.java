package in.dentocare.clinic_management;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {
    TextView setdate,settime;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,container,false);

        setdate = view.findViewById(R.id.date);
        settime = view.findViewById(R.id.Time);
        setdate.setText(UserInfo.dateStr);
        settime.setText(UserInfo.timeStr);
        return view;
    }

}
