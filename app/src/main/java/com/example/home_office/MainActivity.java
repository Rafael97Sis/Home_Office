package com.example.home_office;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.home_office.domain.Endereco;
import com.example.home_office.domain.Maquina;
import com.example.home_office.domain.Pessoa;
import com.example.home_office.domain.Ramal;
import com.example.home_office.repository.EnderecoHttpRepository;
import com.example.home_office.repository.MaquinaHttpRepository;
import com.example.home_office.repository.PessoaHttpRepository;
import com.example.home_office.repository.RamalHttpRepository;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private EnderecoHttpRepository enderecoHttpRepository = new EnderecoHttpRepository();
    private MaquinaHttpRepository maquinaHttpRepository = new MaquinaHttpRepository();
    private PessoaHttpRepository pessoaHttpRepository = new PessoaHttpRepository();
    private RamalHttpRepository ramalHttpRepository = new RamalHttpRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissions();
        initComponents();
        acaoListarDados();
    }

    private void acaoListarDados() {
        try {
            List<Pessoa> pessoas = pessoaHttpRepository.findAll();
            ArrayAdapter<Pessoa> adapter = new ArrayAdapter<>(
                    getApplicationContext(), android.R.layout.simple_list_item_1);
            adapter.clear();
            final ListView lvBandas = findViewById(R.id.LvDadosUsuarios);
            lvBandas.setAdapter(adapter);
            adapter.addAll(pessoas);
        }catch (Exception ex){
            Toast.makeText(MainActivity.this, "Erro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void initComponents() {
        Button btnBusca = findViewById(R.id.btnBusca);
        btnBusca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acaoBuscarDados();
            }
        });
        Button btnSalva = findViewById(R.id.btnSalva);
        btnSalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acaoSalvarDados();
            }
        });
        Button btnRemove = findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acaoRemoverDados();
            }
        });
        Button btnLimpa = findViewById(R.id.btnLimpa);
        btnLimpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acaoLimpaCampos();
            }
        });
    }

    private void acaoLimpaCampos() {
        EditText editMatricula = findViewById(R.id.editMatricula);
        EditText editNome = findViewById(R.id.editNome);
        EditText editEndereco = findViewById(R.id.editRua);
        EditText editNumero = findViewById(R.id.editNumero);
        EditText editBairro = findViewById(R.id.editBairro);
        EditText editComplemento = findViewById(R.id.editComplemento);
        EditText editRamal = findViewById(R.id.editRamal);
        EditText editPaNumero = findViewById(R.id.editPaNumero);
        EditText editModelo = findViewById(R.id.editModelo);
        editMatricula.setText("");
        editNome.setText("");
        editEndereco.setText("");
        editNumero.setText("");
        editBairro.setText("");
        editComplemento.setText("");
        editRamal.setText("");
        editPaNumero.setText("");
        editModelo.setText("");
    }

    private void acaoBuscarDados() {
        EditText editMatricula = findViewById(R.id.editMatricula);
        EditText editNome = findViewById(R.id.editNome);
        EditText editRua = findViewById(R.id.editRua);
        EditText editNumero = findViewById(R.id.editNumero);
        EditText editBairro = findViewById(R.id.editBairro);
        EditText editComplemento = findViewById(R.id.editComplemento);
        EditText editRamal = findViewById(R.id.editRamal);
        EditText editPaNumero = findViewById(R.id.editPaNumero);
        EditText editModelo = findViewById(R.id.editModelo);
        try {
            Long id = Long.parseLong(editMatricula.getText().toString());
            Endereco endereco = enderecoHttpRepository.get(id);
            Pessoa pessoa = pessoaHttpRepository.get(id);
            Maquina maquina = maquinaHttpRepository.get(id);
            Ramal ramal = ramalHttpRepository.get(id);

            editMatricula.setText(id.toString());
            editNome.setText(pessoa.getNome());
            editRua.setText(endereco.getRua());
            editNumero.setText(endereco.getNumero().toString());
            editBairro.setText(endereco.getBairro());
            editComplemento.setText(endereco.getComplemento());
            editRamal.setText(ramal.getNumeroRamal().toString());
            editPaNumero.setText(maquina.getNumeroPa().toString());
            editModelo.setText(maquina.getModelo().toString());


        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, "Erro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
            acaoLimpaCampos();
        }
    }

    private void acaoRemoverDados() {
        EditText editId = findViewById(R.id.editMatricula);

        try {
            Long id = Long.parseLong(editId.getText().toString());
            pessoaHttpRepository.delete(id);
            enderecoHttpRepository.delete(id);
            ramalHttpRepository.delete(id);
            maquinaHttpRepository.delete(id);
            acaoListarDados();
            Toast.makeText(MainActivity.this, "OK: removido endereco " + id, Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, "Erro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        acaoLimpaCampos();
    }

    private void acaoSalvarDados() {
        EditText editMatricula = findViewById(R.id.editMatricula);
        EditText editNome = findViewById(R.id.editNome);
        EditText editRua = findViewById(R.id.editRua);
        EditText editNumero = findViewById(R.id.editNumero);
        EditText editBairro = findViewById(R.id.editBairro);
        EditText editComplemento = findViewById(R.id.editComplemento);
        EditText editRamal = findViewById(R.id.editRamal);
        EditText editPaNumero = findViewById(R.id.editPaNumero);
        EditText editModelo = findViewById(R.id.editModelo);
        Long id = null;
        try {
            id = Long.parseLong(editMatricula.getText().toString());
        }catch (Exception ex2){
        }
        try {
            String Rua = editRua.getText().toString();
            String Numero = editNumero.getText().toString();
            String Bairro = editBairro.getText().toString();
            String Complemento = editComplemento.getText().toString();
            String Matricula = editMatricula.getText().toString();
            String Nome = editNome.getText().toString();
            String Ramal = editRamal.getText().toString();
            String PaNumero = editPaNumero.getText().toString();
            String Modelo = editModelo.getText().toString();

            Endereco endereco = new Endereco();
            Maquina maquina = new Maquina();
            Ramal ramal = new Ramal( );
            Pessoa pessoa = new Pessoa();

            if(id == null) {
                endereco = enderecoHttpRepository.create(endereco);
                maquina = maquinaHttpRepository.create(maquina);
                ramal = ramalHttpRepository.create(ramal);
                pessoa = pessoaHttpRepository.create(pessoa);

                editRua.setText(endereco.getRua().toString());
                editNumero.setText(endereco.getNumero().toString());
                editBairro.setText(endereco.getNumero().toString());
                editComplemento.setText(endereco.getNumero().toString());
                editMatricula.setText(pessoa.getMatricula().toString());
                editNome.setText(pessoa.getMatricula().toString());
                editRamal.setText(ramal.getNumeroRamal().toString());
                editPaNumero.setText(maquina.getNumeroPa().toString());
                editModelo.setText(maquina.getModelo().toString());


            } else {

                enderecoHttpRepository.update(endereco);
                maquinaHttpRepository.update(maquina);
                ramalHttpRepository.update(ramal);
                pessoaHttpRepository.update(pessoa);
            }
            acaoListarDados();
            Toast.makeText(MainActivity.this, "OK: salvo  " + pessoa.getNome() , Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, "Erro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void permissions() {
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


}