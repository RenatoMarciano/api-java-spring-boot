package com.attornatus.api.apijavagerenciarpessoas.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoasRepository extends CrudRepository<Pessoas, Long> {

}
