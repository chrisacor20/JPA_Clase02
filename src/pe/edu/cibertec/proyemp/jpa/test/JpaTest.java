package pe.edu.cibertec.proyemp.jpa.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import pe.edu.cibertec.proyemp.jpa.domain.Departamento;
import pe.edu.cibertec.proyemp.jpa.domain.Empleado;

public class JpaTest {

	private EntityManager manager;

	// Inyeccion de dependencia
	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("persistenceUnit");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			test.crearEmpleados();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		test.mostrarEmpleadoId1();

		System.out.println(".. done");

	}

	private void crearEmpleados() {
		// query a una entidad (JQL),HQL,HSQL
		// ""Select a From Empleado a"" trae una lista
		// getResulList = convierte a una lista ,size = trae la cant. de la
		// lista
		int nroDeEmpleados = manager
				.createQuery("Select a From Empleado a", Empleado.class)
				.getResultList().size();

		if (nroDeEmpleados == 0) {
			Departamento departamento = new Departamento("Java");
			manager.persist(departamento);

			manager.persist(new Empleado("Bob", departamento));
			manager.persist(new Empleado("Mike", departamento));

		}
	}

	private void listarEmpleados() {
		//List<Empleado> resultList = manager.createQuery(
		//		"From Empleado a", Empleado.class).getResultList();
		List<Empleado> resultList = manager.createQuery(
				"Select a From Empleado a", Empleado.class).getResultList();
		System.out.println("nro de empleados:" + resultList.size());
		for (Empleado next : resultList) {
			System.out.println("siguiente empleado: " + next);
		}
	}
	
	private void crearEmpleados2(){
		Departamento departamento = new Departamento("Java");
		
		Empleado emp1 = new Empleado("Bob");
		Empleado emp2 = new Empleado("Mike");
		
		//List<Empleado> empleados = new ArrayList<>();
		
		//empleados.add(emp1);
		//empleados.add(emp2);
		
	//	departamento.setEmpleados(empleados);
		departamento.setEmpleados(Arrays.asList(emp1,emp2));
		
		manager.persist(departamento);
		
	}
	
	/*private void mostrarEmpleadoId1(){
		Empleado emp =  manager.createQuery(
				"Select a From Empleado a where a.id=1", Empleado.class).getSingleResult();
		System.out.println(emp);
	}*/
	private void mostrarEmpleadoId1(){
		Empleado emp =  manager.find(Empleado.class,new Long(1));
		System.out.println(emp);
	}
	
}
