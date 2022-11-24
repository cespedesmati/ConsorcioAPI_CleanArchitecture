#### API para que un consorcio pueda administrar edificios, unidades, clientes y reclamos.[Proyecto en desarrollo]

Implementación en Java con Spring Boot, Hibernate, SQL server, Swagger

Clean Architecture, el proyecto esta dividio en las siguientes carpetas:

    *Domain: clases de negocio.

    *Application: Lógica de negocio en servicio, interfaces de repositorio,Dtos y excepciones.

    *Infraestructure : Entidades a persistir, implementación de los repositorios.

    *Api: controladores y middleware para manejo de excepciones.

##### Requerimientos:

- Registrar al usuario que genera el reclamo. Este debe ser propietario o inquilino de una unidad de los edificios administrados.
- Identificarlo como un usuario válido para hacer el reclamo. Si el desperfecto mencionado está relacionado con las partes comunes del edificio, cualquier usuario relacionado al edificio puede cargarlo. Si el reclamo es sobre una unidad en particular el dueño podrá generarlo, a menos que la unidad se encuentre alquilada en cuyo caso solo lo podrá hacer el inquilino.
- Solicitar datos identificatorios del lugar del reclamo. Deberá poder ingresar Edificio, piso, numero de unidad o lugar donde se encuentra el desperfecto o problema a reclamar. Se requiere que el ingreso sea mínimo y cerrado. 
- Ingresar una descripción libre del reclamo. Deberá ser un texto acotado a una cantidad máxima de caracteres a definir.
- Adjuntar una o más fotos del desperfecto o problema. El usuario deberá poder sacar fotos y adjuntarlas al reclamo realizado.
- Recepción de la registración. Una vez que los datos son enviados para su registración, debe devolver a la aplicación el número de reclamo generado para su posterior consulta.
- Consulta de un reclamo generado. A requerimiento cualquier usuario puede ver cuáles son los reclamos existentes en su edificio y su estado.

- Registrar el acceso a la aplicación mediante un usuario y un password para identificar quien es quien realiza las acciones.
- Consultar los reclamos ingresados por los usuarios. Filtrar por nuevos, cerrados, etc.
- Cambiar el estado de un reclamo indicando cuales fueron las medidas tomadas. Los reclamos pueden tener los siguientes estados, nuevo, abierto, en proceso, desestimado, anulado y terminado.
- Administrar la información de los edificios, unidades, propietarios e inquilinos.
- Administrar a los usuarios del sistema, creándolos, asignándoles permisos o 
  modificándolos.
