package com.example.poule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VehiculosUsusario extends AppCompatActivity {

    ImageButton btnPerfil, btnVehiculos, btnRutas, btnViajes;
    EditText txtCedula, txtMatricula, txtMarca, txtModelo,txtPlaca, txtSoat;

    String key, cedula;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculos_ususario);
        conectar();
        listarCarros();
        listarMotos();
    }

    private void listarCarros() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        mDatabase = database.child("Vehiculos/Carros");
        Query q = mDatabase.orderByChild("email").equalTo(getEmail());
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String matricula = dataSnapshot.child("codigo").getValue(String.class);
                    String marca = dataSnapshot.child("marca").getValue(String.class);
                    String modelo = dataSnapshot.child("modelo").getValue(String.class);
                    String placa = dataSnapshot.child("placa").getValue(String.class);
                    String vigenciaSoat = dataSnapshot.child("soatVigente").getValue(String.class);
                    txtCedula.setText(cedula);
                    txtMarca.setText(marca);
                    txtModelo.setText(modelo);
                    txtSoat.setText(vigenciaSoat);
                    txtPlaca.setText(placa);
                    txtMatricula.setText(matricula);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void listarMotos() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        mDatabase = database.child("Vehiculos/Motos");
        Query q = mDatabase.orderByChild("email").equalTo(getEmail());
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String matricula = dataSnapshot.child("codigo").getValue(String.class);
                    String marca = dataSnapshot.child("marca").getValue(String.class);
                    String modelo = dataSnapshot.child("modelo").getValue(String.class);
                    String placa = dataSnapshot.child("placa").getValue(String.class);
                    String vigenciaSoat = dataSnapshot.child("soatVigente").getValue(String.class);
                    txtCedula.setText(cedula);
                    txtMarca.setText(marca);
                    txtModelo.setText(modelo);
                    txtSoat.setText(vigenciaSoat);
                    txtPlaca.setText(placa);
                    txtMatricula.setText(matricula);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });

    }

    public void conectar(){
        btnPerfil = findViewById(R.id.btnPerfil);
        btnViajes = findViewById(R.id.btnViajes);
        btnVehiculos = findViewById(R.id.btnVehiculos);
        btnRutas = findViewById(R.id.btnRutas);
        txtCedula = findViewById(R.id.txtCedula);
        txtMarca = findViewById(R.id.txtMarca);
        txtMatricula = findViewById(R.id.txtMatricula);
        txtPlaca = findViewById(R.id.txtPlaca);
        txtSoat = findViewById(R.id.txtSoat);
        txtModelo = findViewById(R.id.txtModelo);
        getDatos();
    }



    private String getEmail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail = user.getEmail();
        if (user != null) {
        } else {
            userEmail = "Email not  Found";
        }
        return userEmail;
    }

    private void getDatos(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.child("Usuarios");

        Query q = ref.orderByChild("email").equalTo(getEmail());
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    cedula = dataSnapshot.child("dni").getValue(String.class);
                    key = dataSnapshot.getKey();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void transiciones(){
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VehiculosUsusario.this,PerfilUsuario.class);
                startActivity(intent);
            }
        });
        btnVehiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VehiculosUsusario.this,VehiculosUsusario.class);
                startActivity(intent);
            }
        });
   /*     btnViajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VehiculosUsusario.this,MainScreen.class);
                startActivity(intent);
            }
        });
        btnRutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VehiculosUsusario.this,MainScreen.class);
                startActivity(intent);
            }
        });
*/

    }
}