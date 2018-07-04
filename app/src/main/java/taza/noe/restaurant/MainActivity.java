package taza.noe.restaurant;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ArrayList<Category> category = new ArrayList<Category>();
    private ProgressDialog pDialog;
    private String dom = "http://192.168.1.129";    //dominio del servidor
    private String url = dom+":9000/api/platos";
    private AdapterCategory adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //readJSon();
        //ListView listView = findViewById(R.id.listView);
        //listView.setAdapter(new AdapterCategory(category, MainActivity.this));
        new GetHttp().execute();

    }

    private class GetHttp extends AsyncTask<Void, Void, String> {
        JSONObject response;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Loading...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            String result = null;
            try{
                URL url_= new URL(url);
                HttpURLConnection urlConnection = (HttpURLConnection) url_.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                result = inputStreamToString(in);

            }catch (Exception e){
                e.printStackTrace();
            }

            return result;

        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.dismiss();

            ListView listView = findViewById(R.id.listView);

            try{
                //JSONObject jsonObject = new JSONObject(URLDecoder.decode(result, "UTF-8"));
                JSONArray menu = new JSONArray(result);

                for (int i = 0; i < menu.length(); i++) {
                    JSONObject dish = menu.getJSONObject(i);
                    Category cat = new Category(dish.getString("Dishid"), dish.getString("Name"),
                            dish.getString("Description"), dish.getString("ID"),
                            dish.getDouble("Price"), dom+":9000/images/"+dish.getString("Image"));

                    category.add(cat);
                }

                adapter = new AdapterCategory(category, MainActivity.this);
                listView.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        private String inputStreamToString(InputStream is) {

            String rLine = "";
            StringBuilder answer = new StringBuilder();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader rd = new BufferedReader(isr);
            try {
                while ((rLine = rd.readLine()) != null){
                    answer.append(rLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return answer.toString();

        }
    }

    public void readJSon(){
        String jsonString = IOHelper.stringFromAsset(this, "menu.json");
        try {
            //JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray menu = new JSONArray(jsonString);

            for (int i = 0; i < menu.length(); i++) {
                JSONObject dish = menu.getJSONObject(i);
                Category cat = new Category(dish.getString("Dishid"), dish.getString("Name"),
                        dish.getString("Description"), dish.getString("ID"),
                        dish.getDouble("Price"), dish.getString("image"));

                category.add(cat);
            }

        } catch (Exception e) {
            Log.d("ReadPlacesFeedTask", e.getLocalizedMessage());
        }
    }
}
