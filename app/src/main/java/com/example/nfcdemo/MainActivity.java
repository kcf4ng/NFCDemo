package com.example.nfcdemo;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NFCAvailable();
    }


    //確認是否有開ＮＦＣ
    private void NFCAvailable() {
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter != null && nfcAdapter.isEnabled()){
            Toast.makeText(this,"NFC available :)", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"NFC not available :(", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Toast.makeText(this,"NFC intent received ! Good !",Toast.LENGTH_SHORT).show();
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        Intent intent = new Intent (this, MainActivity.class);
        intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        IntentFilter[] filter = new IntentFilter[]{};
        nfcAdapter.enableForegroundDispatch(this,pendingIntent,filter, null);
        super.onResume();
    }

    @Override
    protected void onPause() {
        nfcAdapter.disableForegroundDispatch(this);
        super.onPause();
    }

    

}
