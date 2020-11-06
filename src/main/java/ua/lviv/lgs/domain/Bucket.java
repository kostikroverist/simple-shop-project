package ua.lviv.lgs.domain;

import java.util.Date;
import java.util.Objects;

public class Bucket {
    private  Integer id;
    private  Integer user_id;
    private Integer product_id;
    private Date purchaseDate;

    public Bucket(Integer id, Integer user_id, Integer product_id, Date purchaseDate) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.purchaseDate = purchaseDate;
    }

    public Bucket(Integer user_id, Integer product_id, Date purchaseDate) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.purchaseDate = purchaseDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bucket bucket = (Bucket) o;
        return Objects.equals(id, bucket.id) &&
                Objects.equals(user_id, bucket.user_id) &&
                Objects.equals(product_id, bucket.product_id) &&
                Objects.equals(purchaseDate, bucket.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, product_id, purchaseDate);
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", product_id=" + product_id +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
