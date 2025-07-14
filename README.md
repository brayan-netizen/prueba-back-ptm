# 🧪 Proyecto CRUD de Productos - Spring Boot + MySQL

Este proyecto consiste en una API REST desarrollada en Java con Spring Boot que permite realizar operaciones CRUD sobre productos, calcular el valor total del inventario y obtener combinaciones de productos que se pueden comprar con un valor determinado.

---

## ⚙️ Tecnologías utilizadas

-   **Java 24**
-   **Spring Boot 3.x**
-   **Spring Web**
-   **Spring Data JPA**
-   **MySQL**
-   **Lombok (opcional)**
-   **Maven**
-   **MVC (Modelo - Vista - Controlador)**

---

## 🚀 Cómo ejecutar el proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/brayan-netizen/prueba-back-ptm
cd prueba-back-ptm
```

### 2. Crear base de datos en MySQL

Conéctate a tu servidor MySQL y ejecuta lo siguiente:

```sql
CREATE DATABASE crud_app;

USE crud_app;

CREATE TABLE product (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  description TEXT NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  stock INT NOT NULL
);

-- Datos de ejemplo
INSERT INTO product (name, description, price, stock) VALUES
('Camiseta básica', 'Camiseta de algodón 100% para uso diario.', 35000, 20),
('Pantalón jeans', 'Pantalón clásico azul, corte recto.', 98000, 15),
('Zapatos deportivos', 'Zapatos livianos para trotar o caminar.', 120000, 10);
```

### 3. Configurar conexión en `application.properties`

```properties
spring.datasource.url=jdbc:mariadb://localhost:{{ PORT_DB }}/crud_app
spring.jpa.database-platform=org.hibernate.dialect.MariaDBDialect
spring.datasource.username={{ USER_BD }}
spring.datasource.password={{ PASSWORD_BD }}
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
server.port={{ PORT_TO_USE }}
```

### 4. Ejecutar el proyecto

Desde IntelliJ, NetBeans o el de tu preferencia, ejecutar la clase `Application.java` con `@SpringBootApplication`.  
O bien desde consola:

```bash
./mvnw spring-boot:run
```

---

## 🌐 Endpoints de la API

Base URL: `{{ URL_BACKEND }}/api/products`

| Método   | Endpoint                               | Descripción                                                     |
| -------- | -------------------------------------- | --------------------------------------------------------------- |
| `GET`    | `/api/products`                        | Obtener todos los productos                                     |
| `POST`   | `/api/products`                        | Crear un producto nuevo                                         |
| `PUT`    | `/api/products/{id}`                   | Actualizar un producto por ID                                   |
| `DELETE` | `/api/products/{id}`                   | Eliminar un producto por ID                                     |
| `POST`   | `/api/products/batch`                  | Crear varios productos a la vez                                 |
| `GET`    | `/api/products/inventory`              | Obtener valor total del inventario y el producto de mayor valor |
| `GET`    | `/api/products/combinations?max=60000` | Obtener combinaciones de productos que no superen ese valor     |

---

### 🔎 Ejemplo de respuesta `/api/products/combinations?max=60000`

```json
[
	{
		"productNames": ["Camiseta básica", "Gorra clásica"],
		"total": 60000.0
	},
	{
		"productNames": ["Calcetines", "Bufanda", "Gorra clásica"],
		"total": 58000.0
	}
]
```

---

## 📁 Estructura del proyecto (Modelo MVC)

```bash
src/
└── main/
    └── java/
        └── com/tu/paquete/
            ├── controllers/
            │   └── ProductController.java
            ├── dto/
            │   └── CombinationDTO.java
            ├── models/
            │   └── Product.java
            ├── repositories/
            │   └── ProductRepository.java
            ├── services/
            │   ├── ProductService.java
            │   └── impl/ProductServiceImpl.java
            └── Application.java
```

### Explicación rápida:

-   `controllers/`: expone los endpoints de la API.
-   `dto/`: contiene estructuras auxiliares para enviar respuestas más claras (por ejemplo: combinaciones).
-   `models/`: representa las entidades (tablas) de la base de datos.
-   `repositories/`: interfaces JPA para consultar la base de datos.
-   `services/`: lógica de negocio separada de los controladores.

---

## 🔐 CORS habilitado

Para facilitar pruebas desde un frontend, se habilitó CORS globalmente:

---

## 🧪 Prueba desde Postman o frontend

Ejemplo para crear producto:

```POST /api/products

{
  "name": "Gorra clásica",
  "description": "Gorra de algodón unisex.",
  "price": 25000,
  "stock": 30
}
```

---

## 📬 Autor

Desarrollado por [Brayan Ocampo Carmona](https://github.com/brayan-netizen).

---

¡Gracias por revisar este proyecto! Siéntete libre de contribuir o compartir sugerencias.
