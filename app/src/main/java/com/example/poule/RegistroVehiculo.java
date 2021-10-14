package com.example.poule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.poule.Fabricas.FabricaDeVehiculos;
import com.example.poule.Interfaces.Vehiculo;
import com.example.poule.Interfaces.VehiculoTransporte;
import com.example.poule.Vehiculos.Carro;
import com.example.poule.Vehiculos.Moto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RegistroVehiculo extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String TAG;
    Button btnRegistrarV;
    EditText txtSoat, txtCedula, txtPlaca, txtMatricula, txtMarca, txtModelo;
    RadioButton rbtnCarro, rbtnMoto;
    String cedula;



    public class FabricaCarros implements VehiculoTransporte {
        private DatabaseReference mDatabase;

        public Vehiculo crearVehiculo(){
            mDatabase = FirebaseDatabase.getInstance().getReference();
            Carro carro = new Carro();
            carro.setCodigo(txtMatricula.getText().toString());
            carro.setPlaca(txtPlaca.getText().toString());
            carro.setCedulaConductor(txtCedula.getText().toString());
            carro.setSoatVigente(txtSoat.getText().toString());
            carro.setMarca(txtMarca.getText().toString());
            carro.setModelo(txtModelo.getText().toString());
            DatabaseReference newRef = mDatabase.child("Vehiculos").child("Carros").push();
            newRef.setValue(carro);
            return carro;

        }

    }
    public class FabricaMotos implements VehiculoTransporte {
        private DatabaseReference mDatabase;


        public Vehiculo crearVehiculo(){
            mDatabase = FirebaseDatabase.getInstance().getReference();
            Moto moto = new Moto();
            moto.setCodigo(txtMatricula.getText().toString());
            moto.setPlaca(txtPlaca.getText().toString());
            moto.setCedulaConductor(txtCedula.getText().toString());
            moto.setSoatVigente(txtSoat.getText().toString());
            moto.setMarca(txtMarca.getText().toString());
            moto.setModelo(txtModelo.getText().toString());
            DatabaseReference newRef = mDatabase.child("Vehiculos").child("Motos").push();
            newRef.setValue(moto);
            return moto;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_vehiculo);
        conectar();
        Registro();
        datePicker();

    }

    public void conectar(){
        txtCedula = findViewById(R.id.txtCedula);
        txtMatricula = findViewById(R.id.txtMatricula);
        txtPlaca = findViewById(R.id.txtPlaca);
        txtSoat = findViewById(R.id.txtDateSoat);
        btnRegistrarV = findViewById(R.id.btnRegistrarV);
        rbtnMoto = findViewById(R.id.rbtnMoto);
        rbtnCarro = findViewById(R.id.rbtnCarro);
        txtModelo = findViewById(R.id.txtModelo);
        txtMarca = findViewById(R.id.txtMarca);
        getCedula();

    }

    public void Registro(){
        btnRegistrarV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistroVehiculo.this,Home.class);
                if(!(txtCedula.getText().toString().isEmpty() && txtMatricula.getText().toString().isEmpty() &&
                        txtPlaca.getText().toString().isEmpty() && txtSoat.getText().toString().isEmpty() && (rbtnCarro.isChecked() == false &&
                        rbtnMoto.isChecked() == false))){
                    if (rbtnCarro.isChecked()){
                        FabricaCarros carros = new FabricaCarros();
                        FabricaDeVehiculos.crearFabricaDeVehiculo(carros);
                        startActivity(intent);
                    }else if (rbtnMoto.isChecked()){
                        FabricaMotos motos = new FabricaMotos();
                        FabricaDeVehiculos.crearFabricaDeVehiculo(motos);
                        startActivity(intent);
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Ingrese todos los campos",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void datePicker(){
        txtSoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.txtDateSoat:
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
                txtSoat.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
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

    private void getCedula(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.child("Usuarios");

        Query q = ref.orderByChild("email").equalTo(getEmail());
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String cedula = dataSnapshot.child("dni").getValue(String.class);
                    txtCedula.setText(cedula);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}

