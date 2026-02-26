package com.example.splashinicio;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private Handler manejador;
    private int contadorImagenes = 0;
    private final int[] idsImagenes = {
            R.id.imagen_snake,
            R.id.imagen_dosmil,
            R.id.imagen_proxima_1,
            R.id.imagen_proxima_2
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Ocultar todas las imágenes primero
        ocultarTodasLasImagenes();

        // Crear handler
        manejador = new Handler();

        // Iniciar secuencia
        manejador.postDelayed(this::mostrarSiguienteImagen, 300);

        // Programar cambio a Main Menu después de 5 segundos
        manejador.postDelayed(this::irAMainMenu, 5000);
    }

    private void ocultarTodasLasImagenes() {
        for (int id : idsImagenes) {
            ImageView imagen = findViewById(id);
            imagen.setVisibility(View.INVISIBLE);
        }
    }

    private void mostrarSiguienteImagen() {
        if (contadorImagenes < idsImagenes.length) {
            ImageView imagenActual = findViewById(idsImagenes[contadorImagenes]);
            imagenActual.setVisibility(View.VISIBLE);

            Animation animacion = AnimationUtils.loadAnimation(this, R.anim.fade_in);
            imagenActual.startAnimation(animacion);

            contadorImagenes++;

            // Programar siguiente imagen si hay más
            if (contadorImagenes < idsImagenes.length) {
                manejador.postDelayed(this::mostrarSiguienteImagen, 300);
            }
        }
    }

    private void irAMainMenu() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        // Limpiar handlers para evitar memory leaks
        if (manejador != null) {
            manejador.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }
}