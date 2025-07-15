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
  

---
## 🍿 Cómo obtener una API Key de OMDb
**Para que tu aplicación funcione correctamente, necesitas una clave de acceso (API Key) gratuita desde el sitio de OMDb.**

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

##  🧪 Ejemplo de uso

 **Consulta una película en tu navegador o con Postman:**

```bash
  GET http://localhost:8080/api/movies/v1/nombre-de-la-película
 ```
**Respuesta esperada:**
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

##  📝 Notas
- La API Key de OMDb está hardcodeada, puedes moverla a **`application.properties`** si prefieres ocultarla.
- El proyecto usa H2 como base de datos en memoria, por lo que no necesitas configurar una base externa.

---

## 📄 Licencia

**MIT License. Puedes usar este código libremente con fines educativos o personales.**

---
   
