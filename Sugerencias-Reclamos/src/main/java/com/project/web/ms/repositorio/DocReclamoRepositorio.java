package com.project.web.ms.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.web.ms.modelo.Cliente;
import com.project.web.ms.modelo.DocReclamos;
import com.project.web.ms.modelo.Empleado;
import com.project.web.ms.modelo.Proveedor;

@Repository
public interface DocReclamoRepositorio extends JpaRepository<DocReclamos, Long>{

	public  List<DocReclamos> findByCliente(Cliente cliente);
	public  List<DocReclamos> findByProveedor(Proveedor proveedor);
	public  List<DocReclamos> findByEmpleado(Empleado empleado);
}
