package es.ies.puerto.model;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Clase Empleado donde se implementan todos los atributos y funciones básicas para manejar los datos de empleado en diferentes operaciones de un CRUD.
 * La clase dispone de un método donde se calcula un 'int' con la edad en años del empleado. Método: public int getEdad().
 * 
 * @author cdiagal
 * @version 1.0.0
 */

public class Empleado {
    private String identificador;
    private String nombre;
    private String puesto;
    private double salario;
    private String fechaNacimiento;


    /**
     * Se crea el constructor vacío de la clase.
     */

    public Empleado(){

    }

    /**
     * Se crea el constructor con el atributo identificador de la clase.
     * @param identificador del empleado.
     */

    public Empleado(String identificador){
        this.identificador = identificador;
    }

    /**
     * Se crea el constructor con todos los atributos de la clase Empleado.
     * @param identificador del empleado.
     * @param nombre del empleado.
     * @param puesto que desempeña el empleado. 
     * @param salario que percibe el empleado.
     * @param fechaNacimiento en la que nació el empleado.
     */

    public Empleado(String identificador, String nombre, String puesto, double salario, String fechaNacimiento){
        this.identificador = identificador;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        this.fechaNacimiento = fechaNacimiento;
    }


    // Getters y setters.

    public String getIdentificador() {
        return this.identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return this.puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSalario() {
        return this.salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    /**
     * ToString().
     */
    @Override
    public String toString() {
        return
            identificador + ", " +
             nombre + ", " +
             puesto + ", " +
             salario + ", " +
             fechaNacimiento + ", ";
    }
    
    
    // equals() y hashcode().
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Empleado)) {
            return false;
        }
        Empleado empleado = (Empleado) o;
        return Objects.equals(identificador, empleado.identificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }

    /**
     * Método que calcula la edad en años de un empleado entre su fecha de nacimiento y la fechaActual.
     * @return número de años que tiene un empleado en formato "dd\mm\yyyy".
     */
    public int getEdad(){
       DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd'//'MM'//'yyyy");
       LocalDate nacimiento = LocalDate.parse(fechaNacimiento, formato);
       LocalDate fechaActual = LocalDate.now();
       
       return Period.between(nacimiento, fechaActual).getYears();
    }


}

