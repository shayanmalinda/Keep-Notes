package com.im.keepnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class editnoteActivity extends AppCompatActivity {

    String file = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editnote);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            String note = bundle.getString("note");
            file = bundle.getString("file");
            EditText etNote = findViewById(R.id.etnote);
            etNote.setText(note);
        }
    }
//    public boolean onKeyDown(int keyCode, KeyEvent event)
//    {
//        //replaces the default 'Back' button action
//        if(keyCode==KeyEvent.KEYCODE_BACK)
//        {
//            //do whatever you want the 'Back' button to do
//            //as an example the 'Back' button is set to start a new Activity named 'NewActivity'
//
//            this.startActivity(new Intent(editnoteActivity.this,viewnotesActivity.class));
//            overridePendingTransition(0,0);
//
//        }
//        return true;
//    }

    public void edit(View v){

        String filename ;
//        String filename  = (file!=null)? file:String.valueOf(new Date().getTime());

        if(file==null){
            filename = String.valueOf(new Date().getTime());
        }
        else{
            filename = file;
        }

        File file = new File(getFilesDir()+ File.separator+"notes"+File.separator+filename);

        try{

            FileOutputStream fos = new FileOutputStream(file);
            EditText etNote = findViewById(R.id.etnote);
            String note = etNote.getText().toString();

            fos.write(note.getBytes());
            fos.close();
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Error. Not Saved", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(this,viewnotesActivity.class);
        this.finish();
        startActivity(intent);
    }

    public void delete(View v){


        File fileToDelete = new File(getFilesDir()+File.separator+"notes"+File.separator+file);
        fileToDelete.delete();

        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this,viewnotesActivity.class);
        this.finish();
        startActivity(intent);
    }

}
