# Readme - Laboratorio 05
- Huayhua Hillpa Yourdyy Yossimar

## 2. Diferencias, ventajas y desventajas de las clases QVector y QList.

### Diferencias identificadas

#### Comportamiento por versión de Qt

Si se trabaja con Qt5, las diferencias todavía importan: QVector suele ofrecer mejor rendimiento para elementos grandes y acceso contiguo; QList puede comportarse distinto para tipos grandes (indirección). 
MIT Stuff

Si se trabaja con Qt6, las diferencias son prácticamente nominales: la implementación es la misma/compatible y muchos de los viejos problemas de QList se resolvieron por unificación. 
Qt

#### Almacenamiento en memoria

Qt5: QVector<T> = elementos contiguos. QList<T> = en muchos casos elementos alocados individualmente y punteros en un array. 

Qt6: ambos almacenan elementos de forma contigua, un comportamiento tipo std::vector.

#### Operaciones como prepend / append

Qt5: QList históricamente tenía mejor coste amortizado para prepend en ciertas implementaciones; QVector era más eficiente para crecimiento cuando se requiere memoria contigua.

Qt6: se añadió optimización de prepend a QList (y por tanto a QVector vía alias) para no romper expectativas. 
Qt

#### Compatibilidad y estilo de API

Muchas APIs de Qt históricamente devolvían QList; por eso se tuvo decisión de unificar para facilitar migraciones entre versiones y evitar confusión en la API pública.

### Ventajas y desventajas (prácticas)

### `QVector`

#### Ventajas (Qt5)

- Almacenamiento contiguo → mejor localidad de caché → mejor rendimiento para acceso secuencial/aleatorio.
- Menos sobrecarga al almacenar tipos grandes (evita alocar cada elemento por separado). 

#### Desventajas (Qt5)

- prepend() no era tan eficiente como en QList esto claro antes de las actualizaciones, dependiendo del uso concreto.
- Estado (Qt6): alias de QList — las diferencias previas desaparecen en gran medida. 

### `QList`

#### Ventajas (Qt5)

API cómoda y era la recomendada por la documentación en muchos casos; en ciertas situaciones prepend() tenía mejor coste amortizado.
Para tipos pequeños no siempre era peor; comportamiento dependía de sizeof(T) y declaraciones como Q_DECLARE_TYPEINFO. 

#### Desventajas (Qt5)

- Para tipos no triviales o grandes, podía inducir muchas asignaciones individuales en el heap y pérdida de localidad de referencia → peor rendimiento y mayor uso de memoria. Por eso muchos desarrolladores preferían QVector como "default". 
- Estado (Qt6): implementación unificada, por lo que la mayoría de las desventajas históricas ya no aplican.

 > Referencias

[1] https://www.qt.io/blog/qlist-changes-in-qt-6
[2] https://stuff.mit.edu/afs/athena/software/texmaker_v5.0.2/qt57/doc/qtcore/qvector.html
