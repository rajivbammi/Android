package com.android.rbammi.persistancedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PersistenceActivity extends AppCompatActivity {
    EditText etPersistence;
    //SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persistance);
        etPersistence = (EditText) findViewById(R.id.etPersist);
        //pref = PreferenceManager.getDefaultSharedPreferences(this);
        onLoad();
    }

    public void onLoad() {
        InputValue val = InputValue.queryMostRecent();
        if(val!= null) {
            etPersistence.setText(val.text);
        }
        //etPersistence.setText(pref.getString("text",""));
        etPersistence.requestFocus();

//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new InputStreamReader(
//                    openFileInput("File")));
//            String line;
//            StringBuffer buffer = new StringBuffer();
//            while ((line = reader.readLine()) != null) {
//                buffer.append(line);
//            }
//            line = buffer.toString();
//            etPersistence.setText(line);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void onPersist(View view) {
        String val = etPersistence.getText().toString();

        InputValue inputValue = new InputValue(val);
        inputValue.save();

        //SharedPreferences.Editor edit = pref.edit();
        //edit.putString("text", val);
        //edit.apply();

//        FileOutputStream fos = null;
//        try {
//            fos = openFileOutput("File", MODE_PRIVATE);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        BufferedWriter write = new BufferedWriter(new OutputStreamWriter(fos));
//        try {
//            write.write(val);
//            write.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Toast.makeText(this, "Saved Data...", Toast.LENGTH_SHORT).show();
    }
}
