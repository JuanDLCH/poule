package com.example.poule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegistroDatos extends AppCompatActivity {
    private String TAG;
    EditText txtNombre,txtApellidos,txtDNI,txtDireccion,txtDate, txtEmail;
    Button btnRegistro, btnRegistroC;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_datos);
        mAuth = FirebaseAuth.getInstance();
        conectar();
        getEmail();
        datePicker();
        Registrar();
        RegistrarConductor();


    }
    private void datePicker(){
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.txtDate:
                        showDatePickerDialog();
                        break;
            }   }
        });

    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                txtDate.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void conectar() {
        txtNombre = findViewById(R.id.txtNombre);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtDNI = findViewById(R.id.txtDNI);
        txtDate = findViewById(R.id.txtDate);
        btnRegistro = findViewById(R.id.btnRegistrar);
        btnRegistroC = findViewById(R.id.btnRegistrarC);
        txtEmail = findViewById(R.id.txtEmail);

    }
    private void Registrar(){
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(txtNombre.getText().toString().isEmpty() && txtDireccion.getText().toString().isEmpty() &&
                        txtApellidos.getText().toString().isEmpty() && txtDNI.getText().toString().isEmpty() && txtDate.getText().toString().isEmpty())){
                    RegistrarBD();
                    Intent intent = new Intent(RegistroDatos.this,Home.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Ingrese todos los campos",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void RegistrarConductor(){
        btnRegistroC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(txtNombre.getText().toString().isEmpty() && txtDireccion.getText().toString().isEmpty() &&
                        txtApellidos.getText().toString().isEmpty() && txtDNI.getText().toString().isEmpty() && txtDate.getText().toString().isEmpty())){
                    RegistrarBD();
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    String DNI = txtDNI.getText().toString();
                    String email = getEmail();
                    Conductor conductor = new Conductor();
                    conductor.setDni(DNI);
                    conductor.setEmail(email);
                    DatabaseReference newRef = mDatabase.child("Conductores").push();
                    newRef.setValue(conductor);
                    Intent intent = new Intent(RegistroDatos.this,RegistroVehiculo.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Ingrese todos los campos",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void RegistrarBD(){

        mDatabase = FirebaseDatabase.getInstance().getReference();
        String nombre = txtNombre.getText().toString();
        String apellidos = txtApellidos.getText().toString();
        String direccion = txtDireccion.getText().toString();
        String DNI = txtDNI.getText().toString();
        String date = txtDate.getText().toString();
        String email = getEmail();
        try {
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setApellidos(apellidos);
            usuario.setDireccion(direccion);
            usuario.setDni(DNI);
            usuario.setFechaNacimiento(date);
            usuario.setEmail(email);
            DatabaseReference newRef = mDatabase.child("Usuarios").push();
            newRef.setValue(usuario);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getEmail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail = user.getEmail();
        if (user != null) {
            txtEmail.setText(userEmail);
        } else {
            userEmail = "Email not  Found";
        }
        return userEmail;
    }
}