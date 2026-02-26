package com.example.splashinicio;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    // Variables para los botones
    private Button botonPlayGames;
    private Button botonViewScores;
    private Button botonSettings;
    private Button botonHelp;

    // Botón actualmente seleccionado
    private Button botonSeleccionadoActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Inicializar botones
        inicializarBotones();

        // Configurar listeners
        configurarListeners();

        // Seleccionar primer botón por defecto
        seleccionarBoton(botonPlayGames);
    }

    private void inicializarBotones() {
        botonPlayGames = findViewById(R.id.boton_play_games);
        botonViewScores = findViewById(R.id.boton_view_scores);
        botonSettings = findViewById(R.id.boton_settings);
        botonHelp = findViewById(R.id.boton_help);
    }

    private void configurarListeners() {
        // Listener para Play Games
        botonPlayGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarBoton(botonPlayGames);
                abrirPlayGames();
            }
        });

        botonPlayGames.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                manejarTouch(event, botonPlayGames);
                return false;
            }
        });

        // Listener para View Scores
        botonViewScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarBoton(botonViewScores);
                abrirViewScores();
            }
        });

        botonViewScores.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                manejarTouch(event, botonViewScores);
                return false;
            }
        });

        // Listener para Settings
        botonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarBoton(botonSettings);
                abrirSettings();
            }
        });

        botonSettings.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                manejarTouch(event, botonSettings);
                return false;
            }
        });

        // Listener para Help
        botonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarBoton(botonHelp);
                abrirHelp();
            }
        });

        botonHelp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                manejarTouch(event, botonHelp);
                return false;
            }
        });
    }

    private void manejarTouch(MotionEvent event, Button boton) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Efecto al tocar
                boton.setScaleX(1.05f);
                boton.setScaleY(1.05f);
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // Restaurar tamaño
                boton.setScaleX(1.0f);
                boton.setScaleY(1.0f);
                break;
        }
    }

    private void seleccionarBoton(Button botonNuevoSeleccionado) {
        // Deseleccionar botón anterior
        if (botonSeleccionadoActual != null) {
            botonSeleccionadoActual.setSelected(false);
            botonSeleccionadoActual.setTextColor(getResources().getColor(android.R.color.darker_gray));
        }

        // Seleccionar nuevo botón
        botonNuevoSeleccionado.setSelected(true);
        botonNuevoSeleccionado.setTextColor(getResources().getColor(android.R.color.white));

        // Actualizar referencia
        botonSeleccionadoActual = botonNuevoSeleccionado;
    }

    // ========== MÉTODOS PARA ABRIR ACTIVIDADES ==========

    private void abrirPlayGames() {
        Intent intent = new Intent(MainMenuActivity.this, PlayGamesActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void abrirViewScores() {
        Intent intent = new Intent(MainMenuActivity.this, ScoresActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void abrirSettings() {
        Intent intent = new Intent(MainMenuActivity.this, SettingsActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void abrirHelp() {
        Intent intent = new Intent(MainMenuActivity.this, HelpActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        // Volver al Splash o salir?
        // Por ahora solo salimos
        finish();
    }
}