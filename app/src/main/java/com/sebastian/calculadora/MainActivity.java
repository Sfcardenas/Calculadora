package com.sebastian.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNumero1, etNumero2;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumero1 = findViewById(R.id.etNumero1);
        etNumero2 = findViewById(R.id.etNumero2);
        tvResultado = findViewById(R.id.tvResultado);

        // Botón Limpiar
        findViewById(R.id.btnLimpiar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etNumero1.setText("");
                etNumero2.setText("");
                tvResultado.setText("Resultado:");
            }
        });

        // ===== SUMA - listener =====
        findViewById(R.id.btnSumar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { sumar(); }
        });

        // [AQUÍ VAN LOS LISTENERS DE LAS DEMÁS OPERACIONES]

        // ===== DIVISIÓN - listener =====
        findViewById(R.id.btnDividir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { dividir(); }
        });

        // ===== MULTIPLICACIÓN - listener =====
        findViewById(R.id.btnMultiplicar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { multiplicar(); }
        });

        // ===== RESTA - listener =====
        findViewById(R.id.btnRestar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { restar(); }
        });
    }

    // ---------- Métodos de apoyo ----------
    private Double leerNumero(EditText campo) {
        String texto = campo.getText().toString().trim();
        if (texto.isEmpty()) {
            campo.setError("Ingresa un número");
            return null;
        }
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            campo.setError("Número no válido");
            return null;
        }
    }

    private void mostrarResultado(String operacion, double resultado) {
        String texto = (resultado == Math.floor(resultado) && !Double.isInfinite(resultado))
                ? String.valueOf((long) resultado)
                : String.valueOf(resultado);
        tvResultado.setText("Resultado (" + operacion + "): " + texto);
    }

    // ===== SUMA - método =====
    private void sumar() {
        Double a = leerNumero(etNumero1), b = leerNumero(etNumero2);
        if (a == null || b == null) return;
        mostrarResultado("suma", a + b);
    }

    // [AQUÍ VAN LOS MÉTODOS DE LAS DEMÁS OPERACIONES]
    // ===== DIVISIÓN - método =====
    private void dividir() {
        Double a = leerNumero(etNumero1), b = leerNumero(etNumero2);
        if (a == null || b == null) return;
        if (b == 0) {
            Toast.makeText(this, "No se puede dividir entre cero", Toast.LENGTH_SHORT).show();
            return;
        }
        mostrarResultado("división", a / b);
    }
    // ===== MULTIPLICACIÓN - método =====
    private void multiplicar() {
        Double a = leerNumero(etNumero1), b = leerNumero(etNumero2);
        if (a == null || b == null) return;
        mostrarResultado("multiplicación", a * b);
    }
    // ===== RESTA - método =====
    private void restar() {
        Double a = leerNumero(etNumero1), b = leerNumero(etNumero2);
        if (a == null || b == null) return;
        mostrarResultado("resta", a - b);
    }
}