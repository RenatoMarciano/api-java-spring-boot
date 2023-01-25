package com.attornatus.api.apijavagerenciarpessoas.service;

import com.attornatus.api.apijavagerenciarpessoas.model.Pessoas;

public interface PessoasService {

    Iterable<Pessoas> buscarTodos();
    Pessoas buscarPorId(Long id);
    void inserir(Pessoas pessoas);
    void atualizar(Long id, Pessoas pessoas);
    void deletar(Long id);
}
