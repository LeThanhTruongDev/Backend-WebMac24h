package org.example.web_lap.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotBlank (message = "Không được bỏ trống tên")
    private String username;

    @NotBlank (message = "Mật khẩu không được bỏ trống ")
    @Size (min = 1 , max = 20 , message = "Mật khẩu chỉ từ 1-20 kí tự")
    private String password;

    @NotBlank (message = "Tên không được bỏ trống ")
    @Size (min = 10 , max = 20 , message = "Tên phải từ 10-20 kí tự")
    private String fullName;

    @NotBlank (message = "Số điện thoại không được bỏ trống ")
    @Size (min = 10 , max = 11 , message = "Số điện thoại phải từ 10-11 kí tự")
    private String phoneNumber;

    @NotBlank (message = "Địa chỉ không được bỏ trống ")
    @Size (min = 20 , max = 100 , message = "Địa chỉ phải từ 20-100 kí tự")
    private String address;

    private Boolean isDeleted = false;

    @NotBlank(message = "Vai trò không được bỏ trống")
    private Long roleId;
}
