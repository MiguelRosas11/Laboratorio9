Reflexión 1: ¿Por qué el estado no persiste al rotar? Relacionar con ciclo de vida, recreación de actividad y recomposición.

En efecto no guarda el valor por el que iba el contador, ya que al rotar se pierde y se reinicia en 0, esto es debido a que al rotar 
Android destruye y recrea la Activity (y con esta tambien la composición), por eso el remember se reinicia. es decir que remember guarda el estado sólo mientras existe la composición actual. Es decir que Al rotar la pantalla Android 
hace un cambio de configuración. Por defecto la Activity se destruye y se vuelve a crear. 

<img width="397" height="889" alt="image" src="https://github.com/user-attachments/assets/815f7af4-5ba7-4331-b3a2-12a2c3d6f0b9" />


y luego se ve como :


<img width="507" height="256" alt="image" src="https://github.com/user-attachments/assets/7994bb92-ac05-4721-8b07-490254f28fce" />



Ahora bien  cuando la Activity se destruye, la composición de Compose se descarta.
Todo lo que estaba guardado con remember (osea lo que vive en la composición) se pierde. Es decir que lo que pasa con la recomposición es que remember si persiste pero como tal hay una recreación de la actividad por lo que una nueva composición  y el remember se reinicia.
