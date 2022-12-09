package com.example.encryptdecrypt;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup modeSelectGroup;
    Encrypt encrypt = new Encrypt();// BAIE BELANGRIK!!!!!!!
    Decrypt decrypt = new Decrypt();// BAIE BELANGRIK!!!!!!!
    EditText editCodeWord;
    EditText paragraph;
    int numCycles = 1, codeChangeFrequency = 1;
    String codeString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modeSelectGroup = findViewById(R.id.rgpModeSelectGroup);
        Button doTheThing = findViewById(R.id.btnDoTheThing);
        editCodeWord = findViewById(R.id.editCodeWord);
        paragraph = findViewById(R.id.editMessage);
        codeString = editCodeWord.getText().toString();
        doTheThing.setOnClickListener(v -> changeMode(v));
    }

    public void changeMode(View v){
        //finds the selected radioButton
        int radioId = modeSelectGroup.getCheckedRadioButtonId();
        RadioButton selectedButton = findViewById(radioId);

        //gets the number of encryption/decryption cycles
        EditText editNumCycles = findViewById(R.id.editNumCycles);
        numCycles = Integer.parseInt(editNumCycles.getText().toString());


        //prompts user to enter a codeword or some text if empty
        if(editCodeWord.getText().toString().equals("") || paragraph.getText().toString().equals("")){
            paragraph.setText(paragraph.getText().toString());
            Toast.makeText(this, "You need to write text and select a codeword.", Toast.LENGTH_SHORT).show();
        }
        //activates the selected mode
        else if (selectedButton.getText().toString().equals("Encrypt")){
            //encrypt
            for(int i = 0; i < numCycles/codeChangeFrequency; i++){
                for (int j = 0; j < codeChangeFrequency; j++){
                    paragraph.setText(encrypt.conversion(codeString, paragraph.getText().toString()));
                }
                codeString = encrypt.conversion(codeString, codeString);
            }
        }
        else {
            //decrypt
            for (int i = 0; i < numCycles/codeChangeFrequency; i++) {
                for (int j = 0; i < codeChangeFrequency; j++){
                    paragraph.setText(decrypt.conversion(codeString, paragraph.getText().toString()));
                }
                codeString = decrypt.conversion(codeString, codeString);
            }
        }
    }
}