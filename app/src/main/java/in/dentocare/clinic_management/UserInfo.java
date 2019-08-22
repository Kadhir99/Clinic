package in.dentocare.clinic_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserInfo extends AppCompatActivity {

    TextView nam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        String name = getIntent().getStringExtra("name");
        nam = findViewById(R.id.name);
        nam.setText(name);
    }
}
