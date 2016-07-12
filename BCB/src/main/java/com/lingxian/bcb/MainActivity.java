package com.lingxian.bcb;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        Button button = (Button) findViewById (R.id.button);
        assert button != null;
        button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                AlertDialog dialog = new AlertDialog.Builder (MainActivity.this).create ();
                dialog.show ();
            }
        });
    }
}
