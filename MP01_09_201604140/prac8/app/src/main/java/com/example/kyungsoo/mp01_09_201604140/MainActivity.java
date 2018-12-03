package com.example.kyungsoo.mp01_09_201604140;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@SuppressLint("NewApi")
public class MainActivity extends AppCompatActivity {
    TextView textview;
    Document doc = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = (TextView) findViewById(R.id.textView1);
    }

    public void onClick(View view) {
        GetXMLTask task = new GetXMLTask(this);
        task.execute("http://www.kma.go.kr/wid/queryDFS.jsp?gridx=61&gridy=125");
    }

    @SuppressLint("NewApi")
    private class GetXMLTask extends AsyncTask<String, Void, Document> {
        private Activity context;

        public GetXMLTask(Activity context) {
            this.context = context;
        }

        @Override
        protected Document doInBackground(String... urls) {
            URL url;
            try {
                url = new URL(urls[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();
            } catch (Exception e) {
                Log.e("sdsd",e.toString());
                Toast.makeText(getBaseContext(), "Parsing Error",
                        Toast.LENGTH_SHORT).show();
            }
            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {
            Log.e("sdsd","44444");
            String s = "";
            NodeList nodeList = doc.getElementsByTagName("data");
            Log.e("sdsd","55555");
            for (int i = 0; i < nodeList.getLength(); i++) {
                s += "" + i + ": 날씨정보: ";
                Node node = nodeList.item(i);
                Element fstElmnt = (Element) node;
                NodeList nameList = fstElmnt.getElementsByTagName("temp");
                Element nameElement = (Element) nameList.item(0);
                nameList = nameElement.getChildNodes();
                s += "온도= " + ((Node) nameList.item(0)).getNodeValue() + " ,";
                NodeList websiteList = fstElmnt.getElementsByTagName("wfKor");
                Element websiteElement = (Element) websiteList.item(0);
                websiteList = websiteElement.getChildNodes();
                s += "날씨= " + ((Node) websiteList.item(0)).getNodeValue() + "\n";
            }
            textview.setText(s);
        }
    }
}