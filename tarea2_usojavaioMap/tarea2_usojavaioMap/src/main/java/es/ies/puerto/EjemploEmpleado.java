package es.ies.puerto;

import java.util.Set;
import java.util.TreeMap;

import es.ies.puerto.model.Empleado;
import es.ies.puerto.model.file.FileOperations;

public class EjemploEmpleado {
    
    public static void main(String[] args) {
        //Instancia de la clase FileOperations;
        FileOperations operacionesFicheros = new FileOperations();

        Empleado empleado = new Empleado("1", "Carlos Díaz", "Desarrollador", 1500.00, "28/01/1987");
        Empleado empleado2 = new Empleado("2", "Marta López", "Gerente", 5500.00, "21/11/1981");
        Empleado empleado3 = new Empleado("3", "Angel Hernández", "Desarrollador", 1500.00, "18/02/1999");
        Empleado empleado4 = new Empleado("4", "Julia Santana", "Diseñador", 1800.50, "03/03/1977");

        //Insertar un empleado nuevo.
        boolean insertar1 = operacionesFicheros.create(empleado);
        if(insertar1){
            System.out.println("Se ha insertado correctamente");
        } else {
            System.out.println("Se ha producido un error");
        }
        boolean insertar2 = operacionesFicheros.create(empleado2);
        if(insertar2){
            System.out.println("Se ha insertado correctamente");
        } else {
            System.out.println("Se ha producido un error");
        }
        boolean insertar3 = operacionesFicheros.create(empleado3);
        if(insertar3){
            System.out.println("Se ha insertado correctamente");
        } else {
            System.out.println("Se ha producido un error");
        }
        boolean insertar4 = operacionesFicheros.create(empleado4);
        if(insertar4){
            System.out.println("Se ha insertado correctamente");
        } else {
            System.out.println("Se ha producido un error");
        }
            


        //Actualizar un empleado.
        Empleado newUpdate = new Empleado("3", "Juan Díaz" , "Gerente", 5500.00, "16/05/1973");
        boolean actualizar = operacionesFicheros.update(newUpdate);
        System.out.println("Se han actualizado: " + actualizar);
        System.out.println("Confirmación: " + operacionesFicheros.read("3"));


        //Borrar un empleado.
        boolean borrar = operacionesFicheros.delete("1");
        Empleado empEliminado = operacionesFicheros.read("1");

        //Listar un empleado.
        Empleado empleadolistar = operacionesFicheros.read("1");
        System.out.println("Información del empleado: " + empleadolistar);
    

        //Listar empleados por puestos de trabajo.
        TreeMap<String, Empleado> listaPorPuesto = operacionesFicheros.empleadosPorPuesto("Desarrollador");
            if (listaPorPuesto.isEmpty()){
                System.out.println("No se encontraron empleados con ese puesto");
            } else {
                for (Empleado e : listaPorPuesto.values()) {
                    System.out.println(e);
                }
            }

        //Listar empleados con edad comprendida entre dos fechas dadas.
        TreeMap<String, Empleado> listaPorFechas = operacionesFicheros.empleadosPorEdad("01/01/1970", "01/01/1988");
            if(listaPorFechas.isEmpty()){
                System.out.println("No se han encontrado personas entre esas fechas.");
            } else {
                for (Empleado emp : listaPorFechas.values()) {
                    System.out.println(emp);
                }
            }
    }
}
