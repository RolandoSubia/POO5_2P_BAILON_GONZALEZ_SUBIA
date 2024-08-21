import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import java.util.Calendar;
import java.util.List;
import Modelo.Evento;

public class CalendarioActivity extends AppCompatActivity {

    private EditText fechaEditText;
    private TextView eventosHoyTextView;
    private TableLayout eventosTableLayout;
    private EventosManager eventosManager;
    private Calendar calendar;
    private Button salirButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        fechaEditText = findViewById(R.id.fechaEditText);
        eventosHoyTextView = findViewById(R.id.eventosHoyTextView);
        eventosTableLayout = findViewById(R.id.eventosTableLayout);
        salirButton = findViewById(R.id.salirButton);

        eventosManager = new EventosManager(getFilesDir());
        calendar = Calendar.getInstance();

        fechaEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatePicker();
            }
        });

        salirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mostrarDatePicker() {
        new DatePickerDialog(CalendarioActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {calendar.set(Calendar.YEAR, year);calendar.set(Calendar.MONTH, month);calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                actualizarFecha();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void actualizarFecha() {
        String fechaSeleccionada = String.format("%02d/%02d/%d", calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));

        fechaEditText.setText(fechaSeleccionada);

        mostrarEventos(fechaSeleccionada);
    }

    private void mostrarEventos(String fechaSeleccionada) {
        if (eventosTableLayout.getChildCount() > 1) {
            eventosTableLayout.removeViews(1, eventosTableLayout.getChildCount() - 1);
        }

        try {
            List<Evento> eventos = eventosManager.deserializarEventos();
            boolean eventosDisponibles = false;

            for (Evento evento : eventos) {
                if (evento.getFecha().equals(fechaSeleccionada)) {
                    eventosDisponibles = true;

                    TextView nuevoTextView = new TextView(this);
                    nuevoTextView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));
                    eventosTableLayout.addView(nuevoTextView);

                    TableRow row = new TableRow(this);

                    TextView deporteTextView = new TextView(this);
                    deporteTextView.setText(evento.getDeporte());
                    deporteTextView.setPadding(8, 8, 8, 8);
                    deporteTextView.setGravity(Gravity.CENTER);
                    deporteTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.table_cell_border));
                    row.addView(deporteTextView);

                    TextView horaTextView = new TextView(this);
                    horaTextView.setText(evento.getHora());
                    horaTextView.setPadding(8, 8, 8, 8);
                    horaTextView.setGravity(Gravity.CENTER);
                    horaTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.table_cell_border));
                    row.addView(horaTextView);

                    eventosTableLayout.addView(row);
                }
            }

            if (!eventosDisponibles) {
                Toast.makeText(this, "No hay eventos para esta fecha", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar los eventos", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}

