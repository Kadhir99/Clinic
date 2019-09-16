package in.dentocare.clinic_management;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.app.ProgressDialog;
import android.content.Intent;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class AsyncLogin extends AsyncTask<String,Void,String>{

    Context context;
    ProgressDialog p;

    public AsyncLogin(Context context, ProgressDialog p) {
        this.context = context;
        this.p = p;
    }


    @Override
    protected String doInBackground(String... arg) {
            try {
                String data = URLEncoder.encode("username", "UTF-8") + "=" +
                        URLEncoder.encode(arg[0], "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                        URLEncoder.encode(arg[1], "UTF-8");

                URL url = new URL("https://core-menu.000webhostapp.com/login.php");
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuffer sb = new StringBuffer();
                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                reader.close();
                return sb.toString();
            } catch (Exception e) {
                alertBox(context,"Exception !",e.getMessage());
                Toast.makeText(context,"Login failed", Toast.LENGTH_SHORT).show();
                return "exception";
            }

    }
    @Override
    protected void onPostExecute(String result){
        p.dismiss();
        if(result.matches(""))
        {
            alertBox(context,"Login failed !","Please check your username and password then try again.");
            Toast.makeText(context,"Login failed", Toast.LENGTH_SHORT).show();
        }
        else if(result.matches("admin"))
        {
            Intent i = new Intent(context,Doctor.class);
            i.putExtra("name",result);
            context.startActivity(i);
            ((LoginActivity) context).overridePendingTransition(R.anim.slide_in_right,android.R.anim.slide_out_right);
        }
        else if(result.matches("exception"))
        {
            return;
        }
        else {
            Intent i = new Intent(context,UserInfo.class);
            i.putExtra("name",result);
            context.startActivity(i);
            ((LoginActivity) context).overridePendingTransition(R.anim.slide_in_right,android.R.anim.slide_out_right);
        }
    }

    public void alertBox(Context c,String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(title);
        builder.setMessage(message);
        AlertDialog alert = builder.create();
        alert.show();
      //  alert.getWindow().setLayout(1000,500);
    }
}