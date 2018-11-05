package org.techtown.mp01_06_201604140;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView text;
    RadioButton R1, R2, R3;
    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView)findViewById(R.id.textView2);
        R1 = (RadioButton)findViewById(R.id.domino);
        R2 = (RadioButton)findViewById(R.id.mr);
        R3 = (RadioButton)findViewById(R.id.hut);
        button1 = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);

        R1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(R1.isChecked() == true) {
                    button1.setEnabled(true);
                    button2.setEnabled(true);
                }
                else if(R1.isChecked() == false) {
                    button1.setEnabled(false);
                    button2.setEnabled(false);
                }
            }
        });

        R2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(R2.isChecked() == true) {
                    button1.setEnabled(true);
                    button2.setEnabled(true);
                }
                else if(R2.isChecked() == false) {
                    button1.setEnabled(false);
                    button2.setEnabled(false);
                }
            }
        });

        R3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(R3.isChecked() == true) {
                    button1.setEnabled(true);
                    button2.setEnabled(true);
                }
                else if(R3.isChecked() == false) {
                    button1.setEnabled(false);
                    button2.setEnabled(false);
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = null;
                if(R1.isChecked() == true) {
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+82)15773082"));
                }
                else if(R2.isChecked() == true) {
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+82)15770077"));
                }
                else if(R3.isChecked() == true) {
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+82)15885588"));
                }
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = null;
                if(R1.isChecked() == true) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dominos.co.kr"));
                }
                else if(R2.isChecked() == true) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mrpizza.co.kr"));
                }
                else if(R3.isChecked() == true) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pizzahut.co.kr"));
                }
                startActivity(intent);
            }
        });
    }

    public void order(View view) {
        Intent intent = new Intent(MainActivity.this, order.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1) {
            if(resultCode == RESULT_OK) {
                int pizzanum = data.getIntExtra("pizzanum", 0);
                int spaghettinum = data.getIntExtra("spaghettinum", 0);
                String temp_text = "피자=" + pizzanum + ", 스파게티=" + spaghettinum;
                text.setText(temp_text);
            }
        }
    }
}
