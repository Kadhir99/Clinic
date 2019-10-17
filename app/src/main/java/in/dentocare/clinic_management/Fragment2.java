package in.dentocare.clinic_management;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//import static in.dentocare.clinic_management.UserInfo.*;


public class Fragment2 extends Fragment {

    TextView name, email,mobileNo,gender,dob;
//    String sEmail,sMobileNo,sGender,sDob;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,container,false);
        email = view.findViewById(R.id.email);
        email.setText(UserInfo.emailStr);
        name = view.findViewById(R.id.FragName);
        mobileNo = view.findViewById(R.id.mobileNo);
        gender = view.findViewById(R.id.gender);
        dob = view.findViewById(R.id.dob);
        return view;
    }
    public void updateValues(String usrData[])
    {
//        email.setText(emailStr);
        name.setText(usrData[4]);
        mobileNo.setText(usrData[2]);
        gender.setText(usrData[1]);
        dob.setText(usrData[0]);
    }

}
