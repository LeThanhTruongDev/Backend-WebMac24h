package org.example.web_lap.dtos.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailRequest {

    @NotBlank(message = "Tên sản phẩm không được bỏ trống")
    @Size(max = 100, message = "Tên sản phẩm không được vượt quá 100 ký tự")
    private String name;

//    @NotNull(message = "Số lượng không được để trống")
//    @Min(value = 0, message = "Số lượng không được nhỏ hơn 0")
//    @Max(value = 10000, message = "Số lượng không được vượt quá 10,000")
    private Integer quantity;

    @NotNull(message = "Giá sản phẩm không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Giá sản phẩm phải lớn hơn 0")
    @DecimalMax(value = "1000000.0", message = "Giá sản phẩm không được vượt quá 1,000,000")
    private Double price;

    @NotBlank(message = "Trạng thái không được để trống")
    @Size(max = 50, message = "Trạng thái không được vượt quá 50 ký tự")
    private String status;

    @NotBlank(message = "Mã sản phẩm không được để trống")
    @Size(max = 50, message = "Mã sản phẩm không được vượt quá 50 ký tự")
    private String code;

    @Pattern(regexp = "^(http|https)://.*$", message = "URL hình ảnh không hợp lệ")
    private String imageUrl;

    @NotNull(message = "RAM không được để trống")
    @Min(value = 1, message = "RAM ID phải lớn hơn 0")
    private Long ramId;

    @NotNull(message = "Bộ nhớ không được để trống")
    @Min(value = 1, message = "Memory ID phải lớn hơn 0")
    private Long memoryId;

    @NotNull(message = "Kích thước hiển thị không được để trống")
    @Min(value = 1, message = "Display Size ID phải lớn hơn 0")
    private Long displaySizeId;

    @NotNull(message = "Sản phẩm cha không được để trống")
    @Min(value = 1, message = "Product ID phải lớn hơn 0")
    private Long productId;

    @NotNull(message = "Màu sắc không được để trống")
    @Min(value = 1, message = "Color ID phải lớn hơn 0")
    private Long colorId;
}
