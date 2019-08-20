package in.dentocare.clinic_management;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import android.content.Intent;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class SigninActivity  extends AsyncTask<String,Void,String>{

    TextView statusField,roleField,failed;
    Context context;
    ConstraintLayout login;

    //flag 0 means get and 1 means post.(By default it is get.)
    public SigninActivity(Context context, TextView failed) {
        this.context = context;
        this.statusField = statusField;
        this.roleField = roleField;
        this.failed = failed;
    }



    @Override
    protected String doInBackground(String... arg0) {

            try {
                String username = (String) arg0[0];
                String password = (String) arg0[1];

                String link = "https://core-menu.000webhostapp.com/login.php";
                String data = URLEncoder.encode("username", "UTF-8") + "=" +
                        URLEncoder.encode(username, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                        URLEncoder.encode(password, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuffer sb = new StringBuffer("");
                String line = "";

                // Read Server Response
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                reader.close();
                return sb.toString();
            } catch (Exception e) {
                return ("Exception: " + e.getMessage());
            }

    }
    @Override
    protected void onPostExecute(String result){
        if(result.equals("")){
            failed.setText("Login Failed");
        }else{
            Intent i = new Intent(context,UserInfo.class);
            i.putExtra("name",result);
            context.startActivity(i);
        }
    }
}