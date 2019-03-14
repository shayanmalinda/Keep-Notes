package com.im.keepnotes;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class viewnotesActivity extends AppCompatActivity {

    Map<Integer, String> data = new HashMap<Integer, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewnotes);
    }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event)
//    {
//        //replaces the default 'Back' button action
//        if(keyCode==KeyEvent.KEYCODE_BACK)
//        {
//            //do whatever you want the 'Back' button to do
//            //as an example the 'Back' button is set to start a new Activity named 'NewActivity'
//            this.startActivity(new Intent(viewnotesActivity.this,MainActivity.class));
//        }
//        overridePendingTransition(0,0);
//        return true;
//    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();

        File folder = new File(getFilesDir()+File.separator+"notes");
        if(!folder.exists()){
            folder.mkdir();
        }

        List<String> list = new ArrayList<String>();

        File[] files = folder.listFiles();
        int index = 0;
        data.clear();

        for(File file:files){
            data.put(index,file.getName());
            index++;
            try {
                FileInputStream fis = new FileInputStream(file);
                byte[] chars = new byte[fis.available()];
                fis.read(chars);
                fis.close();

                String note = new String(chars);
                list.add(note);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        int layout = android.R.layout.simple_list_item_1;


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,layout,list);
        ListView lv = findViewById(R.id.notes_list);
        lv.setAdapter(adapter);
        lv.setBackgroundColor(getColor(R.color.colorPrimary));

//
//        TextView tv = findViewById(android.R.id.text1);
//        tv.setTextColor(Color.WHITE);
//        tv.setTextSize(18);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view;
                String note = tv.getText().toString();
                String file = data.get(position);

                Intent intent = new Intent(viewnotesActivity.this,editnoteActivity.class);

                intent.putExtra("note",note);
                intent.putExtra("file",file);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }
}
