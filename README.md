# NotaViva

Aplicación móvil para que un periodista administre casos de investigación,
sus entrevistas, evidencias y conclusiones.

Desarrollada en Kotlin con etpack Compose (UI declarativa) y Room
(persistencia local sobre SQLite). No usa backend, servicios web ni Firebase.

## Arquitectura

El proyecto separa responsabilidades en tres capas, tal como pide el ejercicio:

```
data/        -> Entidades Room (CaseEntity, InterviewEntity, EvidenceEntity),
                DAOs, AppDatabase y CaseRepository (persistencia).
viewmodel/   -> CaseViewModel: expone estado (StateFlow) y reglas de negocio
                a la interfaz, sin conocer detalles de Room ni de Compose.
util/        -> CaseUtils: lógica pura (filtrado, validaciones) sin
                dependencias de Android, fácil de probar con JUnit.
ui/          -> Pantallas Compose (Home, Lista, Formulario, Detalle) y
                navegación (Navigation Compose). Solo dibuja estado y
                delega acciones al ViewModel.
```

Esto permite testear la lógica de negocio (`CaseUtilsTest`) sin necesidad de
emulador ni dispositivo Android.

## Funcionalidades implementadas

- **Home**: resumen general (conteo de casos por estado) y accesos directos.
- **Crear / editar caso**: título, descripción, fecha y estado (un mismo
  formulario reutilizado para ambas operaciones).
- **Listado de casos**: búsqueda en tiempo real por título/descripción,
  visualización del estado con un chip de color, eliminación con confirmación.
- **Detalle de caso** (con pestañas, inspirado en el mockup):
  - *Resumen*: descripción del caso.
  - *Entrevistas*: alta y baja de entrevistas con hallazgos principales.
  - *Conclusión*: redacción y edición de la conclusión del caso.
  - *Evidencias*: alta y baja de evidencias asociadas al caso.
  - Cambio de estado (En investigación / Publicado / Cerrado) desde un chip.
  - Eliminar el caso completo (borra en cascada entrevistas y evidencias).

## Cómo abrir el proyecto

1. Abre **Android Studio** (Koala o superior recomendado).
2. `File > Open...` y selecciona la carpeta raíz `NotaViva`.
3. Espera a que sincronice Gradle (descargará Gradle 8.6 y las dependencias
   automáticamente, requiere conexión a internet la primera vez).
4. Ejecuta en un emulador o dispositivo con **Android 8.0 (API 26) o superior**.

## Cómo correr las pruebas unitarias

En Android Studio: click derecho sobre
`app/src/test/java/com/example/notaviva/CaseUtilsTest.kt` → **Run**.

Por línea de comandos (una vez tengas `gradlew` generado por Android Studio):

```bash
./gradlew testDebugUnitTest
```

## Notas de diseño

- Las evidencias se registran como referencia/descripción textual (nombre +
  detalle), ya que el ejercicio no exige almacenamiento de archivos ni
  backend; esto mantiene el alcance simple y evita permisos de
  almacenamiento innecesarios.
- La interfaz es intencionalmente sencilla (Material 3 por defecto) para
  priorizar la lógica de negocio y la arquitectura, cumpliendo con la
  indicación de que "la interfaz gráfica queda a criterio del estudiante".
