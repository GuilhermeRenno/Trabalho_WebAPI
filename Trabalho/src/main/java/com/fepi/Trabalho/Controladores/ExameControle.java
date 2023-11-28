package com.fepi.Trabalho.Controladores;

import com.fepi.Trabalho.Modelos.Exame;
import com.fepi.Trabalho.Repositorios.ExameRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:5500", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/exame")
public class ExameControle {
    @Autowired
    private ExameRepositorio exameRepositorio;

    @GetMapping

    public ResponseEntity<Iterable<Exame>> listar() {
        Iterable<Exame> itens = exameRepositorio.findAll();
        return ResponseEntity.ok(itens);
    }

    @PostMapping
    public ResponseEntity<Exame> cadastrar(@RequestBody Exame exm){
        Exame exame =exameRepositorio.saveAndFlush(exm);
        return new ResponseEntity<Exame>(exame,HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        exameRepositorio.deleteById(id);
        return new ResponseEntity<>("Removido com sucesso", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> alterar(@RequestBody Exame exm) {

        if (exm.getCodigo() == 0) {
            return new ResponseEntity<>("Id não foi informado", HttpStatus.BAD_REQUEST);
        }

        Optional<Exame> optionalContato = exameRepositorio.findById(exm.getCodigo());

        if (optionalContato.isEmpty()){
            return new ResponseEntity<>("Id não encontrado", HttpStatus.NOT_FOUND);
        }

        exameRepositorio.saveAndFlush(exm);
        return  new ResponseEntity<>("Alterado com sucesso",HttpStatus.OK);
    }
}
