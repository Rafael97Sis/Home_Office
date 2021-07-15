package com.example.home_office.repository;

import com.example.home_office.domain.Pessoa;

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

public class PessoaHttpRepository {

    private static final String baseURL = "https://";

    public List<Pessoa> findAll() {

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

            List<Pessoa> pessoas = new ArrayList<>();
            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Pessoa pessoa = new Pessoa();

                pessoa.setMatricula(jsonObject.getLong("rua"));
                pessoa.setNome(jsonObject.getString("bairro"));

                pessoas.add(pessoa);
            }
            return pessoas;

        }

        catch (MalformedURLException e) {
            throw new RuntimeException("Erro de URL " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Erro comunicação " + e.getMessage());
        } catch (JSONException e) {
            throw new RuntimeException("Erro de parser json " + e.getMessage());
        }
    }

    public Pessoa get(Long id){
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

            Pessoa pessoa = new Pessoa();
            pessoa.setMatricula(jsonObject.getLong("matricula"));
            pessoa.setNome(jsonObject.getString("nome"));

            return pessoa;

        } catch (MalformedURLException e) {
            throw new RuntimeException("Erro de URL " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Erro comunicação " + e.getMessage());
        } catch (JSONException e) {
            throw new RuntimeException("Erro de parser json " + e.getMessage());
        }
    }

    public Pessoa create(Pessoa pessoa){
        try {
            URL url = new URL(baseURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("matricula", pessoa.getMatricula());
            jsonObject.put("nome", pessoa.getNome());


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

            pessoa = new Pessoa();
            pessoa.setMatricula(jsonObject.getLong("matricula"));
            pessoa.setNome(jsonObject.getString("nome"));


            return pessoa;

        } catch (MalformedURLException e) {
            throw new RuntimeException("Erro de URL " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Erro de comunicação " + e.getMessage());
        } catch (JSONException e) {
            throw new RuntimeException("Erro de parser json " + e.getMessage());
        }
    }

    public void update(Pessoa pessoa){
        try {
            URL url = new URL(baseURL + "/" + pessoa.getMatricula());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("matricula", pessoa.getMatricula());
            jsonObject.put("Nome", pessoa.getNome());




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
