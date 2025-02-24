package org.example.web_lap.security;

public class Endpoints {
    public static final String[] PRIVATE_ADMIN_POST = {
            "/api/v1/roles",
            "/api/v1/color"
    };

    public static final String[] PUBLIC_POST = {
            "/api/v1/user/login",
            "/api/v1/user",
            "/api/v1/color",
            "/api/v1/product",
    };

    public static final String[]  PRIVATE_ADMIN_DELETE = {
            "/api/v1/user/login",
            "/api/v1/user",
            "/api/v1/color/**"
    };

    public static final String[]  PRIVATE_ADMIN_PUT = {
            "/api/v1/user/login",
            "/api/v1/user",
            "/api/v1/color/**"
    };


}
