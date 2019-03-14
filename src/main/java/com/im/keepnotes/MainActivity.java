package com.im.keepnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event)
//    {
//        //replaces the default 'Back' button action
//        if(keyCode==KeyEvent.KEYCODE_BACK)
//        {
//            //do whatever you want the 'Back' button to do
//            //as an example the 'Back' button is set to start a new Activity named 'NewActivity'
//            this.finish();
//            System.exit(0);
//        }
//
//        overridePendingTransition(0,0);
////        overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
//        return true;
//    }

    public void close(View v){
        finish();
        System.exit(0);
    }

    public void viewnotes(View v){
        Intent intent = new Intent(this,viewnotesActivity.class);
        startActivity(intent);
    }

    public void newnote(View v){
        Intent intent = new Intent(this,newnoteActivity.class);
        startActivity(intent);
    }

}
