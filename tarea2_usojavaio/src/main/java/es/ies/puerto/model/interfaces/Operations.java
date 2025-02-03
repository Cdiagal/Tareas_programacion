package es.ies.puerto.model.interfaces;
import java.util.Set;
import java.util.HashMap;
import es.ies.puerto.model.Empleado;

/**
 * La clase Operations declara todos los métodos necesarios para que funcione un CRUD.
 * Los métodos devolverán un valor para confirmar si la operación se realizó de forma exitosa o no.
 * 
 * Métodos incluidos en la clase:
 * boolean create(Empleado empleado) -- Crear un nuevo objeto y almacenarlo
 * Empelado read(String identificador) -- Leer un empleado a partir de su identificador.
 * Empleado read(Empleado empleado) -- Leer un empleado a partir de un objeto de tipo empleado.
 * boolean update(Empleado empleado) -- Actualizarr un objeto existente.
 * boolean delete(String identificador) -- Eliminar un objeto a partir de su identificador.
 * Set<Empleado> empleadosPorPuesto (String puesto) -- Debe devolver el listado de empleados de un puesto concreto.
 * o Set<Empleado> empleadosPorEdad(String fechaInicio, String fechaFin) -- Debe devolver el listado de empleados entre dos fechas.
 */
public interface Operations {

    public boolean create (Empleado empleado);
    public Empleado read (String identificador);
    public Empleado read(Empleado empleado);
    boolean update(Empleado empleado);
    boolean delete(String identificador);
    Set<Empleado> empleadosPorPuesto (String puesto);
    Set<Empleado> empleadosPorEdad (String fechaInicio, String fechaFin);

}
