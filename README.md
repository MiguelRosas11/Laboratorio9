-----------------Reflexión 1: ¿Por qué el estado no persiste al rotar? Relacionar con ciclo de vida, recreación de actividad y recomposición.------------------------

En efecto no guarda el valor por el que iba el contador, ya que al rotar se pierde y se reinicia en 0, esto es debido a que al rotar 
Android destruye y recrea la Activity (y con esta tambien la composición), por eso el remember se reinicia. es decir que remember guarda el estado sólo mientras existe la composición actual. Es decir que Al rotar la pantalla Android 
hace un cambio de configuración. Por defecto la Activity se destruye y se vuelve a crear. 

<img width="397" height="889" alt="image" src="https://github.com/user-attachments/assets/815f7af4-5ba7-4331-b3a2-12a2c3d6f0b9" />


y luego se ve como :


<img width="507" height="256" alt="image" src="https://github.com/user-attachments/assets/7994bb92-ac05-4721-8b07-490254f28fce" />



Ahora bien  cuando la Activity se destruye, la composición de Compose se descarta.
Todo lo que estaba guardado con remember (osea lo que vive en la composición) se pierde. Es decir que lo que pasa con la recomposición es que remember si persiste pero como tal hay una recreación de la actividad por lo que una nueva composición  y el remember se reinicia.



-----------------------------------------------Reflexion II: ¿Por qué el ViewModel resuelve el problema de la Parte 1? Conecta con “única fuente de la verdad” y manejo del ciclo de vida.------------------------------------------------
En esta parte II se crearon varios archivos importantes como lo son el product.kt, WishListUiState, WishlistScreen y WishlistViewModel que este ultimo realmente es el mas importante ya que es la pieza clave para poder como tal resolver el problema que se tenia en la Parte I, que cada rotación que se hacia no se guardaban
los cambios por lo que en esta parte ahora lo que se hizo fue de esto :

<img width="395" height="872" alt="image" src="https://github.com/user-attachments/assets/62390d2d-20d4-48ba-b75f-d0ab93d8d25b" />


se paso a esto:


<img width="1192" height="538" alt="image" src="https://github.com/user-attachments/assets/0c736f51-787a-48ad-9852-21c296383ff3" />

Entonces lo que realmente pasa con  WishlistViewModel  es que  como tiene diferente ciclo de vida que una activity/composable no se borra, ya que en la parte I cuando se hizo el cambio entonces el composable se destruye mientras que por el otro lado El sistema Android mantiene la misma instancia del ViewModel y la reasigna a la nueva Activity recreada.
Ademas este viewModel lo que hace es que iomplementa el patrón de única fuente de la verdad, ya que agrupa y comprime el estado en un solo lugar (el ViewModel) en lugar de tenerlo disperso en múltiples Composables, lo que elimina inconsistencias y facilita el mantenimiento. 
En conclusion todas las implementaciones que se hicieron fueron para que la UI solo se preocupe de mostrar datos y capturar intenciones del usuario, mientras que el ViewModel maneja la lógica de negocio y el estado.


