package com.project.web.ms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.web.ms.modelo.Cliente;
import com.project.web.ms.modelo.DocReclamos;
import com.project.web.ms.servicio.DocReclamoServicio;

import net.bytebuddy.asm.Advice.This;

@RestController
@RequestMapping(value = "/docsugerenciareclamo")
public class DocReclamoController {
	
	@Autowired
	DocReclamoServicio reclamoservicio;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<DocReclamos>> ListarDocReclamo(@RequestParam(name = "clienteId", 
	required = false) Long clienteId){
		
		List<DocReclamos> docReclamo = new ArrayList<>();
		
		if (clienteId == null) {
			docReclamo = reclamoservicio.ListAllDocReclamo();
			if (docReclamo.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		}else {
			docReclamo = reclamoservicio.findByCliente(Cliente.builder()
					.idcliente(clienteId).build());
			if (docReclamo.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		}
		
		return ResponseEntity.ok(docReclamo);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<DocReclamos> getDocReclamo(@PathVariable("id") Long id){
		
		DocReclamos docReclamo = reclamoservicio.getDocReclamo(id);
		if (docReclamo == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(docReclamo);
	}
	
	//@RequestMapping(value = "/",method = RequestMethod.POST)
	@PostMapping
	public ResponseEntity<DocReclamos> CrearDocReclamo(@Valid @RequestBody DocReclamos docReclamos, BindingResult result){
		if(result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result)); 
			
		}
		DocReclamos docReclamoCreado = reclamoservicio.createDocReclamo(docReclamos);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(docReclamoCreado);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<DocReclamos> actualizarDocReclamo(@PathVariable("id") Long id,
			@RequestBody DocReclamos docReclamo){
		
		docReclamo.setIdsugerenciareclamo(id);
		DocReclamos docReclamosEncontrados = reclamoservicio.updateDocReclamo(docReclamo);
		
		if (docReclamosEncontrados == null) {
			return ResponseEntity.notFound().build();
			
		}
		return ResponseEntity.ok(docReclamosEncontrados);			
		
	}
	
	@DeleteMapping(value = "/eliminar/{id}")
	public ResponseEntity<DocReclamos> deleteDocReclamo(@PathVariable("id") Long id){
		DocReclamos docReclamoDelete = reclamoservicio.deleteDocReclamo(id);
		
		if(docReclamoDelete == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(docReclamoDelete);
	}
	
	private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
	

}
