# (Unidad -4) Tarea 2 (Uso y métodos en el paquete java.io.)




## 1. Interfaz `Operations`

La interfaz `Operations` define el contrato que deben cumplir todas las pclases que gestionen operaciones CRUD sobre objetos **Empleado**. Los métodos definidos en la interfaz son los siguientes: 

- `boolean create(Empleado empleado)`
Crea y almacena un nuevo empleado. Retorna true si la operación fue exitosa y false si ocurrió algún error (por ejemplo, si el empleado ya existe o los datos son inválidos).

- `Empleado read(String identificador)`
Lee y retorna un empleado a partir de su identificador único. Si el identificador es nulo, vacío o no se encuentra el empleado, retorna null.

- `Empleado read(Empleado empleado)`
Lee y retorna un empleado utilizando un objeto de tipo Empleado (generalmente se utiliza su identificador). Si el empleado pasado es nulo o sus datos no son válidos, retorna null.

- `boolean update(Empleado empleado)`
Actualiza la información de un empleado existente. Retorna true si la actualización se realizó correctamente y false en caso de error.

- `boolean delete(String identificador)`
Elimina un empleado a partir de su identificador. Retorna true si la eliminación fue exitosa y false en caso de error.

- `Set<Empleado> empleadosPorPuesto(String puesto)`
Retorna un conjunto de empleados que ocupan un puesto de trabajo específico. Si el parámetro es nulo o vacío, retorna un conjunto vacío.

- `Set<Empleado> empleadosPorEdad(String fechaInicio, String fechaFin)`
Retorna un conjunto de empleados cuyos nacimientos se encuentran entre las fechas proporcionadas (formato "dd/MM/yyyy"). Si las fechas son inválidas o no se encuentran empleados en ese rango, retorna un conjunto vacío.

---

## 2. Clase `Empleado`

La clase **Empleado** representa a un empleado y contiene la siguiente información:

- **Atributos:**
  - `String identificador`: Identificador único del empleado.
  - `String nombre`: Nombre completo del empleado.
  - `String puesto`: Puesto de trabajo.
  - `double salario`: Salario del empleado.
  - `String fechaNacimiento`: Fecha de nacimiento en formato `dd/MM/yyyy`.

- **Métodos:**
  - **Constructores:** Permiten inicializar los atributos del empleado.
  - **Getters y Setters:** Permiten acceder y modificar los atributos.
  - **`toString()`**: Retorna una representación en forma de cadena del objeto, la cual se utiliza para almacenar la información en el fichero.
  - **`equals(Object obj)` y `hashCode()`**: Se sobreescriben basándose en el identificador para asegurar que dos empleados sean considerados iguales si tienen el mismo identificador. Esto es fundamental para el uso correcto de colecciones como `Set` que no admiten duplicados.
  - *(Opcional)* Métodos auxiliares, como `getEdad()`, que calcula la edad a partir de la fecha de nacimiento.

---

## 3. Clase `FileOperations`

La clase **FileOperations** implementa la interfaz `Operations` y se encarga de gestionar la persistencia de objetos **Empleado** en un fichero de texto. Sus responsabilidades principales son:

- **Gestión del Fichero:**  
  Define un campo `File fichero` y una ruta (`path`) en la que se encuentra el fichero. En el constructor se verifica que el fichero exista y sea del tipo adecuado.

- **Implementación de Operaciones CRUD:**
  - **`create(Empleado empleado)`**:  
    Antes de insertar un nuevo empleado, el método lee todos los empleados existentes y verifica, mediante la comparación con el método `equals()`, que no exista ya un registro con el mismo identificador. Si existe, no se inserta y retorna `false`.
    
  - **`read(String identificador)` y `read(Empleado empleado)`**:  
    Estos métodos leen el fichero, lo parsean y retornan el objeto **Empleado** que coincida con el identificador proporcionado.
    
  - **`update(Empleado empleado)`**:  
    Actualiza los datos de un empleado existente. Primero, se carga el conjunto de empleados; luego se busca el registro que coincida, se elimina y se añade el nuevo registro actualizado. Finalmente, se reescribe el fichero.
    
  - **`delete(String identificador)`**:  
    Elimina el registro del empleado cuyo identificador coincide con el proporcionado. Se utiliza un iterador para eliminar el objeto del conjunto y luego se actualiza el fichero.
    
  - **`empleadosPorPuesto(String puesto)`** y **`empleadosPorEdad(String fechaInicio, String fechaFin)`**:  
    Permiten filtrar y retornar conjuntos de empleados según el puesto de trabajo o el rango de fechas de nacimiento, respectivamente.

- **Métodos Auxiliares Privados:**
  - **`dividirLíneaDatos(String linea)`**:  
    Parsea una línea del fichero (suponiendo un formato CSV: "identificador, nombre, puesto, sueldo, fechaNacimiento") y retorna un objeto **Empleado**.
    
  - **`read(File fichero)`**:  
    Lee el fichero y retorna un `Set<Empleado>` con todos los registros parseados.
    
  - **`updateFile(Set<Empleado> empleados, File fichero)`**:  
    Reescribe el fichero con el conjunto de empleados actualizado, eliminando el contenido anterior y escribiendo cada empleado.

---