package com.pooespol.appdeconsulta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Modelo.Deporte;
import Modelo.Medalla;
import Modelo.Pais;
import Modelo.datosIncompletosException;

public class RegistrarCampeonActivity extends AppCompatActivity {

    private Spinner medallaSpinner;
    private Spinner paisSpinner;
    private Spinner deporteSpinner;
    private EditText atletaTxt;
    private RadioGroup generoRadioGroup;
    private Button guardarBtn;
    private Button salirBtn;

    private List<Pais> paisesList;
    private List<Deporte> deportesList;
    private List<Medalla> medallasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_campeon); // Asocia el layout correspondiente

        // Inicializa los elementos de la interfaz
        atletaTxt = findViewById(R.id.atletaTxt);
        generoRadioGroup = findViewById(R.id.generoRadioGroup); // Inicializa el RadioGroup
        guardarBtn = findViewById(R.id.guardarBtn);
        salirBtn = findViewById(R.id.salirBtn);

        // Inicializa los Spinners
        medallaSpinner = findViewById(R.id.medallaSpinner);
        paisSpinner = findViewById(R.id.paisSpinner);
        deporteSpinner = findViewById(R.id.deporteSpinner);

        // Inicializa las listas
        medallasList = new ArrayList<>();
        medallasList.add(new Medalla("Oro"));
        medallasList.add(new Medalla("Plata"));
        medallasList.add(new Medalla("Bronce"));

        ArrayAdapter<Medalla> medallaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, medallasList);
        medallaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medallaSpinner.setAdapter(medallaAdapter);

        paisesList = new ArrayList<>();
        paisesList.add(new Pais("Ecuador"));
        paisesList.add(new Pais("Alemania"));
        paisesList.add(new Pais("Argentina"));
        paisesList.add(new Pais("Australia"));
        paisesList.add(new Pais("Bélgica"));
        paisesList.add(new Pais("Brasil"));
        paisesList.add(new Pais("Canadá"));
        paisesList.add(new Pais("China"));
        paisesList.add(new Pais("Colombia"));
        paisesList.add(new Pais("Corea del Sur"));
        paisesList.add(new Pais("Dinamarca"));
        paisesList.add(new Pais("Egipto"));
        paisesList.add(new Pais("España"));
        paisesList.add(new Pais("Estados Unidos"));
        paisesList.add(new Pais("Francia"));
        paisesList.add(new Pais("Grecia"));
        paisesList.add(new Pais("Holanda"));
        paisesList.add(new Pais("India"));
        paisesList.add(new Pais("Irán"));
        paisesList.add(new Pais("Italia"));
        paisesList.add(new Pais("Japón"));
        paisesList.add(new Pais("México"));
        paisesList.add(new Pais("Noruega"));
        paisesList.add(new Pais("Nueva Zelanda"));
        paisesList.add(new Pais("Países Bajos"));
        paisesList.add(new Pais("Polonia"));
        paisesList.add(new Pais("Reino Unido"));
        paisesList.add(new Pais("Rusia"));
        paisesList.add(new Pais("Suecia"));
        paisesList.add(new Pais("Suiza"));
        paisesList.add(new Pais("Turquía"));
        paisesList.add(new Pais("Ucrania"));
        ArrayAdapter<Pais> paisAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paisesList);
        paisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paisSpinner.setAdapter(paisAdapter);

        deportesList = new ArrayList<>();
        deportesList.add(new Deporte("Atletismo"));
        deportesList.add(new Deporte("Natación"));
        deportesList.add(new Deporte("Gimnasia"));
        deportesList.add(new Deporte("Ciclismo"));
        deportesList.add(new Deporte("Esgrima"));
        deportesList.add(new Deporte("Fútbol"));
        deportesList.add(new Deporte("Baloncesto"));
        deportesList.add(new Deporte("Voleibol"));
        deportesList.add(new Deporte("Tenis"));
        deportesList.add(new Deporte("Golf"));
        ArrayAdapter<Deporte> deporteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, deportesList);
        deporteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deporteSpinner.setAdapter(deporteAdapter);

        // Configura el botón Guardar
        guardarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarCampeon();
            }
        });

        // Configura el botón Salir
        salirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrarCampeonActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void registrarCampeon() {
        String medalla = medallaSpinner.getSelectedItem().toString();
        String pais = paisSpinner.getSelectedItem().toString();
        String deporte = deporteSpinner.getSelectedItem().toString();
        String atleta = atletaTxt.getText().toString();

        int selectedId = generoRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        String genero = selectedRadioButton != null ? selectedRadioButton.getText().toString() : "No especificado";

        try {
            if (medalla.isEmpty() || pais.isEmpty() || deporte.isEmpty() || atleta.isEmpty() || genero.equals("No especificado")) {
                throw new datosIncompletosException("Debe llenar todos los campos para registrar un campeón.");
            }

            // Obtener el directorio específico de la aplicación en el almacenamiento externo
            File directorio = getExternalFilesDir(null); // null para obtener el directorio raíz de archivos
            if (directorio != null) {
                File archivo = new File(directorio, "campeones.txt");
                FileWriter writer = new FileWriter(archivo, true);
                writer.append(medalla).append(", ").append(pais).append(", ").append(deporte)
                        .append(", ").append(atleta).append(", ").append(genero).append("\n");
                writer.close();

                Toast.makeText(this, "Campeón registrado con éxito", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al acceder al almacenamiento externo", Toast.LENGTH_SHORT).show();
            }

        } catch (datosIncompletosException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
