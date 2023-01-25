package com.attornatus.api.apijavagerenciarpessoas.service.impl;

import com.attornatus.api.apijavagerenciarpessoas.model.Endereco;
import com.attornatus.api.apijavagerenciarpessoas.model.EnderecoRepository;
import com.attornatus.api.apijavagerenciarpessoas.model.Pessoas;
import com.attornatus.api.apijavagerenciarpessoas.model.PessoasRepository;
import com.attornatus.api.apijavagerenciarpessoas.service.PessoasService;
import com.attornatus.api.apijavagerenciarpessoas.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoasServiceImpl implements PessoasService {

    @Autowired
    private PessoasRepository pessoasRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    // Strategy: Implementar os métodos definidos na interface.
    // Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

    @Override
    public Iterable<Pessoas> buscarTodos() {
        // Buscar todos os Pessoas.
        return pessoasRepository.findAll();
    }

    @Override
    public Pessoas buscarPorId(Long id) {
        // Buscar Pessoa por ID.
        Optional<Pessoas> pessoas = pessoasRepository.findById(id);
        return pessoas.get();
    }

    @Override
    public void inserir(Pessoas pessoas) {
        salvarPessoasComCep(pessoas);
    }

    @Override
    public void atualizar(Long id, Pessoas pessoas) {
        // Buscar Pessoa por ID, caso exista:
        Optional<Pessoas> pessoasBd = pessoasRepository.findById(id);
        if (pessoasBd.isPresent()) {
            salvarPessoasComCep(pessoas);
        }
    }

    @Override
    public void deletar(Long id) {
        // Deletar Pessoa por ID.
        pessoasRepository.deleteById(id);
    }

    private void salvarPessoasComCep(Pessoas pessoas) {
        // Verificar se o Endereco da Pessoa já existe (pelo CEP).
        String cep = pessoas.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            // Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        pessoas.setEndereco(endereco);
        // Inserir Pessoa, vinculando o Endereco (novo ou existente).
        pessoasRepository.save(pessoas);
    }
}
