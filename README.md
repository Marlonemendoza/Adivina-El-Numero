# 📱 Adivina El Número (Android App)

**Adivina El Número** es una aplicación móvil nativa para Android desarrollada en **Kotlin** con **Android Studio**. Cuenta con una experiencia interactiva, validación de datos de entrada, contador de intentos, pistas en tiempo real y una interfaz dinámica construida con animaciones de degradados (*gradients*) y corrutinas para la gestión del flujo del juego.

---

## 🚀 Características y Funcionalidades

- **Pantalla de Bienvenida (Landing Page):** Interfaz principal de inicio con navegación mediante `MaterialButton` e inicio de sesión/configuración.
- **Configuración Personalizada:** Permite ingresar el nombre del jugador y definir el rango máximo del número aleatorio (ej. 1 a 100) con validación mediante `AlertDialog`.
- **Lógica de Juego Dinámica:**
  - Generación aleatoria de números en el rango definido.
  - Indicador de pistas en tiempo real (*"El número oculto es mayor / menor"*).
  - Contador dinámico de intentos realizados.
- **Efectos Visuales y Animaciones:**
  - Fondo animado con transición suave de degradados en bucle (`AnimationDrawable` mediante `animated_gradient.xml`).
  - Animación de victoria: cambio en la velocidad del fondo animado, despliegue del número secreto y marco de felicitación con estrellas al adivinar el número.
  - Ocultamiento automático del teclado al completar el juego (`InputMethodManager`).
  - Animación asíncrona mediante **Kotlin Coroutines (`lifecycleScope`)**.

---

## 🛠️ Tecnologías y Librerías Utilizadas

- **Lenguaje:** [Kotlin](https://kotlinlang.org/)
- **Entorno de Desarrollo:** [Android Studio](https://developer.android.com/studio)
- **UI & Layouts:** XML Layouts, `ConstraintLayout`, `CardView`, `MaterialButton`, Vector Drawables (`XML`).
- **Asincronía & Corrutinas:** `kotlinx.coroutines`, `lifecycleScope` para temporización de eventos visuales.
- **Animaciones UI:** `AnimationDrawable` con listas de animación (`animation-list`).
- **Arquitectura:** Actividades modulares (`MainActivity`, `InicioUsuarioActivity`, `JuegoActivity`).

---

## 📂 Estructura del Código

```text
AdivinaElNumero/
├── app/src/main/
│   ├── java/com/example/adivinaelnumero/
│   │   ├── MainActivity.kt           # Pantalla de bienvenida / Inicio
│   │   ├── InicioUsuarioActivity.kt  # Captura de datos y validaciones
│   │   └── JuegoActivity.kt          # Lógica principal del juego y victoria
│   └── res/
│       ├── drawable/
│       │   ├── animated_gradient.xml # Lista de animación de degradados
│       │   ├── gradient_1.xml        # Transición azul inicial
│       │   ├── gradient_2.xml        # Transición azul a púrpura
│       │   ├── gradient_3.xml        # Transición púrpura a violeta
│       │   ├── redondear_borde.xml   # Estilo curvo para EditText
│       │   └── adivina_el_numeroi_icono.jpg # Icono principal
│       └── layout/
│           ├── activity_main.xml
│           ├── activity_inicio_usuario.xml
│           └── activity_juego.xml
