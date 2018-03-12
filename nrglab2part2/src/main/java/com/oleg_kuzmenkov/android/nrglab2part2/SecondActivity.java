package com.oleg_kuzmenkov.android.nrglab2part2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    private Button mSendLetterButton;
    private EditText mEmailAddressField,mEmailSubjectField,mEmailTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mEmailAddressField = (EditText) findViewById(R.id.email_adress);
        mEmailSubjectField = (EditText) findViewById(R.id.email_subject);
        mEmailTextField = (EditText) findViewById(R.id.email_text);

        mSendLetterButton = (Button)findViewById(R.id.send_letter_button);
        mSendLetterButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                /*Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"destination@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "email subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT   , "email body");
                startActivity(Intent.createChooser(emailIntent, "Send Email"));*/
                //startActivity(emailIntent);

                Intent send = new Intent(Intent.ACTION_SENDTO);
                String uriText = "mailto:" + Uri.encode(mEmailAddressField.getText().toString()) +
                        "?subject=" + Uri.encode(mEmailSubjectField.getText().toString()) +
                        "&body=" + Uri.encode(mEmailTextField.getText().toString());
                Uri uri = Uri.parse(uriText);

                send.setData(uri);
                startActivity(Intent.createChooser(send, "Send mail via:"));
            }
        });
    }
}
