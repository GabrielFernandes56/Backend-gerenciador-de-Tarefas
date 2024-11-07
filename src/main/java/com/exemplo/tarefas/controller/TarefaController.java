package com.exemplo.tarefas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.tarefas.model.Tarefa;
import com.exemplo.tarefas.repository.TarefaRepository;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/tarefas")
public class TarefaController { 
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	
	public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}
	
	@GetMapping
	public List<Tarefa> listarTarefas(){
		return tarefaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Tarefa busccarTarefa(@PathVariable Long id) {
		Optional<Tarefa> tarefa = tarefaRepository.findById(id);
		if(tarefa.isPresent()) {
			return tarefa.get();
		}
		throw new RuntimeException("Tarefa não encontrada");
	}
	@PutMapping("/{id}")
	 public Tarefa atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        Optional<Tarefa> tarefaExistente = tarefaRepository.findById(id);
        if (tarefaExistente.isPresent()) {
            tarefa.setId(id);
            return tarefaRepository.save(tarefa);
        }
        throw new RuntimeException("Tarefa não encontrada");
    }
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void  deletarTarefa(@PathVariable Long id) {
		tarefaRepository.deleteById(id);
	}

}
