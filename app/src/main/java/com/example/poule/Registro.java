package com.example.poule;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Registro extends AppCompatActivity {
    private String TAG;
    EditText txtNombreR,txtContraR,txtConfirmar;
    Button btnRegistrar;
    private FirebaseAuth mAuth;

    //public static ArrayList<Usuario> usuario = new ArrayList<Usuario>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        mAuth = FirebaseAuth.getInstance();
        conectar();
        registrarUsuario();
    }



    private void conectar() {
        txtConfirmar = findViewById(R.id.txtConfirmar);
        txtContraR = findViewById(R.id.txtContraseña);
        txtNombreR = findViewById(R.id.txtNombreRegistro);
        btnRegistrar = findViewById(R.id.btnRegistrar);

    }

    private void registrarUsuario(){
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(txtContraR.getText().toString().isEmpty() && txtConfirmar.getText().toString().isEmpty() &&
                        txtNombreR.getText().toString().isEmpty())){
                    if(txtContraR.getText().toString().equals(txtConfirmar.getText().toString())){
                        crearUsuario();

                    }else{
                        Toast.makeText(getApplicationContext(),"Las contraseñas no coinciden",Toast.LENGTH_LONG).show();

                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Ingrese todos los campos",Toast.LENGTH_LONG).show();
                }


            }
        });

    }
    public void crearUsuario(){
        String email = txtNombreR.getText().toString();
        String password = txtContraR.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(Registro.this,RegistroDatos.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Registro.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }
}
