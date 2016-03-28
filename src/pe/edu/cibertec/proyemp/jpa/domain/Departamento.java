package pe.edu.cibertec.proyemp.jpa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Departamento {
	
	@Id //Primary key
	@GeneratedValue //(strategy = GenerationType.AUTO) -Autoincrement
	private Long id;
	
	private String nombre;
	
	//Lista de Empleados
	@OneToMany(mappedBy ="departamento", cascade= CascadeType.PERSIST)
	private List<Empleado> empleados = new ArrayList<Empleado>();
	
	//JPA , necesita un constructor vacio ....
	public Departamento(){}
	
	public Departamento(String nombre) {
		this.nombre = nombre;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	@OneToMany
	(mappedBy="departamento",cascade=CascadeType.PERSIST)

	
	public List<Empleado> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	
	@Override
	public String toString() {
		return "Departamento [id=" + id + ", nombre=" + nombre + ", empleados="
				+ empleados + "]";
	}
	
	
	
}
