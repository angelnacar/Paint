package com.example.cursomulti.paintii;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Dibujar drawview;
    ImageButton crea, pinta, borrado, color_verde, color_azul, color_rojo, color_negro, color_naranja, color_morado, color_rosa, color_gris;
    Context context = this;
    ImageButton guarda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawview=findViewById(R.id.dibujar);

        guarda=findViewById(R.id.guardar);
        crea=findViewById(R.id.nuevo);
        pinta=findViewById(R.id.pintar);
        borrado=findViewById(R.id.borrar);




        color_verde=findViewById(R.id.verde);
        color_azul=findViewById(R.id.azul);
        color_rojo=findViewById(R.id.rojo);
        color_negro=findViewById(R.id.negro);
        color_naranja=findViewById(R.id.naranja);
        color_morado=findViewById(R.id.morado);
        color_rosa=findViewById(R.id.rosa);
        color_gris=findViewById(R.id.gris);


        crea.setOnClickListener(this);
        pinta.setOnClickListener(this);
        borrado.setOnClickListener(this);
        color_verde.setOnClickListener(this);
        color_azul.setOnClickListener(this);
        color_rojo.setOnClickListener(this);
        color_negro.setOnClickListener(this);
        color_naranja.setOnClickListener(this);
        color_morado.setOnClickListener(this);
        color_rosa.setOnClickListener(this);
        color_gris.setOnClickListener(this);
          guarda.setOnClickListener(this);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){ //crea el menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.circulo:
                Toast.makeText(getApplicationContext(),"Opcion1",Toast.LENGTH_SHORT).show();
                drawview.Forma(1);
                return true;
            case R.id.rectangulo:
                drawview.Forma(2);
                Toast.makeText(getApplicationContext(),"subOpcion1",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.ovalo:
                Toast.makeText(getApplicationContext(),"subOpcion1",Toast.LENGTH_SHORT).show();
                drawview.Forma(3);
                return true;
            default:
                return true;

        }
        //return true;
    }




    @Override
    public void onClick(View v) {
        //int color = Color.parseColor(v.getTag().toString());
        //Toast.makeText(this, (""+g.findViewWithTag(getTaskId())),Toast.LENGTH_SHORT).show();
        //drawview.cambiarColor(color);
        switch (v.getId()){
            case R.id.borrar:

                final CharSequence[] items2 = {"Pequeño","Mediano","Grande"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Elige un tamaño");
                builder.setItems(items2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        Toast.makeText(getApplicationContext(),items2[item],Toast.LENGTH_SHORT).show();
                        switch (items2[item].toString()){
                            case "Pequeño":
                                drawview.Borrar(10);
                                break;
                            case "Mediano":
                                drawview.Borrar(25);
                                break;
                            case "Grande":
                                drawview.Borrar(40);
                                break;

                        }
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

                break;
            case R.id.guardar:
               drawview.buildDrawingCache();
                Bitmap bmap = drawview.getDrawingCache();
                Save fichero = new Save();
                fichero.SaveImage(context,bmap);
                break;
            case R.id.nuevo:
                drawview.Nuevo();
                break;
            case R.id.pintar:
                final CharSequence[] items = {"Pequeño","Mediano","Grande"};
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("Elige un tamaño");
                builder2.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        Toast.makeText(getApplicationContext(),items[item],Toast.LENGTH_SHORT).show();
                        switch (items[item].toString()){
                            case "Pequeño":
                                drawview.Dibujar(10);
                                break;
                            case "Mediano":
                                drawview.Dibujar(25);
                                break;
                            case "Grande":
                                drawview.Dibujar(40);
                                break;

                        }
                    }
                });
                AlertDialog alert2 = builder2.create();
                alert2.show();

                break;

            case R.id.verde:
                drawview.cambiarColor(0xFF669900);
                break;
            case R.id.azul:
                drawview.cambiarColor(0xFF00DDFF);
                break;
            case R.id.rojo:
                drawview.cambiarColor(0xFFCC0000);
                break;
            case R.id.negro:
                drawview.cambiarColor(0xFF000000);
                break;
            case R.id.naranja:
                drawview.cambiarColor(0xFFFF8800);
                break;
            case R.id.morado:
                drawview.cambiarColor(0xFF3F51B5);
                break;
            case R.id.rosa:
                drawview.cambiarColor(0xFFFF4081);
                break;
            case R.id.gris:
                drawview.cambiarColor(0xFF959192);
                break;

        }


    }
}
