package com.example.chapa.entregable1u2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nombre,control,correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre =(EditText) findViewById(R.id.txtNombre);
        control =(EditText) findViewById(R.id.txtControl);
        correo =(EditText) findViewById(R.id.txtCorreo);

        //Guardar
        findViewById(R.id.btnGuardar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });

        //Leer
        findViewById(R.id.btnLeer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperar();
            }
        });
    }

    private void guardar(){
        SharedPreferences datos=getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=datos.edit();

        String nom=nombre.getText().toString();
        String con=control.getText().toString();
        String cor=correo.getText().toString();

        editor.putString("nombre",nom);
        editor.putString("control",con);
        editor.putString("correo",cor);

        editor.commit();

        nombre.setText("");
        control.setText("");
        correo.setText("");
        Toast.makeText(getApplicationContext(),"Saved"+getEmojiByUnicode(0x1F44D),Toast.LENGTH_SHORT).show();
    }

    private void recuperar(){
        SharedPreferences datos=getSharedPreferences("data", Context.MODE_PRIVATE);

        String nom=datos.getString("nombre","Nada guardado");
        String con=datos.getString("control","");
        String cor=datos.getString("correo","");

        nombre.setText(nom);
        control.setText(con);
        correo.setText(cor);

        Toast.makeText(getApplicationContext(),"Recuperado"+getEmojiByUnicode(0x1F44D),Toast.LENGTH_SHORT).show();
    }

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}
