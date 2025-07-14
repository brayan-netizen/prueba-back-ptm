package prueba_tecnica.devbrayan.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres.")
    private String name;


    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "La descripción es obligatoria.")
    @Size(min = 5, message = "La descripción debe tener al menos 5 caracteres.")
    private String description;

    @Column(nullable = false)
    @NotNull(message = "El precio es obligatorio.")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0.")
    private Double price;

    @Column(nullable = false)
    @NotNull(message = "El stock es obligatorio.")
    @Min(value = 1, message = "El stock debe ser al menos 1.")
    private Integer stock;
}
