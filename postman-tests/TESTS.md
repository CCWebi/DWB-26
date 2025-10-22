# Pruebas Categoría

Este archivo contiene las pruebas para la categoría de la API utilizando Postman.

Esta diseñado con compatibilidad en versiones no tan receientes de Postman, así las pruebas pueden ser ejecutadas en una amplia gama de entornos.

## How to Run the Tests

1. Abre Postman.
2. Importa la colección de pruebas desde el archivo json.
3. Configura las variables de entorno necesarias (`baseUrl` y `token`, las demás dejarlas intactas, su valor se reestablece al iniciar las pruebas :D).
4. Ejecuta la colección de pruebas desde un apartado como *RUN*, con la configuración *Run manually* y *delay: 1000ms* y **Sin las preubas depreciadas**.
5. Revisa los resultados de las pruebas en la pestaña de resultados (deben pasar todas las pruebas).

> [!IMPORTANT]
> **No ejecutar las pruebas depreciadas**, ni ejecutarlas en paralelo o sin seguir el orden dado.

Ante cualquier duda, contacta al equipo de desarrollo :D.
