package com.example.poule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {
    ImageButton btnPerfil, btnVehiculos, btnRutas, btnViajes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        conectar();
        transiciones();
    }

    public void conectar(){
        btnPerfil = findViewById(R.id.btnPerfil);
        btnViajes = findViewById(R.id.btnViajes);
        btnVehiculos = findViewById(R.id.btnVehiculos);
        btnRutas = findViewById(R.id.btnRutas);
    }

    public void transiciones(){
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,PerfilUsuario.class);
                startActivity(intent);
            }
        });
        btnVehiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,VehiculosUsusario.class);
                startActivity(intent);
            }
        });
      /*  btnViajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,MainScreen.class);
                startActivity(intent);
            }
        });
        btnRutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,MainScreen.class);
                startActivity(intent);
            }
        });
*/

    }
}