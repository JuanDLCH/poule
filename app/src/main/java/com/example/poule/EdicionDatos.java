package com.example.poule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class EdicionDatos extends AppCompatActivity {
    EditText txtNombre, txtApellidos, txtCedula, txtDireccion, txtFecha, txtEmail;
    Button btnGuardar, btnCancelar;
    String key;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_datos);
        conectar();
        getDatos();
        setDatos();
        cancelar();
    }

    private void conectar() {
        txtNombre = findViewById(R.id.txtNombre);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtCedula = findViewById(R.id.txtCedula);
        txtFecha = findViewById(R.id.txtFecha);
        txtEmail = findViewById(R.id.txtEmail);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnCancelar = findViewById(R.id.btnCancelar);

    }

    private String getEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail = user.getEmail();
        if (user != null) {
        } else {
            userEmail = "Email not  Found";
        }
        return userEmail;
    }

    private void getDatos() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.child("Usuarios");

        Query q = ref.orderByChild("email").equalTo(getEmail());
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String cedula = dataSnapshot.child("dni").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String direccion = dataSnapshot.child("direccion").getValue(String.class);
                    String nombre = dataSnapshot.child("nombre").getValue(String.class);
                    String apellidos = dataSnapshot.child("apellidos").getValue(String.class);
                    String fecha = dataSnapshot.child("fechaNacimiento").getValue(String.class);
                    txtCedula.setText(cedula);
                    txtApellidos.setText(apellidos);
                    txtDireccion.setText(direccion);
                    txtFecha.setText(fecha);
                    txtEmail.setText(email);
                    txtNombre.setText(nombre);
                    key = dataSnapshot.getKey();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setDatos() {

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap User = new HashMap();
                User.put("apellidos",txtApellidos.getText().toString());
                User.put("nombre", txtNombre.getText().toString());
                User.put("direccion",txtDireccion.getText().toString());
                User.put("email",txtEmail.getText().toString());
                 mDatabase = FirebaseDatabase.getInstance().getReference("Usuarios");
                 mDatabase.child(key).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {

                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Informacion Actualizada",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(EdicionDatos.this, PerfilUsuario.class);
                            startActivity(intent);

                        }else{

                            Toast.makeText(getApplicationContext(),"Ha Ocurrido un error",Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });
    }

    private void cancelar(){
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Edicion Cancelada", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EdicionDatos.this, PerfilUsuario.class);
                startActivity(intent);
            }
        });

    }
}