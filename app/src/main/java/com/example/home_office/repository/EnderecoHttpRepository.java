package com.example.home_office.repository;

import com.example.home_office.domain.Endereco;

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

public class EnderecoHttpRepository {

    private static final String baseURL = "https://";

    public List<Endereco> findAll() {

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

            List<Endereco> enderecos = new ArrayList<>();
            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Endereco endereco = new Endereco();

                endereco.setRua(jsonObject.getString("rua"));
                endereco.setBairro(jsonObject.getString("bairro"));
                endereco.setComplemento(jsonObject.getString("complemento"));
                endereco.setNumero(jsonObject.getLong("numero"));
                endereco.setIdMatriculaPessoa(jsonObject.getLong("idMatriculaPessoa"));
                enderecos.add(endereco);
            }
            return enderecos;

        }

        catch (MalformedURLException e) {
            throw new RuntimeException("Erro de URL " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Erro comunicação " + e.getMessage());
        } catch (JSONException e) {
            throw new RuntimeException("Erro de parser json " + e.getMessage());
        }
    }

    public Endereco get(Long id){
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

            Endereco endereco = new Endereco();
            endereco.setRua(jsonObject.getString("rua"));
            endereco.setBairro(jsonObject.getString("bairro"));
            endereco.setComplemento(jsonObject.getString("complemento"));
            endereco.setNumero(jsonObject.getLong("numero"));
            endereco.setIdMatriculaPessoa(jsonObject.getLong("idMatriculaPessoa"));

            return endereco;

        } catch (MalformedURLException e) {
            throw new RuntimeException("Erro de URL " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Erro comunicação " + e.getMessage());
        } catch (JSONException e) {
            throw new RuntimeException("Erro de parser json " + e.getMessage());
        }
    }

    public Endereco create(Endereco endereco){
        try {
            URL url = new URL(baseURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("rua", endereco.getRua());
            jsonObject.put("bairro", endereco.getBairro());
            jsonObject.put("complemento", endereco.getComplemento());
            jsonObject.put("numero", endereco.getNumero());
            jsonObject.put("idMatriculaPessoa", endereco.getIdMatriculaPessoa());


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

            endereco = new Endereco();
            endereco.setRua(jsonObject.getString("Rua"));
            endereco.setNumero(jsonObject.getLong("Numero"));
            endereco.setComplemento(jsonObject.getString("complemento"));
            endereco.setBairro(jsonObject.getString("Bairro"));
            endereco.setIdMatriculaPessoa(jsonObject.getLong("idMatriculaPessoa"));


            return endereco;

        } catch (MalformedURLException e) {
            throw new RuntimeException("Erro de URL " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Erro de comunicação " + e.getMessage());
        } catch (JSONException e) {
            throw new RuntimeException("Erro de parser json " + e.getMessage());
        }
    }

    public void update(Endereco endereco){
        try {
            URL url = new URL(baseURL + "/" + endereco.getRua());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("rua", endereco.getRua());
            jsonObject.put("numero", endereco.getNumero());
            jsonObject.put("bairro", endereco.getBairro());
            jsonObject.put("complemento", endereco.getComplemento());
            jsonObject.put("idenderecoMatricula", endereco.getIdMatriculaPessoa());



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
