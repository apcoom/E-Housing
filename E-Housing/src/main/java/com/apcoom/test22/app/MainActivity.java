package com.apcoom.test22.app;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.os.Vibrator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;


@SuppressLint("HandlerLeak")
public class MainActivity extends Activity {
    //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
    // Debugging
    //coomentario de prueba :)
    public static final String TAG = "LEDv0";
    public static final boolean D = true;
    // Tipos de mensaje enviados y recibidos desde el Handler de ConexionBT
    public static final int Mensaje_Estado_Cambiado = 1;
    public static final int Mensaje_Leido = 2;
    public static final int Mensaje_Escrito = 3;
    public static final int Mensaje_Nombre_Dispositivo = 4;
    public static final int Mensaje_TOAST = 5;
    public static final int MESSAGE_Desconectado = 6;
    public static final int REQUEST_ENABLE_BT = 7;
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";
    private HttpClient httpClient;

    //Nombre del dispositivo conectado
    private String mConnectedDeviceName = null;
    // Adaptador local Bluetooth
    private BluetoothAdapter AdaptadorBT = null;
    //Objeto miembro para el servicio de ConexionBT
    private ConexionBT Servicio_BT = null;
    //Vibrador
    private Vibrator vibrador;
    //variables para el Menu de conexión
    private boolean seleccionador=false;
    public int Opcion= R.menu.menu;
    //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
    private Variables variable=new Variables();
    private char c;
    private String bandera;
    private int correcto;
    private boolean apagado=false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        final ImageButton BotonLed = (ImageButton)findViewById(R.id.Led1);
        final Button Actualizar = (Button)findViewById(R.id.actualizar);
        final Button BtnPrender = (Button)findViewById(R.id.guardarPrender);
        final Button BtnApagar = (Button)findViewById(R.id.guardarApagar);
        final EditText horaPrender = (EditText)findViewById(R.id.horaEncendido);
        final EditText horaApagar = (EditText)findViewById(R.id.horaApagado);
        final EditText fechaPrender = (EditText)findViewById(R.id.fechaEncendido);
        final EditText fechaApagar = (EditText)findViewById(R.id.fechaApagado);

        // Creating HTTP client
        final HttpClient httpClient = new DefaultHttpClient();
        // Creating HTTP Post
        final HttpPost httpPost = new HttpPost("http://www.mrcupon.com");

        BotonLed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View vv) {
                sendMessage("131122174800B");
                /*if(correcto==1){
                    // Making HTTP Request
                    try {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                        HttpResponse response = httpClient.execute(httpPost);
                        // writing response to log
                        Log.d("Http Response:", response.toString());

                    } catch (ClientProtocolException e) {
                        // writing exception to log
                        e.printStackTrace();

                    } catch (IOException e) {
                        // writing exception to log
                        e.printStackTrace();
                    }
                    String varString="";
                    if(apagado){
                        varString="http://www.mrcupon.com?foco=1";
                        apagado=false;
                    }else{
                        varString="http://www.mrcupon.com?foco=0";
                        apagado=true;
                    }
                    try {
                        URL url = new URL(varString);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                        StringBuilder sb =  new StringBuilder();
                        BufferedReader r = new BufferedReader(new InputStreamReader(in),1000);
                        for(String line = r.readLine(); line != null; line=r.readLine()) {
                            sb.append(line);
                        }
                        in.close();
                        urlConnection.disconnect();
                        Log.d("SERVIDOR_REMOTO", sb.toString());
                    } catch(Exception e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(getApplicationContext(), "Se actualizo el estado del foco", Toast.LENGTH_SHORT).show();
                    correcto=0;
                }*/

                /*try {
                    if(D) Log.e("HTTTP","CONFIGURA ENCCENDIDO");
                    httpPost.setEntity(new UrlEncodedFormEntity(encendido));
                } catch (UnsupportedEncodingException e) {
                    // writing error to Log
                    e.printStackTrace();
                }*/


            }
        });//fin de metodo de BotonLed

        Actualizar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View vv) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df3 = new SimpleDateFormat("HH:mm:ss");
                df3.format(c.getTime());
                String var= ""+c.getTime();
                String var2 = ""+var.charAt(17)+var.charAt(18)+var.charAt(14)+var.charAt(15)+var.charAt(11)+var.charAt(12)+"102213A";
                Log.d("Fecha",var);
                Log.d("String",var2);
                Log.d("actualizar","si lo hace");
                sendMessage(var2);
                if(correcto==1){

                    Toast.makeText(getApplicationContext(), "Se actualizo la fecha del dispositivo", Toast.LENGTH_SHORT).show();
                }
                //sendMessage("131122174800B");
            }
        });

        BtnPrender.setOnClickListener(new View.OnClickListener() {
            public void onClick(View vv) {
                if(horaPrender.getText().length()==0){
                    Toast.makeText(getApplicationContext(), "Proporcione la hora de apagado", Toast.LENGTH_SHORT).show();
                }else{
                    if(fechaPrender.getText().length()==0){
                        Toast.makeText(getApplicationContext(), "Proporcione la fecha de apagado", Toast.LENGTH_SHORT).show();
                    }else{
                        String var= horaPrender.getText().toString();
                        //String var3= fechaPrender.getText().toString();
                        String var2 = ""+var.charAt(6)+var.charAt(7)+var.charAt(3)+var.charAt(4)+var.charAt(0)+var.charAt(1)+"102213D";
                        Log.d("Entro",var2);
                        Log.d("PRENDER","si prende");
                        //sendMessage("131122174800B")

                        sendMessage(var2);
                        if(correcto==1){
                            // Making HTTP Request
                            /*try {
                                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                                StrictMode.setThreadPolicy(policy);
                                HttpResponse response = httpClient.execute(httpPost);
                                // writing response to log
                                Log.d("Http Response:", response.toString());

                            } catch (ClientProtocolException e) {
                                // writing exception to log
                                e.printStackTrace();

                            } catch (IOException e) {
                                // writing exception to log
                                e.printStackTrace();
                            }*/

                            Log.d("SERVIDOR_REMOTO", "EMPIEZA TRY-CATCH");
                            try {
                                URL url = new URL("http://www.mrcupon.com/config?apagado="+var);
                                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                                StringBuilder sb =  new StringBuilder();
                                BufferedReader r = new BufferedReader(new InputStreamReader(in),1000);
                                for(String line = r.readLine(); line != null; line=r.readLine()) {
                                    sb.append(line);
                                }
                                in.close();
                                urlConnection.disconnect();
                                Log.d("SERVIDOR_REMOTO", sb.toString());
                            } catch(Exception e) {
                                e.printStackTrace();
                            }

                            Toast.makeText(getApplicationContext(), "Se guardo la fecha para apagar", Toast.LENGTH_SHORT).show();
                            correcto=0;
                        }
                    }
                }
            }
        });//fin de metodo de BotonLed

        BtnApagar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View vv) {
                if(horaApagar.getText().length()==0){
                    Toast.makeText(getApplicationContext(), "Proporcione la hora de encendido", Toast.LENGTH_SHORT).show();
                }else{
                    if(fechaApagar.getText().length()==0){
                        Toast.makeText(getApplicationContext(), "Proporcione la fecha de encendido", Toast.LENGTH_SHORT).show();
                    }else{
                        String var= horaApagar.getText().toString();
                        //String var3= fechaApagar.getText().toString();
                        String var2 = ""+var.charAt(6)+var.charAt(7)+var.charAt(3)+var.charAt(4)+var.charAt(0)+var.charAt(1)+"102213C";
                        Log.d("Entro",var2);
                        //sendMessage("131122174800B");
                        Log.d("APAGAR","si lo apaga");

                        sendMessage(var2);
                        if(correcto==1){
                            // Making HTTP Request
                           /* try {
                                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                                StrictMode.setThreadPolicy(policy);
                                HttpResponse response = httpClient.execute(httpPost);
                                // writing response to log
                                Log.d("Http Response:", response.toString());

                            } catch (ClientProtocolException e) {
                                // writing exception to log
                                e.printStackTrace();

                            } catch (IOException e) {
                                // writing exception to log
                                e.printStackTrace();
                            }*/

                            Log.d("SERVIDOR_REMOTO", "EMPIEZA TRY-CATCH");
                            try {
                                URL url = new URL("http://www.mrcupon.com?encendido="+var);
                                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                                StringBuilder sb =  new StringBuilder();
                                BufferedReader r = new BufferedReader(new InputStreamReader(in),1000);
                                for(String line = r.readLine(); line != null; line=r.readLine()) {
                                    sb.append(line);
                                }
                                in.close();
                                urlConnection.disconnect();
                                Log.d("SERVIDOR_REMOTO", sb.toString());
                            } catch(Exception e) {
                                e.printStackTrace();
                            }

                            Toast.makeText(getApplicationContext(), "Se guardo la fecha para encendido", Toast.LENGTH_SHORT).show();
                            correcto=0;
                        }
                    }
                }
            }
        });//fin de metodo de BotonLed
    }

    public  void onStart() {
        super.onStart();
        ConfigBT();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if (Servicio_BT != null) Servicio_BT.stop();//Detenemos servicio
    }

    public void ConfigBT(){
        // Obtenemos el adaptador de bluetooth
        AdaptadorBT = BluetoothAdapter.getDefaultAdapter();
        if (AdaptadorBT.isEnabled()) {//Si el BT esta encendido,
            if (Servicio_BT == null) {//y el Servicio_BT es nulo, invocamos el Servicio_BT
                Servicio_BT = new ConexionBT(this, mHandler);
            }
        }
        else{ if(D) Log.e("Setup", "Bluetooth apagado...");
            Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetooth, REQUEST_ENABLE_BT);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Una vez que se ha realizado una actividad regresa un "resultado"...
        switch (requestCode) {

            case REQUEST_ENABLE_BT://Respuesta de intento de encendido de BT
                if (resultCode == Activity.RESULT_OK) {//BT esta activado,iniciamos servicio
                    ConfigBT();
                } else {//No se activo BT, salimos de la app
                    finish();}



        }//fin de switch case
    }//fin de onActivityResult



    @Override
    public boolean onPrepareOptionsMenu(Menu menux){
        //cada vez que se presiona la tecla menu  este metodo es llamado
        menux.clear();//limpiamos menu actual
        if (seleccionador==false)Opcion=R.menu.menu;//dependiendo las necesidades
        if (seleccionador==true)Opcion=R.menu.desconecta;  // crearemos un menu diferente
        getMenuInflater().inflate(Opcion, menux);
        return super.onPrepareOptionsMenu(menux);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Conexion:
                if(D) Log.e("conexion", "conectandonos");
                vibrador = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrador.vibrate(1000);
                String address = "20:13:05:31:27:76";//Direccion Mac del  rn42
                BluetoothDevice device = AdaptadorBT.getRemoteDevice(address);
                Servicio_BT.connect(device);
                return true;

            case R.id.desconexion:
                if (Servicio_BT != null) Servicio_BT.stop();//Detenemos servicio
                return true;
        }//fin de swtich de opciones
        return false;
    }//fin de metodo onOptionsItemSelected



    public  void sendMessage(String message) {
        if (Servicio_BT.getState() == ConexionBT.STATE_CONNECTED) {//checa si estamos conectados a BT
            if (message.length() > 0) {   // checa si hay algo que enviar
                byte[] send = message.getBytes();//Obtenemos bytes del mensaje
                if(D) Log.e(TAG, "Mensaje enviado:"+ message);
                Servicio_BT.write(send);     //Mandamos a escribir el mensaje
            }
        } else Toast.makeText(this, "No conectado", Toast.LENGTH_SHORT).show();
    }//fin de sendMessage

    final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                case Mensaje_Escrito:
                    byte[] writeBuf = (byte[]) msg.obj;//buffer de escritura...
                    // Construye un String del Buffer
                    String writeMessage = new String(writeBuf);
                    if(D) Log.e(TAG, "Message_write  =w= "+ writeMessage);
                    correcto=1;
                    break;
                //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                case Mensaje_Leido:
                    byte[] readBuf = (byte[]) msg.obj;//buffer de lectura...
                    //Construye un String de los bytes validos en el buffer
                    String readMessage = new String(readBuf, 0, msg.arg1);
                    //bandera = readMessage;
                    if(D) Log.e(TAG, "Message_read   =r= "+ readMessage);
                    break;
                //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                case Mensaje_Nombre_Dispositivo:
                    mConnectedDeviceName = msg.getData().getString(DEVICE_NAME); //Guardamos nombre del dispositivo
                    Toast.makeText(getApplicationContext(), "Conectado con "+ mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                    seleccionador=true;
                    break;
                //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                case Mensaje_TOAST:
                    Toast.makeText(getApplicationContext(), msg.getData().getString(TOAST),
                            Toast.LENGTH_SHORT).show();
                    break;
                //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                case MESSAGE_Desconectado:
                    if(D) Log.e("Conexion","DESConectados");
                    seleccionador=false;
                    break;
                //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            }//FIN DE SWITCH CASE PRIMARIO DEL HANDLER
        }//FIN DE METODO INTERNO handleMessage
    };//Fin de Handler
}//Fin MainActivity