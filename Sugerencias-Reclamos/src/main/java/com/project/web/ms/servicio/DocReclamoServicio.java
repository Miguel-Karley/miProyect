package com.project.web.ms.servicio;

import java.util.List;

import com.project.web.ms.modelo.Cliente;
import com.project.web.ms.modelo.DocReclamos;
import com.project.web.ms.modelo.Empleado;
import com.project.web.ms.modelo.Proveedor;

public interface DocReclamoServicio {

	public List<DocReclamos> ListAllDocReclamo();
	public DocReclamos getDocReclamo(Long id);
	
	public DocReclamos createDocReclamo(DocReclamos docReclamo);
	public DocReclamos updateDocReclamo(DocReclamos docReclamo);
	public DocReclamos deleteDocReclamo(Long id);
	
	public List<DocReclamos> findByCliente(Cliente cliente);
	public List<DocReclamos> findByEmpleado(Empleado empleado);
	public List<DocReclamos> findByProveedor(Proveedor proveedor);
	
}
