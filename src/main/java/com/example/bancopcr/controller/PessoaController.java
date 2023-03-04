package com.example.bancopcr.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.bancopcr.dao.PessoaDAO;
import com.example.bancopcr.entity.Pessoa;
import com.example.bancopcr.service.SmsService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    private PessoaDAO pessoaDAO;
    private SmsService smsService;


    @Autowired
    public PessoaController(PessoaDAO pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
        this.smsService = smsService;
    }

    @PostMapping
    public ResponseEntity<Pessoa> adicionarPessoa(@RequestBody Pessoa pessoa) throws Exception {
        pessoaDAO.save(pessoa);
        String mensagem = "Olá, " + pessoa.getNome() + "! Você foi cadastrado na fila.";
        smsService.enviarSms(pessoa.getTelefone(), mensagem);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
    }

    @GetMapping
    public List<Pessoa> listarPessoas() {
        return pessoaDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable Long id) {
        Pessoa pessoa = pessoaDAO.findById(id);
        if (pessoa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoaAtualizada) {
        Pessoa pessoaExistente = pessoaDAO.findById(id);
        if (pessoaExistente == null) {
            return ResponseEntity.notFound().build();
        }
        pessoaExistente.setNome(pessoaAtualizada.getNome());
        pessoaExistente.setIdade(pessoaAtualizada.getIdade());
        pessoaExistente.setPosicaoFila(pessoaAtualizada.getPosicaoFila());
        pessoaDAO.update(pessoaExistente);
        return ResponseEntity.ok(pessoaExistente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPessoa(@PathVariable Long id) {
        Pessoa pessoaExistente = pessoaDAO.findById(id);
        if (pessoaExistente == null) {
            return ResponseEntity.notFound().build();
        }
        pessoaDAO.delete(pessoaExistente);
        return ResponseEntity.noContent().build();
    }
}
