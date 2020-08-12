[![Build Status](https://travis-ci.org/GEscandar/TP2-Kahoot.svg?branch=master)](https://travis-ci.org/GEscandar/TP2-Kahoot)
[![codecov](https://codecov.io/gh/GEscandar/TP2-Kahoot/branch/master/graph/badge.svg)](https://codecov.io/gh/GEscandar/TP2-Kahoot)


# TP2 Algoritmos 3: KAHOOT

Trabajo Práctico número 2 de la materia Algoritmos y Programación III de FIUBA

## Grupo 8

* **Integrante 1** - [German Escandar](https://github.com/GEscandar)
* **Integrante 2** - [Javier Zardain](https://github.com/Jaz05)
* **Integrante 3** - [Santiago Guerra](https://github.com/SantiG95)
* **Integrante 4** - [Miroslav Sapunar](https://github.com/MiroslavSapunar)
* **Integrante 5** - [Nicolas De Sousa](https://github.com/Nicodoxia)

Corrector: **{Diego}**

### Pre-requisitos

Listado de software/herramientas necesarias para el proyecto

```
java 11
maven 3.6.0
...
```

## Ejecutando las pruebas

```bash
    mvn test
```

Este comando crea el reporte de cobertura para CI y el reporte HTML que pueden abrir de la siguiente manera:

```bash
    <browser> ./target/site/jacoco/index.html
```

## Ejecutando la aplicación

Se deberá ejecutar el siguiente comando dentro del directorio del proyecto:

```bash
	mvn javafx:run
```

## Diagramas

Diagrama de clases principal del proyecto:

<img src="https://raw.githubusercontent.com/GEscandar/TP2-Kahoot/actualizacion-diagramas-entrega3/docs/out/diagramaClases-Principal-TP2/DiagramaUML-Clases-TP2.png">

Para la clase abstracta ScoreAugmenter, se creó un diagrama de clases en detalle:

<img src="https://raw.githubusercontent.com/GEscandar/TP2-Kahoot/actualizacion-diagramas-entrega3/docs/out/DiagramaClases-DetalleAugmentation/DiagramaUML-DetalleAugmentation-TP2.png">

Lo mismo para el detalle de la clase abstracta Question:

<img src="https://github.com/GEscandar/TP2-Kahoot/blob/actualizacion-diagramas/docs/out/DiagramaClases-DetallePreguntas-TP2/DiagramaClases-DetallePreguntas-TP2.png?raw=true">

Diagrama de Secuencia para calculo de puntaje a un jugador:

<img src="https://raw.githubusercontent.com/GEscandar/TP2-Kahoot/actualizacion-diagramas-entrega3/docs/out/DiagramaSecuencia-ConestarPregunta/Diagrama%20Secuencia%20TP2%20-%20Jugador%20contesta%20una%20pregunta.png">

Ya para el caso del jugador que aplica un multiplicador o exclusividad: 

<img src="https://raw.githubusercontent.com/GEscandar/TP2-Kahoot/actualizacion-diagramas-entrega3/docs/out/DiagramaSecuencia-ConestarPreguntaconAugmenter/Diagrama%20Secuencia%20TP2%20-%20Jugador%20contesta%20una%20pregunta%20con%20augmenter.png">

## Licencia

Este repositorio está bajo la Licencia MIT
