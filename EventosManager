import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;
import Modelo.Evento;

public class EventosManager {

    private static final String EVENTOS_BIN = "eventos.bin";
    private File filesDir;

    public EventosManager(File filesDir) {
        this.filesDir = filesDir;
    }

    public List<Evento> deserializarEventos() throws Exception {
        File archivoBin = new File(filesDir, EVENTOS_BIN);
        try (FileInputStream fis = new FileInputStream(archivoBin);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (List<Evento>) ois.readObject();
        }
    }
}
