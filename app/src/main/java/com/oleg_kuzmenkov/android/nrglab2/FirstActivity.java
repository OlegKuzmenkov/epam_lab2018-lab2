package com.oleg_kuzmenkov.android.nrglab2;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    private Button mDateButton;
    private TextView mDateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        mDateTextView = (TextView)findViewById(R.id.date_text_view);

        mDateButton = (Button)findViewById(R.id.button);
        mDateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                checkCustomPermission();
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    checkCustomPermission();
                } else {
                    Toast.makeText(this,"Call Permission Not Granted", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
    }

    /**
     * Check custom permission and start protected Activity from another module
     */

    public void checkCustomPermission(){
        int permissionCheck = ContextCompat.checkSelfPermission(this, "com.oleg_kuzmenkov.android.mypermission");

        if(permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{"com.oleg_kuzmenkov.android.mypermission"}, 1);
        } else {
            startActivity();
        }
    }

    /**
     * Start ptotected Activity
     */

    public void startActivity(){
        Intent intent = new Intent();
        intent.setAction("com.oleg_kuzmenkov.android.DANGEROUS_ACTIVITY");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setClassName("com.oleg_kuzmenkov.android.nrglab2part2", "com.oleg_kuzmenkov.android.nrglab2part2.SecondActivity");
        startActivity(intent);
    }
}
