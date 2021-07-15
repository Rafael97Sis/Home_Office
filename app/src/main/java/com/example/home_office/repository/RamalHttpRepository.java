package com.example.home_office.repository;

import com.example.home_office.domain.Ramal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RamalHttpRepository {

    private static final String baseURL = "https://";

    public List<Ramal> findAll() {

        try {
            URL url = new URL(baseURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Erro HTTP: " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((conn.getInputStream())));
            String output;
            StringBuffer buffer = new StringBuffer();
            while ((output = br.readLine()) != null) {
                buffer.append(output);
            }
            conn.disconnect();
            String stringJsonArray = buffer.toString();
            JSONArray jsonArray = new JSONArray(stringJsonArray);

            List<Ramal> ramais = new ArrayList<>();
            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Ramal ramal = new Ramal();

                ramal.setNumeroRamal(jsonObject.getLong("rua"));
                ramal.setIdMatriculaPessoa(jsonObject.getLong("idMatriculaPessoa"));
                ramais.add(ramal);
            }
            return ramais;

        }

        catch (MalformedURLException e) {
            throw new RuntimeException("Erro de URL " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Erro comunicação " + e.getMessage());
        } catch (JSONException e) {
            throw new RuntimeException("Erro de parser json " + e.getMessage());
        }
    }

    public Ramal get(Long id){
        try {
            URL url = new URL(baseURL + "/" + id);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Erro HTTP: " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((conn.getInputStream())));
            String output;
            StringBuffer buffer = new StringBuffer();
            while ((output = br.readLine()) != null) {
                buffer.append(output);
            }
            conn.disconnect();
            String stringJson = buffer.toString();
            JSONObject jsonObject = new JSONObject(stringJson);

            Ramal ramal = new Ramal();
            ramal.setNumeroRamal(jsonObject.getLong("NumeroRamal"));
            ramal.setIdMatriculaPessoa(jsonObject.getLong("idMatriculaPessoa"));

            return ramal;

        } catch (MalformedURLException e) {
            throw new RuntimeException("Erro de URL " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Erro comunicação " + e.getMessage());
        } catch (JSONException e) {
            throw new RuntimeException("Erro de parser json " + e.getMessage());
        }
    }

    public Ramal create(Ramal ramal){
        try {
            URL url = new URL(baseURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("numeropa", ramal.getNumeroRamal());
            jsonObject.put("idMatriculaPessoa", ramal.getIdMatriculaPessoa());

            String input = jsonObject.toString();
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Erro HTTP: " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((conn.getInputStream())));
            String output;
            StringBuffer buffer = new StringBuffer();
            while ((output = br.readLine()) != null) {
                buffer.append(output);
            }
            conn.disconnect();
            String stringJson = buffer.toString();
            jsonObject = new JSONObject(stringJson);

            ramal = new Ramal();
            ramal.setNumeroRamal(jsonObject.getLong("NumeroRamal"));
            ramal.setIdMatriculaPessoa(jsonObject.getLong("idMatriculaPessoa"));

            return ramal;

        } catch (MalformedURLException e) {
            throw new RuntimeException("Erro de URL " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Erro de comunicação " + e.getMessage());
        } catch (JSONException e) {
            throw new RuntimeException("Erro de parser json " + e.getMessage());
        }
    }

    public void update(Ramal ramal){
        try {
            URL url = new URL(baseURL + "/" + ramal.getNumeroRamal());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("numeropa", ramal.getNumeroRamal());
            jsonObject.put("idMatriculaPessoa", ramal.getIdMatriculaPessoa());

            String input = jsonObject.toString();
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 204) {
                throw new RuntimeException("Erro HTTP: " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((conn.getInputStream())));
            String output;
            StringBuffer buffer = new StringBuffer();
            while ((output = br.readLine()) != null) {
                buffer.append(output);
            }
            conn.disconnect();

        } catch (MalformedURLException e) {
            throw new RuntimeException("Erro de URL " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Erro de comunicação " + e.getMessage());
        } catch (JSONException e) {
            throw new RuntimeException("Erro de parser json " + e.getMessage());
        }
    }

    public void delete(Long id){
        try {
            URL url = new URL(baseURL + "/" + id);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Erro HTTP: " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((conn.getInputStream())));
            String output;
            StringBuffer buffer = new StringBuffer();
            while ((output = br.readLine()) != null) {
                buffer.append(output);
            }
            conn.disconnect();

        } catch (MalformedURLException e) {
            throw new RuntimeException("Erro de URL " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Erro comunicação " + e.getMessage());
        }

    }


}
