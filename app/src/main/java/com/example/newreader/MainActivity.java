package com.example.newreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    CustomAdapter customAdapter;
    ArrayList<DocBao> arrayList;

    ListView lstNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        setTitle(intent.getStringExtra("Title"));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadData().execute(intent.getStringExtra("link"));
            }
        });
        arrayList = new ArrayList<DocBao>();
        lstNews = (ListView) findViewById(R.id.listView);
        lstNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailNews.class);
                intent.putExtra("link", arrayList.get(position).getLink());
                startActivity(intent);
            }
        });
    }

    private class ReadData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeListDecription = document.getElementsByTagName("description");
            String hinhAnh = "";
            String title = "";
            String link = "";
            for (int i = 0; i < nodeList.getLength(); i++) {
                String cdata = nodeListDecription.item(i + 1).getTextContent();
                //hàm đọc đc img trong cdata
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);
                //ktra noi dung
                if(matcher.find()){
                    hinhAnh = matcher.group(1);
                }
                Element element = (Element) nodeList.item(i);
                title = parser.getValue(element,"title");
                link = parser.getValue(element,"link");
                arrayList.add(new DocBao(title, link, hinhAnh));
            }
            customAdapter = new CustomAdapter(MainActivity.this,android.R.layout.simple_list_item_1,arrayList);
            lstNews.setAdapter(customAdapter);
            super.onPostExecute(s);

        }

        private String docNoiDung_Tu_URL(String theUrl) {
            StringBuilder content = new StringBuilder();
            try {
                // create a url object
                URL url = new URL(theUrl);
                // create a urlconnection object
                URLConnection urlConnection = url.openConnection();
                // wrap the urlconnection in a bufferedreader
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(

                        urlConnection.getInputStream()));
                String line;
                // read from the urlconnection via the bufferedreader
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line + "\n");
                }
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return content.toString();
        }
    }
}