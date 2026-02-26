package com.example.splashinicio;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PlayGamesActivity extends AppCompatActivity {

    private Button botonSnakeGame;
    private Button boton2000Game;
    private Button botonVolver;
    private Button botonSeleccionadoActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_games);

        inicializarVistas();
        configurarListeners();
        seleccionarBoton(botonSnakeGame);
    }

    private void inicializarVistas() {
        botonSnakeGame = findViewById(R.id.boton_snake_game);
        boton2000Game = findViewById(R.id.boton_2000_game);
        botonVolver = findViewById(R.id.boton_volver_play_games);

        TextView titulo = findViewById(R.id.texto_titulo_play_games);
        titulo.setText("SELECT GAME");
    }

    private void configurarListeners() {
        // Botón Snake Game (CORREGIDO)
        botonSnakeGame.setOnClickListener(v -> {
            seleccionarBoton(botonSnakeGame);
            // Abrir el juego Snake
            Intent intent = new Intent(PlayGamesActivity.this, com.example.sakeclassic.SnakeGameActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        botonSnakeGame.setOnTouchListener((v, event) -> {
            manejarTouch(event, botonSnakeGame);
            return false;
        });

        // Botón 2000 Game
        boton2000Game.setOnClickListener(v -> {
            seleccionarBoton(boton2000Game);
            // Aquí iniciarías el juego 2000 (cuando lo tengas)
        });

        boton2000Game.setOnTouchListener((v, event) -> {
            manejarTouch(event, boton2000Game);
            return false;
        });

        // Botón Volver
        botonVolver.setOnClickListener(v -> {
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        botonVolver.setOnTouchListener((v, event) -> {
            manejarTouch(event, botonVolver);
            return false;
        });
    }

    private void manejarTouch(MotionEvent event, Button boton) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                boton.setScaleX(1.05f);
                boton.setScaleY(1.05f);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                boton.setScaleX(1.0f);
                boton.setScaleY(1.0f);
                break;
        }
    }

    private void seleccionarBoton(Button botonNuevoSeleccionado) {
        if (botonSeleccionadoActual != null) {
            botonSeleccionadoActual.setSelected(false);
            botonSeleccionadoActual.setTextColor(getResources().getColor(android.R.color.darker_gray));
        }

        botonNuevoSeleccionado.setSelected(true);
        botonNuevoSeleccionado.setTextColor(getResources().getColor(android.R.color.white));
        botonSeleccionadoActual = botonNuevoSeleccionado;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}