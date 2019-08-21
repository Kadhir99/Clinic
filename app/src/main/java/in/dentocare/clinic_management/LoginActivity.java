package in.dentocare.clinic_management;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText usernameField,passwordField;
    ConstraintLayout login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameField = findViewById(R.id.editText1);
        passwordField = findViewById(R.id.editText2);
        login= findViewById(R.id.loginform);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public void login(View view){
        hideKeyboard(LoginActivity.this);
        logMeIn();
    }

    private void logMeIn() {
        if(!isConnected(LoginActivity.this))
            buildDialog(LoginActivity.this).show();
        else {
            ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();
            new SignInAction(this, progressDialog).execute(username, password);
        }

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
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setPositiveButton("Try again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                logMeIn();
            }
        });
        return builder;
    }


}

