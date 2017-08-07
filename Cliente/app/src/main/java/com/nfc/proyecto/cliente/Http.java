package com.nfc.proyecto.cliente;

import android.content.Context;
import android.content.Entity;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.io.ContentLengthInputStream;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by sony on 03/08/2017.
 */

public class Http extends AsyncTask<String, Integer, String>{

    String resultado;
    JSONObject obj;
    HttpEntity entity;
    String url;
    String rest;
    Context context;

    public void setUrl(String url){
        this.url = url;
    }

    public void setEntity(HttpEntity entity){
       this.entity = entity;
   }

    public void setRest(String rest){ this.rest = rest; }

    public String getResultado(){ return this.resultado; }



    @Override
    protected String doInBackground(String... params) {
        postData();
        return null;
    }

    public void postData(){
        try {

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://" + url + rest);

            //Url del servidor
            //https://proyectoepsl.pythonanywhere.com/rest_sala/
            //http://192.168.2.129:8000/rest_sala/
            //HttpPost post = new HttpPost("https://proyectoepsl.pythonanywhere.com/rest_sala/");
            //Cabecera del envio de datos

            //HttpPost post = new HttpPost("http://" + url + "/rest_login/");
            httppost.setHeader("Content-Type", "application/json");
            httppost.setHeader("charset", "utf-8");
            //Construimos el objeto en formato JSON
            //JSONObject dato = new JSONObject();
            //El dato encritado en json se llama Hash
            //dato.put("Hash", a);
            //Creo entidad para enviar los datos
            //StringEntity entity = new StringEntity(dato.toString());
            httppost.setEntity(entity);
            //Realizo el envío
            HttpResponse resp = null;

            resp = httpClient.execute(httppost);


            //3º:Procesar la respuesta del servidor
            //Obtengo respuesta del servidor
            String respStr = EntityUtils.toString(resp.getEntity());
            //Creo un objeto Json con esta respuesta para poder acceder a los datos
            obj = new JSONObject(respStr);



            //4º:Segun la respuesta del servidor llevo a cabo las acciones.
            //Obtengo el objeto resultado de la respuesta
            resultado = obj.get("result").toString();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(String result){

    }
}
