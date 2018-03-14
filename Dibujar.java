package com.example.cursomulti.paintii;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import static android.graphics.Path.Direction.CCW;
import static android.graphics.Path.Direction.CW;

public class Dibujar extends View {

    //recorrido del dibujo
    private Path path;
    //dibujar y pintar el canvas
    private Paint pincel, canvasPaint;
    //iniciar color
    private int paintColor = 0xFF660000;
    //canvas
    private Canvas canvas;
    //canvas bitmap
    private Bitmap bitmap;
    private float touchX;
    private  float touchY;
    private int opciones = 0;


    public Dibujar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();

    }

    public void setupDrawing() {

        path = new Path();
        pincel = new Paint();
        pincel.setColor(paintColor);
        pincel.setAntiAlias(true);
        pincel.setStrokeWidth(8);
        pincel.setStyle(Paint.Style.STROKE);
        canvasPaint = new Paint(Paint.DITHER_FLAG); //evita el difunado de los colores
    }

    @Override
    protected void onSizeChanged(int x, int y, int width, int height) {
        //view given size
        super.onSizeChanged(x, y, width, height);
        bitmap = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);//para hacer una copia de los bits que compone el mapa
        canvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, canvasPaint);
        canvas.drawPath(path, pincel);
    }

   // @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
         touchX = event.getX();
         touchY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                if(opciones == 1){
                    path.addCircle(touchX,touchY,Math.abs(touchX-touchY), Path.Direction.CW);

                }else if(opciones==2){

                    //canvas.drawPath(),pincel);
                    path.addRoundRect(250,touchY,touchX,350,0,0,Path.Direction.CW);

                }else if(opciones == 3){
                    path.addOval(250,touchY,touchX,350,Path.Direction.CW);

                }

                else {
                    path.lineTo(touchX, touchY);
                }
                break;
            case MotionEvent.ACTION_UP:

                canvas.drawPath(path, pincel);
                path.reset();
                break;

            default:
                return false;
        }
        invalidate();
        return true;
    }

    public void cambiarColor(int color) {
        pincel.setColor(color);

    }

    public void Nuevo() {
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);

    }

    public void Borrar(int grosor) {

        pincel.setStrokeWidth(grosor);
        PorterDuff.Mode mode = PorterDuff.Mode.CLEAR;
        pincel.setXfermode(new PorterDuffXfermode(mode));



    }

    public void Dibujar(int grosor) {
        pincel.set(new Paint());
        pincel.setAntiAlias(true);
        pincel.setStrokeWidth(grosor);
        pincel.setStyle(Paint.Style.STROKE);
        opciones = 0;

    }

    public void Forma(int opcion) {
        opciones = opcion;
    }
}