package org.example.web_lap.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "is_deleted = false")
@Table(name = "product_detail")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "status")
    private String status;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnoreProperties("productDetails")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ram_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Ram ram;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memory_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Memory memory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "display_size_id") // Sửa lỗi tên cột
    @NotFound(action = NotFoundAction.IGNORE)
    private DisplaySize displaySize; // Sửa lỗi chính tả

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id") // Sửa lỗi tên cột
    @NotFound(action = NotFoundAction.IGNORE)
    private Color color;

}

