package com.ylee.a08openweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    TextView updates, country, location, temperature, humidity, pressure;
    Button button;
    String sourceurl = null;
    String sourceurlforecast = null;
    // 현재날씨
    // xml
    String currenturl = "https://api.openweathermap.org/data/2.5/weather?&mode=xml&units=metric&appid=" ;
    String forecasturl = "https://api.openweathermap.org/data/2.5/forecast?&mode=json&units=metric&appid=";
    String cityname = "seoul";
    String weatherdata = null;
    String weatherdataforecast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        updates = findViewById(R.id.updates);
        country = findViewById(R.id.country);
        location = findViewById(R.id.location);
        temperature = findViewById(R.id.temperature);
        humidity = findViewById(R.id.humidity);
        pressure = findViewById(R.id.pressure);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        setTitle("이용희 openweather");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재날씨 받아오기
                cityname = editText.getText().toString();
                sourceurl = currenturl + "&q=" + cityname;

                ReqeustTaskcurrent reqeustTaskcurrent = new ReqeustTaskcurrent();
                reqeustTaskcurrent.execute();

                // 예보받아오기
                sourceurlforecast = forecasturl + "&q=" + cityname;
                ReqeustTaskforecast reqeustTaskforecast = new ReqeustTaskforecast();
                reqeustTaskforecast.execute();
            }
        });
    }

    class ReqeustTaskcurrent extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textView.setText("AsyncTask running");
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
                parseXMLopenweather(weatherdata);
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            weatherdata = getHTTPdata(sourceurl);
            return null;
        }
    }

    class ReqeustTaskforecast extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textView.setText("AsyncTask running");
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
                parseJSONforecast(weatherdataforecast);
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            weatherdataforecast = getHTTPdata(sourceurlforecast);
            return null;
        }
    }

    public void parseJSONforecast(String odata){
        Integer humidity, pressure;
        Double temperature;
        String presult = null;

        if(odata == null){
            textView.setText("데이터 없음");
            return;
        }

        presult = "Forecast\n";
        try{
            JSONObject jsonObject = new JSONObject(odata);
            JSONArray jArray = jsonObject.getJSONArray("list");
            for(int i=0; i<jArray.length(); ++i){
                JSONObject jlist = jArray.getJSONObject(i);
                Long udate = jlist.getLong("dt");
                Date date = new Date(udate * 1000);
                SimpleDateFormat dateformat =
                        new SimpleDateFormat("yyyy-MM-ddEHH:mm:ss");

                JSONObject jmain = jlist.getJSONObject("main");
                temperature = jmain.getDouble("temp");
                humidity = jmain.getInt("humidity");
                presult += dateformat.format(date) + " temperature = " + temperature
                        + ",  " + "humidity = " + humidity + "\n";
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.e("JSONParsing", "Parsing Error");
        }
        textView.setText(presult);

    }

    public void parseXMLopenweather(String odata){
        if(odata == null) {
            updates.setText("디폴트:오늘");
            country.setText("디폴드:한국");
            location.setText("디폴트:서울");
            temperature.setText("디폴트:쾌적");
            humidity.setText("디폴트: 좋음");
            pressure.setText("디폴트: 적당");
            textView.setText(odata);
            return;
        }
        String supdates=null, scountry=null, slocation = null, stemperature = null;
        String shumidity = null, spressure = null;
        String presult = "XML:";

        try{
            String fxml;
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(odata));

            int eventType = xpp.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){
                if(eventType == XmlPullParser.START_DOCUMENT){
                    presult += "Start of XML\n";
                }else  if(eventType == XmlPullParser.START_TAG){
                    fxml = xpp.getName();
                    if(fxml.equals("temperature")){
                        stemperature = xpp.getAttributeValue(null, "value");
                    }else if(fxml.equals("lastupdate")){
                        supdates = xpp.getAttributeValue(null, "value");
                        // <coord lon="126.98" lat="37.57"/>
                    }else if(fxml.equals("coord")){
                        String slon = xpp.getAttributeValue(null, "lon");
                        String slat = xpp.getAttributeValue(null, "lat");
                        slocation = "Long:" + slon + ",  " + "Lat:" + slat;
                    }
                    // <country>KR</country>
                    else if(fxml.equals("country")){
                        eventType = xpp.next();
                        if(eventType == XmlPullParser.TEXT){
                            scountry = xpp.getText();
                        }
                    }

                }
                eventType = xpp.next();
            }
        }catch (Exception e){
            Log.e("xml parsing", "Parsing E", e);
        }

        updates.setText(supdates);
        country.setText(scountry);
        location.setText(slocation);
        temperature.setText(stemperature);
        humidity.setText(shumidity);
        pressure.setText(spressure);
    }

    public String getHTTPdata(String urlStr){
        String rdata = null;

        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if(conn != null){
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                int resCode = conn.getResponseCode();
                BufferedReader reader = new BufferedReader
                        (new InputStreamReader(conn.getInputStream()));
                String line;
                line = reader.readLine();
                rdata = line;
                while (true){
                    line = reader.readLine();
                    if(line == null)
                        break;
                    rdata += line;
                }
                reader.close();
                conn.disconnect();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return rdata;
    }

}
