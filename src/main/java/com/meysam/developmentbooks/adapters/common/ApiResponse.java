package com.meysam.developmentbooks.adapters.common;

public record ApiResponse<T>(
        int code,
        T data,
        String msg
) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, data, "OK");
    }

    public static <T> ApiResponse<T> success(T data, String msg) {
        return new ApiResponse<>(200, data, msg);
    }

    public static <T> ApiResponse<T> error(int code, String msg) {
        return new ApiResponse<>(code, null, msg);
    }
}
