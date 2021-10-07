package com.project.web.ms.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "cliente")

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Cliente {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idcliente;
	private String tipocliente; 
	private String nombre;
	private String tipo_documento ;
	private String num_documento ;
	private String direccion ;
	private String telefono ;
	private String email ;

}
