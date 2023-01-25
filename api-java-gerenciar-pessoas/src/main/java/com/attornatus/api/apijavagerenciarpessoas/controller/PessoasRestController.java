package com.attornatus.api.apijavagerenciarpessoas.controller;

import com.attornatus.api.apijavagerenciarpessoas.model.Endereco;
import com.attornatus.api.apijavagerenciarpessoas.model.Pessoas;
import com.attornatus.api.apijavagerenciarpessoas.service.PessoasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pessoa")
public class PessoasRestController {

    @Autowired
    private PessoasService pessoasService;

    @GetMapping
    public ResponseEntity<Iterable<Pessoas>> buscarTodos() {
        return ResponseEntity.ok(pessoasService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoas> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pessoasService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Pessoas> inserir(@RequestBody Pessoas pessoas) {
        pessoasService.inserir(pessoas);
        return ResponseEntity.ok(pessoas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoas> atualizar(@PathVariable Long id, @RequestBody Pessoas pessoas) {
        pessoasService.atualizar(id, pessoas);
        return ResponseEntity.ok(pessoas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pessoasService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
