package in.dentocare.clinic_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText usernameField,passwordField;
    TextView status,role,failed;
    ConstraintLayout login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameField = (EditText)findViewById(R.id.editText1);
        passwordField = (EditText)findViewById(R.id.editText2);
        failed = findViewById(R.id.failed);
        login= findViewById(R.id.loginform);
    }



    public void login(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        new SigninActivity(this,failed).execute(username,password);

    }


//    public void loginPost(View view){
//        String username = usernameField.getText().toString();
//        String password = passwordField.getText().toString();
//        method.setText("Post Method");
//        new SigninActivity(this,status,role,1).execute(username,password);
//    }


}

