## Laboratorio #9 — MVVM con Jetpack Compose  
**Curso:** Programación de Plataformas Móviles  
**Universidad del Valle de Guatemala**

---

### Contexto y Objetivo

En Android moderno, la interfaz de usuario (UI) se construye como una **función del estado**. Jetpack Compose facilita este enfoque declarativo, pero el manejo del estado a través del ciclo de vida (rotaciones, navegación, recreaciones) requiere una arquitectura que lo preserve y lo organice correctamente.

El objetivo del laboratorio fue **construir una mini aplicación de “Lista de Deseos”** (Wishlist App) en tres partes para comprender los límites del estado local (`remember`), implementar **MVVM** con `ViewModel` + `StateFlow`, y finalmente compartir el mismo estado entre pantallas usando **Navigation Compose**.

---

## Parte 1 — Estado local y sus límites

**Archivo:** `LikeCounterScreen.kt`

Se construyó una pantalla sencilla con un contador de "Me Gusta" que incrementa con un botón:

```kotlin
var likeCount by remember { mutableStateOf(0) }
```

Al rotar el dispositivo, el contador vuelve a cero porque el estado se almacena únicamente en memoria temporal (local al Composable).

### Reflexión 1 
El estado no persiste porque **`remember`** guarda los datos dentro de la composición activa.  
Cuando la actividad se destruye por una rotación, se crea una nueva instancia de la UI, perdiendo todo el estado previo.  
Esto muestra la necesidad de una fuente de verdad más duradera: el **ViewModel**.

📸 *[Agrega aquí la captura de LikeCounterScreen]*

---

## Parte 2 — MVVM con ViewModel y StateFlow

**Archivos clave:**
- `Product.kt`
- `WishlistUiState.kt`
- `WishlistViewModel.kt`
- `WishlistScreen.kt`

Se refactorizó el proyecto usando el patrón **MVVM** (Model–View–ViewModel):

- `WishlistViewModel` mantiene el estado de la UI con un `MutableStateFlow`.
- La UI (`WishlistScreen`) recolecta el flujo con `collectAsStateWithLifecycle()`.
- Los productos se representan como una lista de `Product` y se pueden marcar como favoritos con un ícono de estrella.

```kotlin
val uiState = vm.uiState.collectAsStateWithLifecycle().value
```

El estado persiste al rotar el dispositivo gracias al **ViewModel**, que vive mientras la actividad o el gráfico de navegación exista.

### Reflexión 2  
El ViewModel resuelve el problema anterior porque funciona como **única fuente de verdad** para la UI y sobrevive a los cambios de configuración.  
Además, al usar `StateFlow`, el flujo de datos es reactivo y seguro para el ciclo de vida.

📸 *[Agrega aquí la captura de WishlistScreen]*

---

## Parte 3 — Navigation Compose y ViewModel compartido

**Archivos clave:**
- `AppNavGraph.kt`
- `Destinations.kt`
- `ProfileScreen.kt`

Se configuró un **NavHost** con dos destinos:
- `WishlistScreen` (lista principal)
- `ProfileScreen` (contador de favoritos)

Ambas pantallas comparten el mismo `WishlistViewModel`, creado con alcance del gráfico raíz:

```kotlin
val parentEntry = remember(backStackEntry) {
    navController.getBackStackEntry(Destinations.Root.route)
}
val vm: WishlistViewModel = viewModel(parentEntry)
```

Esto permite que las dos pantallas accedan al mismo estado sin duplicarlo.

### Reflexión 3  
Compartir un `ViewModel` entre destinos es más eficiente y limpio que pasar listas o argumentos por navegación.  
Se mantiene una **única fuente de verdad**, evitando inconsistencias y garantizando sincronización entre pantallas.

📸 *[Agrega aquí la captura de ProfileScreen]*

---

## Conceptos aplicados
- **MVVM:** separación clara entre lógica (ViewModel), datos (Model) y presentación (UI).  
- **StateFlow + collectAsStateWithLifecycle():** flujo de datos reactivo y seguro.  
- **Navigation Compose:** manejo estructurado de destinos y back stack.  
- **ViewModel compartido:** persistencia del estado entre pantallas.

---

## Conclusión
Este laboratorio demostró el poder del enfoque declarativo de Compose y la importancia del **manejo de estado a nivel de arquitectura**.  
`remember` es útil para estados efímeros, pero **MVVM con ViewModel y StateFlow** permite construir aplicaciones robustas, escalables y reactivas.  
La integración con **Navigation Compose** consolida un flujo unidireccional de datos (UDF) limpio y sostenible.

---

## Enlace al repositorio
👉 [https://github.com/MiguelRosas11/Laboratorio9](https://github.com/MiguelRosas11/Laboratorio9)
