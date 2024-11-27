# **FilmApp: Cliente-Servidor con Threads, BBDD y Gestión de Películas**

FilmApp es una aplicación diseñada para gestionar una colección de películas en un entorno cliente-servidor. Este proyecto combina el uso de Java, programación concurrente con hilos, y acceso a bases de datos SQL, integrando funcionalidades clave para la comunicación cliente-servidor y la persistencia de datos.

## **Características**
- 📽 **Gestión de Películas**: Permite crear, consultar, actualizar y eliminar películas desde el cliente.  
- ⚡ **Arquitectura Cliente-Servidor**: Implementación de un servidor central que gestiona solicitudes de varios clientes.  
- 🧵 **Concurrencia con Hilos**: Soporte para múltiples clientes conectados simultáneamente usando threads en Java.  
- 🗃 **Base de Datos SQL**: Persistencia de datos utilizando JDBC para conectarse a una base de datos MySQL.  
- 🎓 **Proyecto Educativo**: Desarrollado como parte del curso de 2º DAM para consolidar conocimientos en Java, hilos y bases de datos.

---

## **Requisitos**
### **Software necesario**
- JDK 11 o superior
- MySQL Server (u otro servidor SQL compatible)
- IDE (Eclipse, IntelliJ, NetBeans, etc.)

### **Configuración de la Base de Datos**
1. Crear una base de datos llamada `filmapp`.
2. Ejecutar el script SQL incluido en el repositorio para crear las tablas necesarias. Archivo: `database_schema.sql`.
3. Configurar las credenciales en el archivo `db.properties` ubicado en el proyecto.

Ejemplo de `db.properties`:
```properties
db.url=jdbc:mysql://localhost:3306/filmapp
db.user=tu_usuario
db.password=tu_contraseña
```

## **Estructura del Proyecto**
### **Cliente**
- Interfaz gráfica o consola para que el usuario gestione películas.  
- Comunicación con el servidor mediante sockets.  

### **Servidor**
- Gestión de múltiples clientes usando hilos.  
- Conexión a la base de datos para realizar operaciones CRUD.  

### **Base de Datos**
- Tablas para almacenar la información de las películas (títulos, director, género, año, etc.).  

## **Instalación y Ejecución**
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/danicostas-xyz/2dam-filmapp-server-client-threads-bbdd-activity.git
   cd 2dam-filmapp-server-client-threads-bbdd-activity
   ```

2. Configurar la base de datos según las instrucciones.
3. Compilar y ejecutar el servidor:
    ```bash
    javac Servidor.java
    java Servidor
    ```

4. Compilar y ejecutar el cliente:
    ```bash
    javac Cliente.java
    java Cliente
    ```
## **Tecnologías Utilizadas**
- **Lenguaje**: Java  
- **Conexión a BD**: JDBC  
- **Servidor de BD**: MySQL  
- **Programación Concurrente**: Hilos en Java  
- **Comunicación Cliente-Servidor**: Sockets TCP/IP  

---

## **Estado del Proyecto**
Actualmente en desarrollo.  
- [ ] Diseño del esquema de la base de datos  
- [ ] Implementación básica del servidor  
- [ ] Finalización de las operaciones CRUD  
- [ ] Pruebas con múltiples clientes  

---

## **Contribuciones**
Las contribuciones son bienvenidas. Si tienes ideas para mejorar el proyecto, envía un pull request o abre un issue.

---

## **Autor**
**Dani Costas**  
Estudiante de Desarrollo de Aplicaciones Multiplataforma (DAM) en UpgradeHub (Madrid).  
[LinkedIn](https://www.linkedin.com/in/danicostas-xyz/) | [GitHub](https://github.com/danicostas-xyz)


