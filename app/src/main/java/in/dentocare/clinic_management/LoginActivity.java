package in.dentocare.clinic_management;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText usernameField,passwordField;
    ConstraintLayout login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameField = findViewById(R.id.RegEmail);
        passwordField = findViewById(R.id.RegPassword);
        login= findViewById(R.id.loginform);
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser User = mAuth.getCurrentUser();
        if(User!=null&&isConnected(LoginActivity.this)) {
            String user = User.getEmail();
            Intent i = new Intent(LoginActivity.this, UserInfo.class);
            i.putExtra("emailStr", user);
            startActivity(i);
            (LoginActivity.this).overridePendingTransition(R.anim.slide_in_right, android.R.anim.slide_out_right);
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void register(View view){
        hideKeyboard(LoginActivity.this);
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);

    }

    public void login(View view){
        hideKeyboard(LoginActivity.this);
        logMeIn();
    }

    private void logMeIn() {
        if(!isConnected(LoginActivity.this))
            internetAlert(LoginActivity.this).show();
        else {
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();
            if(username.matches("") || password.matches(""))
            {
                alertBox(LoginActivity.this,"Empty !","\nPlease enter your credentials").show();
                return;
            }
            final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            progressDialog.getWindow().setLayout(900,400);

            mAuth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete( Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                FirebaseUser User = mAuth.getCurrentUser();
                                // Sign in success, update UI with the signed-in user's information
                                String user = User.getEmail();
                                Intent i = new Intent(LoginActivity.this,UserInfo.class);
                                i.putExtra("emailStr",user);
                                startActivity(i);
                                (LoginActivity.this).overridePendingTransition(R.anim.slide_in_right,android.R.anim.slide_out_right);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, "login failed.",
                                        Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }


                        }
                    });

            //new AsyncLogin(this, progressDialog).execute(username, password);
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

    public AlertDialog.Builder internetAlert(Context c) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet !");
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
    public AlertDialog.Builder alertBox(Context c,String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(LoginActivity.this,"Field empty", Toast.LENGTH_SHORT).show();
            }
        });
        return builder;
    }


}

