package com.rku.tutorial09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    Button readAssets;
    Button readFile;
    Button writeFile;
    TextView data_of_file;
    EditText line_Write_into_file;

    final static String FILE_ASSETS="data.json";
    final static String FILE_WRITE="data.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readAssets=findViewById(R.id.btnReadAssets);
        readFile=findViewById(R.id.btnReadFile);
        writeFile=findViewById(R.id.btnWriteFile);
        data_of_file=findViewById(R.id.data);
        line_Write_into_file=findViewById(R.id.edittext);
    }

    public void readAssets(View view) {
        try {
            InputStream inputStream = getAssets().open(FILE_ASSETS);
            InputStreamReader inputStreamReader =new InputStreamReader(inputStream);
            BufferedReader reader=new BufferedReader(inputStreamReader);

            String Line;
            StringBuilder stringBuilder=new StringBuilder();
            while ((Line = reader.readLine()) !=null){
                stringBuilder.append(Line);
            }
            data_of_file.setText(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile(View view) {
        try {
            FileInputStream fin = openFileInput(FILE_WRITE);
            int c;
            String temp="";
            while( (c = fin.read()) != -1){
                temp = temp + Character.toString((char)c);
            }
            data_of_file.setText(temp);
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(View view) {
        try {
            FileOutputStream fOut = openFileOutput(FILE_WRITE, Context.MODE_PRIVATE);
            fOut.write(line_Write_into_file.getText().toString().getBytes());
            fOut.close();
            Toast.makeText(this, "File Write Successfully", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}