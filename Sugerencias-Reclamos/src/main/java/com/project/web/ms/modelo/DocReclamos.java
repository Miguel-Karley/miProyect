package com.project.web.ms.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "docsugerenciareclamo")

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DocReclamos {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idsugerenciareclamo;
	
	@NotNull(message = "el empleado no puede estar vacio")
	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name = "idempleado")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Empleado empleado;
	
	@NotNull(message = "el cliente no puede estar vacio")
	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name = "idcliente")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Cliente cliente;
	
	@NotNull(message = "el proveedor no puede estar vacio")
	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name = "idproveedor")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Proveedor proveedor;
	
	@Column(name = "fechareclamo")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechareclamo;
	private  String reclamo;
	private String estado;
	
}
