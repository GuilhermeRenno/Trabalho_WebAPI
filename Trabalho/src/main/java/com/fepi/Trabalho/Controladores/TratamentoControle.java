package com.fepi.Trabalho.Controladores;

import com.fepi.Trabalho.Modelos.Tratamento;
import com.fepi.Trabalho.Repositorios.TratamentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin(origins = "http://127.0.0.1:5500", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/tratamento")
public class TratamentoControle {

    @Autowired
    private TratamentoRepositorio tratamentoRepositorio;

    @GetMapping

    public ResponseEntity<Iterable<Tratamento>> listar() {
        Iterable<Tratamento> itens = tratamentoRepositorio.findAll();
        return ResponseEntity.ok(itens);
    }
    @CrossOrigin("http://127.0.0.1:5500/PagAddTratamentos.html")
    @PostMapping
    public ResponseEntity<Tratamento> cadastrar(@RequestBody Tratamento trat){
        Tratamento tratamento =tratamentoRepositorio.saveAndFlush(trat);
        return new ResponseEntity<Tratamento>(tratamento, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        tratamentoRepositorio.deleteById(id);
        return new ResponseEntity<>("Removido com sucesso", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> alterar(@RequestBody Tratamento trat) {

        if (trat.getCodigo() == 0) {
            return new ResponseEntity<>("Id não foi informado", HttpStatus.BAD_REQUEST);
        }

        Optional<Tratamento> optionalContato = tratamentoRepositorio.findById(trat.getCodigo());

        if (optionalContato.isEmpty()){
            return new ResponseEntity<>("Id não encontrado", HttpStatus.NOT_FOUND);
        }

        tratamentoRepositorio.saveAndFlush(trat);
        return  new ResponseEntity<>("Alterado com sucesso",HttpStatus.OK);
    }
}
