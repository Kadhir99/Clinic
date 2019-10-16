package in.dentocare.clinic_management;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static in.dentocare.clinic_management.UserInfo.*;


public class Fragment2 extends Fragment {

    TextView name, email,mobileNo,gender,dob;
//    String sEmail,sMobileNo,sGender,sDob;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,container,false);
        email = view.findViewById(R.id.email);
        email.setText(emailStr);
        name = view.findViewById(R.id.FragName);
        name.setText(usrData[4]);
        mobileNo = view.findViewById(R.id.mobileNo);
        mobileNo.setText(usrData[2]);
        gender = view.findViewById(R.id.gender);
        gender.setText(usrData[1]);
        dob = view.findViewById(R.id.dob);
        dob.setText(usrData[0]);
        return view;
    }

}
