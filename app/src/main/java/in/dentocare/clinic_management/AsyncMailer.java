package in.dentocare.clinic_management;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class AsyncMailer extends AsyncTask<String,Void,String> {
    Context context;
//    ProgressDialog p;
//    Boolean flag;
//    String link,lable1,lable2;
//
    public AsyncMailer(Context context) {
        this.context = context;
//        this.p = p;
//        this.flag=f;
    }


    @Override
    protected String doInBackground(String... arg) {

        try {
            String data = URLEncoder.encode("email", "UTF-8") + "=" +
                    URLEncoder.encode(arg[0], "UTF-8");
            data += "&" + URLEncoder.encode("time", "UTF-8") + "=" +
                    URLEncoder.encode(arg[1], "UTF-8");

            URL url = new URL("https://core-menu.000webhostapp.com/mail/mail.php");
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
            Toast.makeText(context,"Login failed", Toast.LENGTH_SHORT).show();
            cancel(true);
            return "exception";
        }
    }
    @Override
    protected void onPostExecute(String result){

        if(result.matches("Message sent!"))
        {
            Toast.makeText(context,"Booking successful", Toast.LENGTH_SHORT).show();
        }
        else if(result.matches("exception"))
        {
            return;
        }
    }
}