# Actividad-de-Aprendizaje2
# Sistema de Generación de Actividades Terapéuticas con IA y TTS

Este proyecto implementa un sistema basado en **Spring Boot (backend)** y **React (frontend)** para generar actividades terapéuticas personalizadas usando **OpenAI API** y retroalimentación auditiva con **Google Cloud Text-to-Speech**.  
Además, integra almacenamiento en **PostgreSQL** y soporta métricas de progreso por niño.

## **Características principales**
- Generación de texto mediante **IA (OpenAI)**.
- Conversión de texto a voz (TTS) con **Google Cloud**.
- Gestión de usuarios, perfiles de niños y actividades.
- Historial y progreso por niño.
- API RESTful con validación de entradas y manejo de errores global.
- Interfaz frontend en React con **Vite** y **React Router**.

## **1. Instrucciones de Despliegue**

### **Requisitos previos**
- [Node.js](https://nodejs.org/) v16+  
- [Java JDK 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)  
- [PostgreSQL](https://www.postgresql.org/download/)  
- Cuenta en **Google Cloud** con servicio **Text-to-Speech habilitado**.  
- Clave API de **OpenAI**.  

### **Backend (Spring Boot)**
1. Clona el repositorio:
   ```bash
   git clone https://github.com/usuario/speechdown-project.git
   cd backend

2. Configura las variables en application.properties:
spring.datasource.url=jdbc:postgresql://localhost:5432/speechdown_db
spring.datasource.username=postgres
spring.datasource.password=tu_password

openai.api.key=tu_api_key_openai
google.application.credentials=credentials.json

3. Ejecuta la aplicación:
mvn spring-boot:run
API disponible en: http://localhost:8080

**Frontend (React con Vite)**
1. Ir a la carpeta del frontend:
cd frontend

2. Instalar dependencias:
npm install

3. Ejecutar el proyecto:
npm run dev
Disponible en: http://localhost:5173

## **2. Diagrama de Arquitectura**
Descripción rápida:

*Frontend: React + Vite → Solicitudes al backend.
*Backend: Spring Boot → Controladores REST + Servicios.
*IA: OpenAI API → Generación de texto.
*TTS: Google Cloud → Conversión texto a audio.
*Base de datos: PostgreSQL → Persistencia.

## **3. Ejemplos de Prompts Usados con la API de IA**
Un ejemplo reale que se puede usar en la API:
{
  "message": "Escribe un cuento sobre un perro que juega fútbol",
  "age": 6,
  "difficulty": "Fácil"
}

## **4. Endpoints principales**
Método	    Endpoint	                            Descripción
POST	  /api/activities/generate/{childId}	    Genera una actividad y la guarda
GET    	/api/activities/child/{childId}	        Lista historial de actividades
GET    	/api/activities/progress/{childId}	    Obtiene progreso detallado

## **5. Tecnologías Usadas**
*Backend: Spring Boot, Java 17, Maven
*Frontend: React, Vite, TailwindCSS
*Base de Datos: PostgreSQL
*IA: OpenAI GPT API
*TTS: Google Cloud Text-to-Speech

## Este proyecto es académico y desarrollado para la materia Arquitectura de Software.

