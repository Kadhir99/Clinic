package in.dentocare.clinic_management;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Fragment2 extends Fragment {

    String n;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,container,false);
        n = UserInfo.name;
        //TextView info = view.findViewById(R.id.userinfo);
        TextView email = view.findViewById(R.id.email);
        email.setText(n);
        return view;
    }

}
