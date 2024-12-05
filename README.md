# Simulador del Sistema J.A.R.V.I.S. para la Armadura de Iron Man

Este repositorio contiene una simulación detallada del sistema J.A.R.V.I.S., diseñado para gestionar y operar la armadura de Iron Man. La implementación incluye modelos de dispositivos, gestión de energía, radar, combate, y maniobras evasivas, utilizando conceptos avanzados de programación orientada a objetos en Java.

## Funcionalidades principales:

### Gestión de la Armadura
- **Movimiento:** Caminar, correr, propulsarse y volar, ajustando el consumo energético de las botas y guantes según la intensidad de uso.
- **Combate:** Uso de repulsores en los guantes para atacar enemigos, con consumo energético proporcional al esfuerzo.
- **Diagnóstico:** Monitoreo del estado de cada dispositivo y del reactor con niveles de energía reportados en diferentes unidades.

### Sistema de Reparación
- **Daños:** Cada dispositivo tiene un 30% de probabilidad de dañarse durante el uso.
- **Reparación:** Probabilidad del 40% de reparar dispositivos dañados automáticamente, con detección y reparación insistente.

### Radar Versión 1.0
- **Detección:** Localización de objetos en coordenadas X, Y, Z, con hasta 10 objetos simultáneos, incluyendo clasificación como hostiles o neutrales.
- **Cálculo de Distancia:** Determina la distancia entre la armadura y los objetos usando la clase `Math`.

### Combate y Acciones Evasivas
- **Destrucción de Enemigos:** Análisis de resistencia y distancia de enemigos para decidir ataques. Disparos proporcionales a la distancia.
- **Evasión:** Si la batería cae por debajo del 10%, se detienen los ataques y la armadura se aleja 10 km para evitar ser alcanzada por el enemigo.

## Requisitos:
- **Lenguaje:** Java 8+
- **Entorno de Desarrollo:** NetBeans, Eclipse o IntelliJ
- **Bibliotecas:** `java.util.Random`, `java.lang.Math`

## Instalación y Ejecución
1. Clona este repositorio.
2. Abre el proyecto en tu IDE preferido.
3. Ejecuta las pruebas simulando acciones, movimientos y combate.

## Contribución
Las contribuciones son bienvenidas. Crea un fork del repositorio, implementa mejoras y envía un pull request.

---
© 2024 - Proyecto Simulador J.A.R.V.I.S.
