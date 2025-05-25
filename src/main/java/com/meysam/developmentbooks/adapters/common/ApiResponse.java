package com.meysam.developmentbooks.adapters.common;

import static com.meysam.developmentbooks.common.constants.MessageConstants.Web.SUCCESS;

public record ApiResponse<T>(
        int code,
        T data,
        String msg
) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, data, SUCCESS);
    }

    public static <T> ApiResponse<T> success(T data, String msg) {
        return new ApiResponse<>(200, data, msg);
    }

    public static <T> ApiResponse<T> success(int code, T data, String msg) {
        return new ApiResponse<>(code, data, msg);
    }

    public static <T> ApiResponse<T> error(int code, String msg) {
        return new ApiResponse<>(code, null, msg);
    }


}
