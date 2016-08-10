package com.microsoft.AzureIntelligentServicesExample;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class welcomeActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

        if (!isFirstStart) {

            Intent i = new Intent(welcomeActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
        else{

            final EditText name = (EditText) findViewById(R.id.nameEditText);
            Button submitBtn = (Button) findViewById(R.id.submitButton);

            submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {

                    if (name.getText().toString().isEmpty()) {
                        name.setError("Enter a valid user name!");
                        return;
                    }

                    Intent i = new Intent(welcomeActivity.this, MainActivity.class);
                    startActivity(i);
                    SharedPreferences.Editor e = getPrefs.edit();
                    e.putBoolean("firstStart", false);
                    e.putString("userName", name.getText().toString());
                    e.apply();
                }
            });
        }
    }
}
