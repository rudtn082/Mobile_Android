package org.techtown.mp01_06_201604140;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class order extends AppCompatActivity {
    CheckBox pizza;
    CheckBox spaghetti;
    Button add, add2, sub, sub2, ok;
    TextView textView1, textView2;
    int pizzanum=0, spaghettinum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        pizza = (CheckBox)findViewById(R.id.pizza);
        spaghetti = (CheckBox)findViewById(R.id.spaghetti);
        add = (Button)findViewById(R.id.add);
        add2 = (Button)findViewById(R.id.add2);
        sub = (Button)findViewById(R.id.sub);
        sub2 = (Button)findViewById(R.id.sub2);
        ok = (Button)findViewById(R.id.button4);

        textView1 = (TextView)findViewById(R.id.textView3);
        textView2 = (TextView)findViewById(R.id.textView4);

        pizza.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pizza.isChecked() == true) {
                    add.setEnabled(true);
                    sub.setEnabled(true);
                }
                else if(pizza.isChecked() == false) {
                    add.setEnabled(false);
                    sub.setEnabled(false);
                    textView1.setText("피자=0");
                    pizzanum=0;
                }
            }
        });

        spaghetti.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spaghetti.isChecked() == true) {
                    add2.setEnabled(true);
                    sub2.setEnabled(true);
                }
                else if(spaghetti.isChecked() == false) {
                    add.setEnabled(false);
                    sub.setEnabled(false);
                    textView2.setText("스파게티=0");
                    spaghettinum=0;
                }
            }
        });

        add.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = (String) textView1.getText();
                str = str.replaceAll("[^0-9]", "");
                pizzanum = Integer.parseInt(str);
                pizzanum++;
                textView1.setText("피자=" + pizzanum);
            }
        });

        sub.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = (String) textView1.getText();
                str = str.replaceAll("[^0-9]", "");
                pizzanum = Integer.parseInt(str);
                pizzanum--;
                if(pizzanum <= 0) pizzanum = 0;
                textView1.setText("피자=" + pizzanum);
            }
        });

        add2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = (String) textView2.getText();
                str = str.replaceAll("[^0-9]", "");
                spaghettinum = Integer.parseInt(str);
                spaghettinum++;
                textView2.setText("스파게티=" + spaghettinum);
            }
        });

        sub2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = (String) textView2.getText();
                str = str.replaceAll("[^0-9]", "");
                spaghettinum = Integer.parseInt(str);
                spaghettinum--;
                if(spaghettinum <= 0) spaghettinum = 0;
                textView2.setText("스파게티=" + spaghettinum);
            }
        });
    }

    public void ok(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("pizzanum", pizzanum);
        intent.putExtra("spaghettinum", spaghettinum);
        setResult(RESULT_OK, intent);
        finish();
    }
}
