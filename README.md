# Viajes con Seguridad JWT

## Idea del Proyecto
En este proyecto se implementará la seguridad JWT en los aspectos de usuario y se manejará de forma segura su token con la información, además de manejar los roles de diferentes usuarios.

## Justificación del Proyecto
Implementar seguridad JWT en un proyecto de viajes garantiza la protección de datos personales , mejora la experiencia del usuario al permitir sesiones continuas , y facilita la escalabilidad y flexibilidad de la aplicación. Además, reduce la carga en el servidor al almacenar tokens en el cliente.

## Descripción Detallada de las Tablas
### Tabla 1: Usuarios
- **Descripción**: Almacena la información de los usuarios.
- **Columnas**:
  - **Columna 1**: id_usuario(PK).
  - **Columna 2**: nombre(String).
  - **Columna 3**: email(String).
  - **Columna 4**: teléfono(Int)
  - **Columna 5**: rol (“admin”,”user”)(String).

### Tabla 2: Viajes
- **Descripción**: Almacena toda la información del viaje relacionada con el usuario y destino.
- **Columnas**:
  - **Columna 1**: id_viajes(PK).
  - **Columna 2**: descripción(String).
  - **Columna 3**: fecha_ida(Date).
  - **Columna 4**: fecha_regreso(Date).
  - **Columna 5**: id_usuario(FK).
  - **Columna 6**: id_destino(FK).

### Tabla 3: Destinos
- **Descripción**: Almacena todos los destinos de un viaje.
- **Columnas**:
  - **Columna 1**: id_destino(PK).
  - **Columna 2**: nombre(String).
  - **Columna 3**: pais(String).
  - **Columna 4**: descripción(String).

