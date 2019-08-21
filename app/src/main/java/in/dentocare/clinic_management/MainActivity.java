package in.dentocare.clinic_management;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText usernameField,passwordField;
    TextView failed;
    ConstraintLayout login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showMainPage();

        usernameField = findViewById(R.id.editText1);
        passwordField = findViewById(R.id.editText2);
        failed = findViewById(R.id.failed);
        login= findViewById(R.id.loginform);
    }

    private void showMainPage() {
        if(!isConnected(MainActivity.this))
            buildDialog(MainActivity.this).show();
        else {
            Toast.makeText(MainActivity.this,"Welcome", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_main);
        }
    }


    public void login(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        new SigninActivity(this,failed).execute(username,password);

    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else
                return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet!");
        builder.setMessage("You are not connected to the internet. Please check your internet connection and try again.");

        builder.setPositiveButton("Try again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showMainPage();
            }
        });

        return builder;
    }


}

