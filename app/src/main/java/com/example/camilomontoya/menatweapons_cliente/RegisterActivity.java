package com.example.camilomontoya.menatweapons_cliente;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import serial.Usuario;

public class RegisterActivity extends AppCompatActivity implements Observer{

    private String GROUP_ADDRESS;
    private EditText user,pass;
    private ImageButton signin,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Comunicacion.getInstance().addObserver(this);

        GROUP_ADDRESS = Comunicacion.getInstance().getGROUP_ADDRESS();

        user = (EditText) findViewById(R.id.userR);
        pass = (EditText) findViewById(R.id.passwordR);

        signin = (ImageButton) findViewById(R.id.registrar);
        back = (ImageButton) findViewById(R.id.back);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comunicacion.getInstance().enviar(new Usuario(user.getText().toString(),pass.getText().toString()),GROUP_ADDRESS);
                aviso("Registro completado");
                Intent volver = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(volver);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volver = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(volver);
            }
        });
    }

    public void aviso(String msg) {
        Toast notificacion = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        notificacion.show();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
