package com.pooespol.appdeconsulta;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import Modelo.Evento;

public class InicializadorEventos {

    private File filesDir;

    public InicializadorEventos(File filesDir) {
        this.filesDir = filesDir;
    }

    public void crearYSerializarEventos() {

        List<Evento> eventos = new ArrayList<>();

        eventos.add(new Evento("Atletismo", "01/08/2024", "10:00"));
        eventos.add(new Evento("Natación", "01/08/2024", "12:00"));
        eventos.add(new Evento("Gimnasia", "01/08/2024", "14:00"));
        eventos.add(new Evento("Ciclismo", "01/08/2024", "16:00"));
        eventos.add(new Evento("Esgrima", "01/08/2024", "18:00"));

        eventos.add(new Evento("Fútbol", "02/08/2024", "10:00"));
        eventos.add(new Evento("Baloncesto", "02/08/2024", "12:00"));
        eventos.add(new Evento("Voleibol", "02/08/2024", "14:00"));
        eventos.add(new Evento("Tenis", "02/08/2024", "16:00"));
        eventos.add(new Evento("Golf", "02/08/2024", "18:00"));

        eventos.add(new Evento("Atletismo", "03/08/2024", "10:00"));
        eventos.add(new Evento("Natación", "03/08/2024", "12:00"));
        eventos.add(new Evento("Gimnasia", "03/08/2024", "14:00"));
        eventos.add(new Evento("Ciclismo", "03/08/2024", "16:00"));
        eventos.add(new Evento("Esgrima", "03/08/2024", "18:00"));

        eventos.add(new Evento("Fútbol", "04/08/2024", "10:00"));
        eventos.add(new Evento("Baloncesto", "04/08/2024", "12:00"));
        eventos.add(new Evento("Voleibol", "04/08/2024", "14:00"));
        eventos.add(new Evento("Tenis", "04/08/2024", "16:00"));
        eventos.add(new Evento("Golf", "04/08/2024", "18:00"));

        eventos.add(new Evento("Atletismo", "05/08/2024", "10:00"));
        eventos.add(new Evento("Natación", "05/08/2024", "12:00"));
        eventos.add(new Evento("Gimnasia", "05/08/2024", "14:00"));
        eventos.add(new Evento("Ciclismo", "05/08/2024", "16:00"));
        eventos.add(new Evento("Esgrima", "05/08/2024", "18:00"));

        eventos.add(new Evento("Fútbol", "06/08/2024", "10:00"));
        eventos.add(new Evento("Baloncesto", "06/08/2024", "12:00"));
        eventos.add(new Evento("Voleibol", "06/08/2024", "14:00"));
        eventos.add(new Evento("Tenis", "06/08/2024", "16:00"));
        eventos.add(new Evento("Golf", "06/08/2024", "18:00"));

        // Serializar la lista de eventos en un archivo binario
        try {
            File archivoBin = new File(filesDir, "eventos.bin");
            try (FileOutputStream fos = new FileOutputStream(archivoBin);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(eventos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
