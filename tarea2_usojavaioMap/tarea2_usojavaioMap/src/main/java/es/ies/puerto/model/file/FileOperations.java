package es.ies.puerto.model.file;
import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import es.ies.puerto.model.Empleado;
import es.ies.puerto.model.interfaces.Operations;



/**
 * La subclase 'FileOperations' implementará la interfaz de la clase 'Operations' para gestionar los empleados almacenados en un fichero.
 */
public class FileOperations implements Operations {

    /**
     * Se declara un nuevo fichero con su directorio.
     */
    private File fichero;
    private String path = "\\c:\\Users\\cdiag\\Desktop\\tarea2_usojavaio\\src\\main\\resources\\archivo.txt"; //El directorio es de Windows, por eso está al revés y con doble barra.

    /**
     * Se genera un constructor para inicializar 'fichero' y se aplica programación defensiva.
     */
    public FileOperations(){
        fichero = new File(path);
        if (!fichero.exists() || !fichero.isFile()){
            throw new IllegalArgumentException("El recurso: " + path + " no es un tipo de fichero o no existe." );
        }
    }

    /**
     * Método que implementa la operación de crear un nuevo empleado.
     * @param empleado como objeto que se desea crear y almacenar.
     * @return si el empleado se crea correctamente, true. En caso contrario, false.
     */
    @Override
    public  boolean create(Empleado empleado){
        if(empleado == null || empleado.getIdentificador() == null || empleado.toString().isEmpty()){
            return false;
        }
        Set<Empleado> empleadosExistentes = read(fichero);
        if(empleadosExistentes.contains(empleado)){
            return false;
        }
        return create(empleado.toString(),fichero);
    }
    /**
     * Método que realiza la escritura de datos en el fichero "fichero". 
     * @param data Cadena de texto que contiene toda la información del empleado que se quiere crear o añadir.
     * @param fichero es donde se almacenará la información creada sobre el empleado.
     * @return si se realiza la escritura correctamente, true. En caso contrario, false.
     */
    public boolean create(String data, File fichero){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichero, true))){
            writer.write(data);
            writer.newLine();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Método auxiliar que divide la línea de datos.
     * @param linea del fichero con los datos del empleado.
     * @return objeto de Empelado construido a partir de la lína.
     */
    private Empleado dividirLíneaDatos (String linea){
        if(linea == null || linea.isEmpty()){
            return null;
        }
        String[] partes = linea.split(",");
            if(partes.length<5){
                return null;
            }
        try { //Se divide la línea en partes que componen un Array y se aplica el método trim() para eliminar los espacios en blanco.
            String identificador = partes[0].trim();
            String nombre = partes[1].trim();
            String puesto = partes[2].trim();
            double sueldo = Double.parseDouble(partes[3].trim());
            String fechaNacimiento = partes[4].trim();
            return new Empleado(identificador, nombre, puesto, sueldo, fechaNacimiento);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }


    /**
     * Método que lee un empleado a partir de su identificador.
     * Valida el identificador.
     * @param identificador del empleado a buscar.
     * @return El empleado correspondiente al identificador.
     */
    @Override
    public Empleado read(String identificador) {
        if (identificador == null || identificador.isEmpty()){
            return null;
        }
        Empleado empleado = new Empleado(identificador);
        return read(empleado);
    }

    /**
     * Método que lee un empleado a partir de todas las propiedades de Empleado.
     * Valida si el objeto es nulo o no y posteriormente obtiene la información del fichero
     * para identificar y ver si coincide con el identificador que lo diferencia del resto de la lista.
     * @param Empleado como objeto.
     * @return el empleado buscado.
     */
    @Override
    public Empleado read(Empleado empleado) {
       if (empleado == null || empleado.getIdentificador() == null 
       || empleado.getIdentificador().isEmpty()){
            return empleado;
       }
       Set<Empleado> empleados = read(fichero);
       for (Empleado empleadoLeer : empleados) {
            if(empleadoLeer.getIdentificador().equals(empleado.getIdentificador())){
                return empleadoLeer;
            }
       }
       return null;
    }

    /**
     * Lee el contenido del fichero.
     * @param contenido del fichero.
     * 
     */
    public Set<Empleado> read(File fichero) {
        Set<Empleado> empleados = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            return new HashSet<>();
        }
        return empleados;
    }



    /**
     * Método que actualiza los datos de un empleado.
     * @param empleado por actualizar datos.
     * @return datos actualizados del empleado.
     */
    @Override
    public boolean update(Empleado empleado) {
        if(empleado == null || empleado.getIdentificador()==null 
        || empleado.getIdentificador().isEmpty()){
            return false;
        }
        Set<Empleado> empleados = read(fichero);
        if(!empleados.contains(empleado)){
            return false;
        }
        for (Empleado empleadoBuscar : empleados) {
            if(empleadoBuscar.equals(empleado)){
                empleados.remove(empleadoBuscar);
                empleados.add(empleado);
                return updateFile(empleados,fichero);
            }
        }
        return false;
    }

    /**
     * Método que un empleado en la lista del fichero
     * @param empleados
     * @param fichero
     * @return
     */
    private boolean updateFile (Set<Empleado> empleados, File fichero){
        String path = fichero.getAbsolutePath();
        fichero.delete();
        try {
            fichero.delete();
            fichero.createNewFile();
        } catch (IOException e) {
            return false;
        }
        for (Empleado empleado : empleados) {
            create(empleado);
        }
        return true;
    }
    
    /**
     * Método que elimina un empleado deseado, por su identificador.
     * @return empleado eliminado. Por el contrario, false.
     */
    @Override
    public boolean delete(String identificador) {
       if (identificador == null || identificador.isEmpty()){
        return false;
       }
       Set<Empleado> empleados = read(fichero);

       if (!empleados.contains(identificador)){
        return false;
       }
       for (Empleado empleadoEliminar : empleados) {
            if(empleadoEliminar.getIdentificador().equals(identificador)){
                empleados.remove(empleadoEliminar);
                return updateFile(empleados, fichero);
            }
       }
       return false;
    }

    /**
     * Método que lista una cantidad de empleados del fichero por un puesto de trabajo en concreto. 
     * @return  lista de empleados por un puesto de trabajo
     */
    @Override
    public Set<Empleado> empleadosPorPuesto(String puesto) {
        if(puesto == null || puesto.isEmpty()){
            return Collections.emptySet();
        }
        Set<Empleado> listaEmpleadosPuesto = new HashSet<>();
        Set<Empleado> empleados = read(fichero);
        for (Empleado empleadoLista : empleados) {
            if(empleadoLista.getPuesto().equals(puesto)){
                listaEmpleadosPuesto.add(empleadoLista);
            }
        }
        return listaEmpleadosPuesto;
    }

    /**
     * Método que lista empleados que hayan nacido entre dos fechas.
     * @return lista de empleados que estén entre dos fechas.
     */
    @Override
    public Set<Empleado> empleadosPorEdad(String fechaInicio, String fechaFin) {
        if(fechaInicio == null || fechaFin == null || fechaInicio.isEmpty()|| fechaFin.isEmpty()){
            return Collections.emptySet();
        }
        DateTimeFormatter fechaFormato = DateTimeFormatter.ofPattern("dd'/'MM'yyyy'");
        LocalDate inicio;
        LocalDate fin;
        try {
            inicio = LocalDate.parse(fechaInicio, fechaFormato);
            fin = LocalDate.parse(fechaFin, fechaFormato);
        } catch (Exception e) {
           return new HashSet<>();
        }
        Set<Empleado> listaEmpleadosEdad = new HashSet<>();
        Set<Empleado> empleados = read(fichero);
        for (Empleado empleadoLista : empleados) {
            try {
                LocalDate fechaNacimiento = LocalDate.parse(empleadoLista.getFechaNacimiento(),fechaFormato);
                if(fechaNacimiento.isEqual(inicio) || fechaNacimiento.isAfter(inicio) &&
                    fechaNacimiento.isEqual(fin) || fechaNacimiento.isBefore(fin)){
                        listaEmpleadosEdad.add(empleadoLista);
                    }
            } catch (Exception e) {
                System.out.println("Error" + e.getMessage());
            }
        }
        return listaEmpleadosEdad;
    }
    
}
