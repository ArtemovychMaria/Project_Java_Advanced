package Project_Java_Advanced.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

public class Bucket {
    private int id;
    private int userId;
    private int productId;
    private Date purchaseDate;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private int userId;
        private int productId;
        private Date purchaseDate;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setProductId(int productId) {
            this.productId = productId;
            return this;
        }

        public Builder setPurchaseDate(Date purchaseDate) {
            this.purchaseDate = purchaseDate;
            return this;
        }

        public Bucket build() {
            Bucket bucket=new Bucket();
            bucket.setId(id);
            bucket.setUserId(userId);
            bucket.setProductId(productId);
            bucket.setPurchaseDate(purchaseDate);
            return bucket;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public static Bucket of(ResultSet resultSet) throws SQLException {
       int id=resultSet.getInt("id");
       int userId=resultSet.getInt("user_id");
       int productId=resultSet.getInt("product_id");
       Date purchaseDate=resultSet.getDate("purchase_date");
        return Bucket.builder()
                .setId(id)
                .setUserId(userId)
                .setProductId(productId)
                .setPurchaseDate(purchaseDate)
                .build();
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", purchaseDate=" + purchaseDate +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Bucket)) return false;
        Bucket bucket = (Bucket) object;
        return getId() == bucket.getId() &&
                getUserId() == bucket.getUserId() &&
                getProductId() == bucket.getProductId() &&
                Objects.equals(getPurchaseDate(), bucket.getPurchaseDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getProductId(), getPurchaseDate());
    }
}
