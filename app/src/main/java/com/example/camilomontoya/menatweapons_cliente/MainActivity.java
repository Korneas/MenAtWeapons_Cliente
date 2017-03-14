package com.example.camilomontoya.menatweapons_cliente;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import serial.Confirmacion;
import serial.Usuario;

public class MainActivity extends AppCompatActivity implements Observer {

    private String GROUP_ADDRESS;
    private EditText user, pass;
    private ImageButton login, signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Comunicacion.getInstance().addObserver(this);

        GROUP_ADDRESS = Comunicacion.getInstance().getGROUP_ADDRESS();

        login = (ImageButton) findViewById(R.id.iniciar_s);
        signin = (ImageButton) findViewById(R.id.registrar);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registro = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(registro);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registro = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registro);
            }
        });


    }

    public void aviso(String msg) {
        Toast notificacion = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        notificacion.show();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Confirmacion) {

        }
    }
}
