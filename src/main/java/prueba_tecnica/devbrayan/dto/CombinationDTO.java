package prueba_tecnica.devbrayan.dto;

import java.util.List;

public class CombinationDTO {
    private List<String> productNames;
    private double total;

    public CombinationDTO(List<String> productNames, double total) {
        this.productNames = productNames;
        this.total = total;
    }

    public List<String> getProductNames() {
        return productNames;
    }

    public void setProductNames(List<String> productNames) {
        this.productNames = productNames;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
