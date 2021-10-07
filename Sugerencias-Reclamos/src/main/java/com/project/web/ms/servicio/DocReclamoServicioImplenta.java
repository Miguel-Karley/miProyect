package com.project.web.ms.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.web.ms.modelo.Cliente;
import com.project.web.ms.modelo.DocReclamos;
import com.project.web.ms.modelo.Empleado;
import com.project.web.ms.modelo.Proveedor;
import com.project.web.ms.repositorio.DocReclamoRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocReclamoServicioImplenta implements DocReclamoServicio{
	
	public final DocReclamoRepositorio docReclamosrepositorio;
	
	@Override
	public List<DocReclamos> ListAllDocReclamo() {
		return docReclamosrepositorio.findAll();
	}

	@Override
	public DocReclamos getDocReclamo(Long id) {
		return docReclamosrepositorio.findById(id).orElse(null);
	}

	@Override
	public DocReclamos createDocReclamo(DocReclamos docReclamo) {
		docReclamo.setEstado("EN PROCESO");
		docReclamo.setFechareclamo(new Date());
		
		return docReclamosrepositorio.save(docReclamo);
	}

	@Override
	public DocReclamos updateDocReclamo(DocReclamos docReclamo) {
		DocReclamos docReclamosUpdate = getDocReclamo(docReclamo.getIdsugerenciareclamo());
		
		if (docReclamosUpdate ==  null) {
			return null;
		}
		
		docReclamosUpdate.setEmpleado(docReclamo.getEmpleado());
		docReclamosUpdate.setCliente(docReclamo.getCliente());
		docReclamosUpdate.setProveedor(docReclamo.getProveedor());
		docReclamosUpdate.setReclamo(docReclamo.getReclamo());
		
		return docReclamosrepositorio.save(docReclamosUpdate);
	}

	@Override
	public DocReclamos deleteDocReclamo(Long id) {
		DocReclamos docReclamoDelete = getDocReclamo(id);
		
		if (docReclamoDelete == null) {
			return null;
		}
		docReclamoDelete.setEstado("ELIMINADO");
		
		return docReclamosrepositorio.save(docReclamoDelete);
	}

	@Override
	public List<DocReclamos> findByCliente(Cliente cliente) {
		return docReclamosrepositorio.findByCliente(cliente);
	}

	@Override
	public List<DocReclamos> findByEmpleado(Empleado empleado) {
		return docReclamosrepositorio.findByEmpleado(empleado);
	}

	@Override
	public List<DocReclamos> findByProveedor(Proveedor proveedor) {
		return docReclamosrepositorio.findByProveedor(proveedor);
	}

	
}
