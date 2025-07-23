<img width="1536" height="1024" alt="ChatGPT Image 14 jul 2025, 07_19_26 p m" src="https://github.com/user-attachments/assets/a80da4a2-b6f1-4c21-8adc-d72d8d839cbf" />

# 🎬 Screenmatch - API de Películas con Traducción Automática

Este proyecto Spring Boot permite consultar películas usando la API pública de OMDb y traducir automáticamente la descripción (`plot`) al español usando la API de Gemini (Google AI).

---

## 🚀 Características

- Consulta de películas por título desde OMDb API.
- Traducción automática de la sinopsis usando Google Gemini (IA generativa).
- Arquitectura limpia con Spring Boot 3 y Java 21.
- Separación de servicios, controladores, DTOs y manejo de errores personalizado.
- Seguridad: la API Key de Gemini nunca se sube al repositorio.

---

## 🧰 Tecnologías utilizadas

- Java 21
- Spring Boot 3.5.3
- Maven
- OMDb API (gratuita)
- Google AI Gemini API
- MapStruct
- H2 Database (memoria)
- Jackson & Gson
- Postgres (SQL)
  

---
## 🍿 Cómo obtener una API Key de OMDb
Para que tu aplicación funcione correctamente, necesitas una clave de acceso (API Key) gratuita desde el sitio de OMDb.

### 📝 Pasos para obtenerla:

1. Ve al sitio oficial de OMDb:

   👉 [https://www.omdbapi.com/apikey.aspx](https://www.omdbapi.com/apikey.aspx)

2. Rellena el formulario con tu email y selecciona el plan gratuito:
   - Email Address: escribe tu correo electrónico válido
   - Choose a plan: selecciona Free (para uso personal o pruebas)
3. Haz clic en el botón Submit.
4. Recibirás tu API Key por correo electrónico en unos minutos.
5. Una vez que tengas tu clave, puedes usarla de dos maneras:
   - Hardcodeada temporalmente en tu código:
     
      ```java
          URI uri = URI.create("http://www.omdbapi.com/?t=" + title + "&apikey=TU_API_KEY");
     ```
      
    - O guardarla en application.properties para mayor seguridad:
      
        ```properties
         omdb.api.key=TU_API_KEY
        ```
        
 6. Si la colocas en application.properties, puedes accederla en tu clase Java así:
    
     ```java
          @Value("${omdb.api.key}")
          private String omdbApiKey;
     ```
 ## 🛑 Notas importantes
 - El plan gratuito tiene límite de 1,000 peticiones por día.
 - No uses esta API para fines comerciales sin autorización del proveedor.
        
---
## 🔐 Configuración de Clave API (Gemini)

Este proyecto utiliza una clave API de [Google AI Studio (Gemini)](https://makersuite.google.com/app/apikey) para acceder a la funcionalidad de traducción automática.

### 🛠️ Pasos para configurar tu clave

1. Copia el archivo de ejemplo y crea tu archivo de configuración real:

   ```bash
   cp src/main/resources/application-example.properties src/main/resources/application.properties
     ```
   
2. Abre **`application.properties`** y reemplaza el valor por tu clave real:

    ```bash
     gemini.api.key=TU_API_KEY_REAL
     ```

3. Puedes obtener tu clave desde:
   
     👉 [https://makersuite.google.com/app/apikey](https://makersuite.google.com/app/apikey)
   
---

## 🐘 Configuración de PostgreSQL (opcional)

Este proyecto también puede usar **`PostgreSQL`** como base de datos en lugar de H2 en memoria. 
Si deseas persistencia real de los datos entre reinicios, puedes usar PostgreSQL.

### 🔽 Descarga e instalación

1. Ve al sitio oficial de PostgreSQL y descarga la versión más reciente para tu sistema operativo:

   👉 [https://makersuite.google.com/app/apikey](https://makersuite.google.com/app/apikey)
2. Durante la instalación, asegúrate de configurar un usuario, contraseña y puerto (por defecto es el 5432).

## ⚙️ 🛠️ Configuración del perfil PostgreSQL
1. Crea un archivo de configuración:

   `src/main/resources/application-postgres.properties`
2. Copia y edita este contenido:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/TU_BASE_DE_DATOS
spring.datasource.username=postgres
spring.datasource.password=TU_PASSWORD
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

 ``` 
 **🔐 Reemplaza TU_PASSWORD con la contraseña real de tu usuario PostgreSQL.**
___

## 🚀 Activar perfil de PostgreSQL

Para levantar la app usando PostgreSQL, ejecuta con el siguiente perfil:

```bash
     ./mvnw spring-boot:run -Dspring-boot.run.profiles=postgres

```
**⚙️ Configuración en `application.properties`**

Configura el perfil activo:

**`spring.profiles.active=postgres`**
___



## 🌐 Endpoints disponibles
La API expone los siguientes endpoints:

Consulta una película en tu **`navegador o con Postman:`**

 ### 🎥 `GET /api/movies/v1/{title}`

Consulta una película por su título (en inglés). Busca en la base de datos local, y si no existe, la trae desde la API pública de OMDb, traduce la sinopsis al español con Gemini.

  - **Parámetros de ruta**:

    - **title**: Título de la película (en inglés, sin espacios o codificado en URL).
    
- **Ejemplo de uso**:

```bash
  GET http://localhost:8080/api/movies/v1/Halo
 ```

- **Respuesta esperada (`200 OK`):**
```json
 {
    "title": "Halo",
    "year": "2022–2024",
    "rated": "TV-14",
    "released": "24 Mar 2022",
    "runtime": "N/A",
    "genre": "Action, Adventure, Sci-Fi",
    "director": "N/A",
    "writer": null,
    "actors": "Pablo Schreiber, Shabana Azmi, Natasha Culzac",
    "plot": "Con la galaxia al borde de la destrucción, el Jefe Maestro John-117 lidera a su equipo de Spartans contra la amenaza alienígena conocida como el Covenant.\n",
    "language": "English",
    "country": "United States",
    "awards": "6 wins & 3 nominations total",
    "poster": "https://m.media-amazon.com/images/M/MV5BYzNkODg3OTYtM2EyZC00NmEyLTgzZDktYjY5NGNjZmQ5ZDRiXkEyXkFqcGc@._V1_SX300.jpg",
    "metascore": "N/A",
    "imdbRating": "7.3",
    "imdbVotes": "104,700",
    "imdbID": "tt2934286",
    "type": "series",
    "totalSeasons": "2",
    "response": "True",
    "ratings": [
        {
            "source": "Internet Movie Database",
            "value": "7.3/10"
        }
    ]
}
 ```
---
 
### 💾 `POST /api/movies/v1/save/{title}`

Consulta y guarda una película en la base de datos.

- **Parámetros de ruta**:

    - **title**: Título de la película (en inglés, sin espacios o codificado en URL).
- **Acción**:
    - Obtiene la película desde OMDb.
    - Traduce la sinopsis con Gemini.
    - Guarda la información en la base de datos (H2 o PostgreSQL).

- **Ejemplo de uso**:

```bash
  POST http://localhost:8080/api/movies/v1/save/Halo
 ```

- **Respuesta esperada (`200 OK`):**
```json
 {
    "title": "Halo",
    "year": "2022–2024",
    "rated": "TV-14",
    "released": "24 Mar 2022",
    "runtime": "N/A",
    "genre": "Action, Adventure, Sci-Fi",
    "director": "N/A",
    "writer": null,
    "actors": "Pablo Schreiber, Shabana Azmi, Natasha Culzac",
    "plot": "Con la galaxia al borde de la destrucción, el Jefe Maestro John-117 lidera a su equipo de Spartans contra la amenaza alienígena conocida como el Covenant.\n",
    "language": "English",
    "country": "United States",
    "awards": "6 wins & 3 nominations total",
    "poster": "https://m.media-amazon.com/images/M/MV5BYzNkODg3OTYtM2EyZC00NmEyLTgzZDktYjY5NGNjZmQ5ZDRiXkEyXkFqcGc@._V1_SX300.jpg",
    "metascore": "N/A",
    "imdbRating": "7.3",
    "imdbVotes": "104,700",
    "imdbID": "tt2934286",
    "type": "series",
    "totalSeasons": "2",
    "response": "True",
    "ratings": [
        {
            "source": "Internet Movie Database",
            "value": "7.3/10"
        }
    ]
}
 ```
---

### 📋 `GET /api/movies/v1/all`

Lista todas las películas almacenadas en la base de datos.

- **Respuesta**: Lista JSON de películas guardadas.

- **Ejemplo de uso**:

```bash
  GET http://localhost:8080/api/movies/v1/all
 ```

- **Respuesta esperada (`200 OK`):**
```json
 [
  {
    "title": "Halo",
    "year": "2022–2024",
    "rated": "TV-14",
    "released": "24 Mar 2022",
    "runtime": "N/A",
    "genre": "Action, Adventure, Sci-Fi",
    "director": "N/A",
    "writer": null,
    "actors": "Pablo Schreiber, Shabana Azmi, Natasha Culzac",
    "plot": "Con la galaxia al borde de la destrucción, el Jefe Maestro John-117 lidera a su equipo de Spartans contra la amenaza alienígena conocida como el Covenant.\n",
    "language": "English",
    "country": "United States",
    "awards": "6 wins & 3 nominations total",
    "poster": "https://m.media-amazon.com/images/M/MV5BYzNkODg3OTYtM2EyZC00NmEyLTgzZDktYjY5NGNjZmQ5ZDRiXkEyXkFqcGc@._V1_SX300.jpg",
    "metascore": "N/A",
    "imdbRating": "7.3",
    "imdbVotes": "104,700",
    "imdbID": "tt2934286",
    "type": "series",
    "totalSeasons": "2",
    "response": "True",
    "ratings": [
      {
        "source": "Internet Movie Database",
        "value": "7.3/10"
      }
    ]
  },
  {
    "title": "Matrix",
    "year": "1993",
    "rated": "N/A",
    "released": "01 Mar 1993",
    "runtime": "60 min",
    "genre": "Action, Drama, Fantasy",
    "director": "N/A",
    "writer": null,
    "actors": "Nick Mancuso, Phillip Jarrett, Carrie-Anne Moss",
    "plot": "Aquí está la traducción al español:\n\nEl asesino a sueldo Steven Matrix recibe un disparo, experimenta el más allá y obtiene una segunda oportunidad ayudando a otros. Despierta, conoce a guías que le asignan casos donde ayuda a personas usando métodos poco convencionales de su antigua profesión.\n",
    "language": "English",
    "country": "Canada",
    "awards": "1 win total",
    "poster": "https://m.media-amazon.com/images/M/MV5BM2JiZjU1NmQtNjg1Ni00NjA3LTk2MjMtNjYxMTgxODY0NjRhXkEyXkFqcGc@._V1_SX300.jpg",
    "metascore": "N/A",
    "imdbRating": "7.2",
    "imdbVotes": "220",
    "imdbID": "tt0106062",
    "type": "series",
    "totalSeasons": "N/A",
    "response": "True",
    "ratings": [
      {
        "source": "Internet Movie Database",
        "value": "7.2/10"
      }
    ]
  }
]
 ```
---

### 🔍 `GET /api/movies/v1/search?title={partialTitle}`
Busca películas por coincidencia parcial en el título (desde la base de datos local).

- **Parámetros de ruta**:

    - **title**: Título de la película (en inglés, sin espacios o codificado en URL).

- **Ejemplo de uso**:

```bash
  GET http://localhost:8080/api/movies/v1/search?title=matrix
 ```

- **Respuesta esperada (`200 OK`):**
```json
 [
  {
    "title": "Matrix",
    "year": "1993",
    "rated": "N/A",
    "released": "01 Mar 1993",
    "runtime": "60 min",
    "genre": "Action, Drama, Fantasy",
    "director": "N/A",
    "writer": null,
    "actors": "Nick Mancuso, Phillip Jarrett, Carrie-Anne Moss",
    "plot": "Aquí está la traducción al español:\n\nEl asesino a sueldo Steven Matrix recibe un disparo, experimenta el más allá y obtiene una segunda oportunidad ayudando a otros. Despierta, conoce a guías que le asignan casos donde ayuda a personas usando métodos poco convencionales de su antigua profesión.\n",
    "language": "English",
    "country": "Canada",
    "awards": "1 win total",
    "poster": "https://m.media-amazon.com/images/M/MV5BM2JiZjU1NmQtNjg1Ni00NjA3LTk2MjMtNjYxMTgxODY0NjRhXkEyXkFqcGc@._V1_SX300.jpg",
    "metascore": "N/A",
    "imdbRating": "7.2",
    "imdbVotes": "220",
    "imdbID": "tt0106062",
    "type": "series",
    "totalSeasons": "N/A",
    "response": "True",
    "ratings": [
      {
        "source": "Internet Movie Database",
        "value": "7.2/10"
      }
    ]
  }
]
 ```
---
## ⚠️ Posibles errores por endpoint

### 🔍 `GET /api/movies/v1/{title}`

| Código | Error                | Causa posible                                | Ejemplo de respuesta                                              |
|--------|----------------------|----------------------------------------------|-------------------------------------------------------------------|
| 400    | Invalid title        | El título es nulo, vacío o inválido          | `{ "error": "Invalid movie title provided." }`                    |
| 404    | Movie not found      | La película no fue encontrada en OMDb        | `{ "error": "Movie not found for title: unknown" }`              |
| 500    | Internal Server Error| Error interno al consultar o traducir        | `{ "error": "Error while fetching or translating movie data." }` |

---

### 💾 `POST /api/movies/v1/save/{title}`

| Código | Error                | Causa posible                                          | Ejemplo de respuesta                                        |
|--------|----------------------|--------------------------------------------------------|-------------------------------------------------------------|
| 400    | Invalid title        | El título está vacío o es nulo                         | `{ "error": "Invalid movie title provided." }`              |
| 404    | Movie not found      | No se encontró en OMDb                                | `{ "error": "Movie not found for title: matrixx" }`         |
| 409    | Movie already exists | Ya existe una película con ese título guardada        | `{ "error": "Movie 'Matrix' is already stored." }`          |
| 500    | Internal Error       | Fallo en traducción o persistencia                    | `{ "error": "Failed to translate or save movie." }`         |

---

### 📋 `GET /api/movies/v1/all`

| Código | Error                | Causa posible                               | Ejemplo de respuesta                                             |
|--------|----------------------|---------------------------------------------|------------------------------------------------------------------|
| 200    | OK                   | Devuelve lista (vacía o con datos)          | `[ { "title": "Matrix", ... } ]`                                |
| 500    | Database error       | Problema al consultar la base de datos      | `{ "error": "Could not fetch movies from database." }`         |

---

### 🔍 `GET /api/movies/v1/search?title={partialTitle}`

| Código | Error                | Causa posible                                          | Ejemplo de respuesta                                             |
|--------|----------------------|--------------------------------------------------------|------------------------------------------------------------------|
| 400    | Missing parameter    | No se pasó el parámetro `title`                        | `{ "error": "Query parameter 'title' is required." }`           |
| 200    | OK                   | Devuelve coincidencias o lista vacía                  | `[ { "title": "Matrix" } ]`                                     |
| 500    | Internal Error       | Fallo en la búsqueda o conexión                        | `{ "error": "Search operation failed." }`                       |
---
##  📝 Notas
- La API Key de OMDb está hardcodeada, puedes moverla a **`application.properties`** si prefieres ocultarla.
- El proyecto usa H2 como base de datos en memoria, por lo que no necesitas configurar una base externa.

---

## 📄 Licencia

**MIT License. Puedes usar este código libremente con fines educativos o personales.**

---
   
