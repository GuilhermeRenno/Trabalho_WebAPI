package com.fepi.Trabalho.Controladores;

import com.fepi.Trabalho.Modelos.Paciente;
import com.fepi.Trabalho.Repositorios.ExameRepositorio;
import com.fepi.Trabalho.Repositorios.PacienteRepositorio;
import com.fepi.Trabalho.Repositorios.TratamentoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@CrossOrigin(origins = "http://127.0.0.1:5500", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/paciente")
public class PacienteControle {

    @Autowired
    private PacienteRepositorio pacienteRepositorio;
    @Autowired
    private TratamentoRepositorio tratamentoRepositorio;
    @Autowired
    private ExameRepositorio exameRepositorio;
    @CrossOrigin("http://127.0.0.1:5500/PagMedico.html")
    @GetMapping

    public ResponseEntity<Iterable<Paciente>> listar() {
        Iterable<Paciente> itens = pacienteRepositorio.findAll();
        return ResponseEntity.ok(itens);
    }
    @CrossOrigin("http://127.0.0.1:5500/PadAddPacientes.html")
    @PostMapping
    public ResponseEntity<Paciente> cadastrar(@RequestBody Paciente paci){
        Paciente paciente =pacienteRepositorio.saveAndFlush(paci);
        return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
    }
    @CrossOrigin("http://127.0.0.1:5500/PagMedico.html")
    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id){
        // Primeiro, obtenha o Paciente pelo id
        Optional<Paciente> optionalPaciente = pacienteRepositorio.findById(id);
        if (optionalPaciente.isPresent()) {
            Paciente paciente = optionalPaciente.get();

            // Excluindo todos os Tratamentos associados ao Paciente
            tratamentoRepositorio.deleteByPaciente(paciente);

            // Excluindo todos os Exames associados ao Paciente
            exameRepositorio.deleteByPaciente(paciente);

            // Excluindo o Paciente
            pacienteRepositorio.deleteById(id);
            return new ResponseEntity<>("Removido com sucesso", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Paciente não encontrado", HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin("http://127.0.0.1:5500/PadAddPacientes.html")
    @PutMapping("/{id}")
    public ResponseEntity<Object> alterar(@PathVariable Long id, @RequestBody Paciente paci) {

        if (paci.getCodigo() == null || paci.getCodigo() == 0) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Id não foi informado");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        Optional<Paciente> optionalPaciente = pacienteRepositorio.findById(paci.getCodigo());

        if (optionalPaciente.isEmpty()){
            Map<String, String> error = new HashMap<>();
            error.put("error", "Id não encontrado");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        pacienteRepositorio.saveAndFlush(paci);
        Map<String, String> success = new HashMap<>();
        success.put("message", "Alterado com sucesso");
        return new ResponseEntity<>(success, HttpStatus.OK);
    }
}

