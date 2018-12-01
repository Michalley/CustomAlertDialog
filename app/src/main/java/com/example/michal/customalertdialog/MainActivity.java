package com.example.michal.customalertdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    AlertDialog.Builder adb;
    LinearLayout mydialog;
    Switch sw;
    EditText et1,et2;

    Double [] data = new Double[20];
    Integer [] array = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    ListView lv;

    TextView x,d,n,snt;

    Double f,mh,sn;
    Boolean tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=(ListView)findViewById(R.id.lv);

        x=(TextView)findViewById(R.id.x);
        d=(TextView)findViewById(R.id.d);
        n=(TextView)findViewById(R.id.n);
        snt=(TextView)findViewById(R.id.sn);

        lv.setOnItemClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }

    public void enter(View view) {
        mydialog=(LinearLayout)getLayoutInflater().inflate(R.layout.my_dialog,null);
        sw=(Switch)mydialog.findViewById(R.id.sw);
        et1=(EditText)mydialog.findViewById(R.id.et1);
        et2=(EditText)mydialog.findViewById(R.id.et2);

        adb=new AlertDialog.Builder(this);
        adb.setView(mydialog);
        adb.setPositiveButton("Cancel",myClick);
        adb.setNegativeButton("Reset",myClick);
        adb.setNeutralButton("Get",myClick);
        adb.show();
    }

    DialogInterface.OnClickListener myClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE) {

            }
            if (which == DialogInterface.BUTTON_NEGATIVE) {
                ArrayAdapter<Integer> adp = new ArrayAdapter<Integer>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,array);
                lv.setAdapter(adp);
                mh=0.0;
                f=0.0;
                x.setText("");
                d.setText("");
                n.setText("");
                snt.setText("");
            }
            if (which == DialogInterface.BUTTON_NEUTRAL) {
                String st1 = et1.getText().toString();
                String st2 = et2.getText().toString();
                f = Double.parseDouble(st1);
                mh = Double.parseDouble(st2);
                if (sw.isChecked()) {
                    tf = true;
                } else {
                    tf = false;
                }
                x.setText("");
                d.setText("");
                n.setText("");
                snt.setText("");
                fillData(tf,f,mh,data);
            }

        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        x.setText(data[position] + "");
        n.setText((position + 1) + "");
        d.setText(mh + "");
        if (tf == false) {
            sn = ((position + 1) * (2 * f + ((position + 1) - 1) * mh)) / 2;
            snt.setText(sn + "");
        }
        if (tf == true) {
            sn = (f * ((Math.pow(mh, (position + 1))) - 1)) / (mh - 1);
            snt.setText(sn + "");
        }

    }

    public void fillData(boolean tf, Double f, Double mh, Double[] data){
        int i=0;
        int j=1;
        if (tf == false) {
            while (i < 20) {
                data[i] = f + (j - 1) * mh;
                i++;
                j++;
            }
        }
        if (tf == true) {
            while (i < 20) {
                data[i] = f * (Math.pow(mh, j - 1));
                j++;
                i++;
            }
        }
        ArrayAdapter<Double> adp = new ArrayAdapter<Double>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,data);
        lv.setAdapter(adp);
    }

}
