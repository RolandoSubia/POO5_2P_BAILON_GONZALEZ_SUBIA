package com.pooespol.appdeconsulta;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedalleroActivity extends AppCompatActivity {

    private TableLayout medalleroTableLayout;
    private Button ordenarButton, salirButton;
    private List<PaisMedallas> medalleroList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medallero);

        medalleroTableLayout = findViewById(R.id.medalleroTableLayout);
        ordenarButton = findViewById(R.id.ordenarButton);
        salirButton = findViewById(R.id.salirButton);

        cargarDatosMedallero();

        mostrarTabla();

        ordenarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ordenarPorOro();
                mostrarTabla();
            }
        });

        salirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedalleroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void cargarDatosMedallero() {
        AssetManager assetManager = getAssets();
        try (InputStream inputStream = assetManager.open("Medallero.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String pais = parts[0].trim();
                    int oro = Integer.parseInt(parts[1].trim());
                    int plata = Integer.parseInt(parts[2].trim());
                    int bronce = Integer.parseInt(parts[3].trim());
                    medalleroList.add(new PaisMedallas(pais, oro, plata, bronce));
                }
            }

        } catch (IOException e) {
            Toast.makeText(this, "Error al leer el archivo medallero.txt", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void mostrarTabla() {
        if (medalleroTableLayout.getChildCount() > 1) {
            medalleroTableLayout.removeViews(1, medalleroTableLayout.getChildCount() - 1);
        }

        for (PaisMedallas paisMedallas : medalleroList) {
            TableRow row = new TableRow(this);

            TextView paisTextView = new TextView(this);
            paisTextView.setText(paisMedallas.getPais());
            paisTextView.setPadding(8, 8, 8, 8);
            paisTextView.setGravity(Gravity.CENTER);
            paisTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.table_cell_border));
            row.addView(paisTextView);

            TextView oroTextView = new TextView(this);
            oroTextView.setText(String.valueOf(paisMedallas.getOro()));
            oroTextView.setPadding(8, 8, 8, 8);
            oroTextView.setGravity(Gravity.CENTER);
            oroTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.table_cell_border));
            row.addView(oroTextView);

            TextView plataTextView = new TextView(this);
            plataTextView.setText(String.valueOf(paisMedallas.getPlata()));
            plataTextView.setPadding(8, 8, 8, 8);
            plataTextView.setGravity(Gravity.CENTER);
            plataTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.table_cell_border));
            row.addView(plataTextView);

            TextView bronceTextView = new TextView(this);
            bronceTextView.setText(String.valueOf(paisMedallas.getBronce()));
            bronceTextView.setPadding(8, 8, 8, 8);
            bronceTextView.setGravity(Gravity.CENTER);
            bronceTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.table_cell_border));
            row.addView(bronceTextView);

            medalleroTableLayout.addView(row);
        }
    }

    private void ordenarPorOro() {
        Collections.sort(medalleroList);
    }

    private static class PaisMedallas implements Comparable<PaisMedallas> {
        private final String pais;
        private final int oro;
        private final int plata;
        private final int bronce;

        public PaisMedallas(String pais, int oro, int plata, int bronce) {
            this.pais = pais;
            this.oro = oro;
            this.plata = plata;
            this.bronce = bronce;
        }

        public String getPais() {
            return pais;
        }

        public int getOro() {
            return oro;
        }

        public int getPlata() {
            return plata;
        }

        public int getBronce() {
            return bronce;
        }

        @Override
        public int compareTo(PaisMedallas otroPais) {
            return Integer.compare(otroPais.getOro(), this.oro);
        }
    }
}
