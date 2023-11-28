package com.fepi.Trabalho.Repositorios;

import com.fepi.Trabalho.Modelos.Paciente;
import com.fepi.Trabalho.Modelos.Tratamento;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TratamentoRepositorio extends JpaRepository<Tratamento,Long> {


    void deleteByPaciente(Paciente paciente);
}
