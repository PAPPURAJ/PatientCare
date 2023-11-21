package com.example.patientcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference firebaseDatabase=FirebaseDatabase.getInstance().getReference();
    private MediaPlayer mediaPlayer;
    private String signal;
    private ImageView iv;
    private TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=findViewById(R.id.patientIv);
        msg=findViewById(R.id.patientMsg);
        firebaseDatabase.child("Signal").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                signal=snapshot.getValue(String.class);
                runSignal(signal);
               // Toast.makeText(getApplicationContext(), snapshot.getValue(String.class), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




    void runSignal(String signal){
        if(mediaPlayer!=null && mediaPlayer.isPlaying())
            return;
        switch (signal){
            case "1":
                mediaPlayer=MediaPlayer.create(this,R.raw.medicine);
                iv.setImageResource(R.drawable.medicine);
                msg.setText("I need medicine!");
                break;
            case "2":
                mediaPlayer=MediaPlayer.create(this,R.raw.sick);
                iv.setImageResource(R.drawable.sick);
                msg.setText("I'm feeling very sick!");
                break;
            case "3":
                mediaPlayer=MediaPlayer.create(this,R.raw.washroom);
                iv.setImageResource(R.drawable.washroom);
                msg.setText("I need to go to washroom!");
                break;
            case "4":
                mediaPlayer=MediaPlayer.create(this,R.raw.hungry);
                iv.setImageResource(R.drawable.sick);
                msg.setText("I need food!");
                break;
            default:
                if(mediaPlayer!=null && mediaPlayer.isPlaying())
                       mediaPlayer.stop();
                msg.setText("Don't worry! I'm okay!!");
                iv.setImageResource(R.drawable.patient);
                return;

        }
        mediaPlayer.start();
    }
}