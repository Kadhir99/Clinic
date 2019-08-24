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

public class AsyncThread extends AsyncTask<String,Void,String>{

    Context context;
    ProgressDialog p;
    Boolean flag;
    String link,lable1,lable2;

    public AsyncThread(Context context, ProgressDialog p, Boolean f) {
        this.context = context;
        this.p = p;
        this.flag=f;
    }


    @Override
    protected String doInBackground(String... arg) {
        if(flag==true) {
            link = "https://core-menu.000webhostapp.com/login.php";
            lable1="username";
            lable2="password";
        }
        else {
            link = "https://core-menu.000webhostapp.com/mail/mail.php";
            lable1="email";
            lable2="time";
        }

            try {
                String data = URLEncoder.encode(lable1, "UTF-8") + "=" +
                        URLEncoder.encode(arg[0], "UTF-8");
                data += "&" + URLEncoder.encode(lable2, "UTF-8") + "=" +
                        URLEncoder.encode(arg[1], "UTF-8");

                URL url = new URL(link);
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
        else if(result.matches("Message sent!"))
        {
            Toast.makeText(context,result, Toast.LENGTH_SHORT).show();
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