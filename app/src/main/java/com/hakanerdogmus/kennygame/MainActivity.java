package com.hakanerdogmus.kennygame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView gosterge1;
    TextView gosterge;
    int skor;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;

    ImageView[] resimDizi;

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gosterge1 = findViewById(R.id.gosterge1);
        gosterge = findViewById(R.id.gosterge);
        skor = 0;
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        resimDizi = new ImageView[] {imageView1, imageView2, imageView3, imageView4, imageView5, imageView6,imageView7, imageView8, imageView9};

        skor = 0;

        imajSakla();

        new CountDownTimer(10000,1000){ //geri saydırmak için millisinFuture:milisaniye cinsinden sayıyı yazıyoruz,CountDownInterval:kaç saniye aralıkla azalıcak onu yazıyoruz
            @Override
            public void onTick(long millisUntilFinished) {
                gosterge.setText("Time: " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                gosterge.setText("Zaman Doldu");
                handler.removeCallbacks(runnable);
                for (ImageView resim : resimDizi){
                    resim.setVisibility(View.INVISIBLE); // bütün resimleri saklar.
                }

                AlertDialog.Builder uyarı = new AlertDialog.Builder(MainActivity.this);
                uyarı.setTitle("TEKRAR OYNA");
                uyarı.setMessage("Tekrar Oynamak İstediğinden Emin misin?");
                uyarı.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                   //tekrar başlatsın.
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);


                    }
                });

                uyarı.setNegativeButton("HAYIR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "OYUN BİTTİ!", Toast.LENGTH_SHORT).show();
                    }
                });
                uyarı.show();

            }
        }.start()   ;

    }

    public void arttır(View view){

            gosterge1.setText("Score: " + skor);
            skor++;
        gosterge1.setText("Score: " + skor);

    }

    public void imajSakla(){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView resim : resimDizi){
                    resim.setVisibility(View.INVISIBLE); //bütün resimleri görünmez yapar.
                }

                Random random = new Random();
                int i = random.nextInt(9);      // i tanımladık ve i değerini 0-8 değerlerinden rastgele alasını sağladık
                resimDizi[i].setVisibility(View.VISIBLE); //resimleri rastgele görünür hale getirir.

                handler.postDelayed(this, 500);

            }
        };
            handler.post(runnable);

    }

}