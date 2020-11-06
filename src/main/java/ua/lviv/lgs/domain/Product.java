package ua.lviv.lgs.domain;

import java.util.Objects;

public class Product {
    private Integer id;
    private  String name;
    private  String description;
    private  Double prise;



    public Product(Integer id, String name, String description, Double prise) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.prise = prise;
    }
    public Product(String name, String description, Double prise) {
        this.name = name;
        this.description = description;
        this.prise = prise;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrise() {
        return prise;
    }

    public void setPrise(Double prise) {
        this.prise = prise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) &&
                name.equals(product.name) &&
                description.equals(product.description) &&
                prise.equals(product.prise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, prise);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", prise=" + prise +
                '}';
    }
}
