package in.dentocare.clinic_management;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText user,pass;
    Button register;
    ConstraintLayout reg;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user= findViewById(R.id.email);
        pass = findViewById(R.id.password);
        register = findViewById(R.id.register);
        reg=findViewById(R.id.registerForm);
        mAuth = FirebaseAuth.getInstance();


    }

    public void register(View view) {
        registerUser();
    }
    private void registerUser() {
        if(!isConnected(RegisterActivity.this))
            internetAlert(RegisterActivity.this).show();
        else{
            String username = user.getText().toString();
            String password = pass.getText().toString();
            if(username.matches("") || password.matches(""))
            {
                alertBox(RegisterActivity.this,"Empty !","\nPlease enter your credentials").show();
                return;
            }
            final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();
            progressDialog.getWindow().setLayout(900,400);
            mAuth.createUserWithEmailAndPassword(username, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete( Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                // Sign in success, update UI with the signed-in user's information
                                String user = mAuth.getCurrentUser().getEmail();
                                Intent i = new Intent(RegisterActivity.this,UserInfo.class);
                                i.putExtra("emailStr",user);
                                startActivity(i);
                                (RegisterActivity.this).overridePendingTransition(R.anim.slide_in_right,android.R.anim.slide_out_right);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                            // ...
                        }
                    });
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
                registerUser();
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
                Toast.makeText(RegisterActivity.this,"Field empty", Toast.LENGTH_SHORT).show();
            }
        });
        return builder;
    }
}
