package com.vault.global.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;
    private String code;
    private String message;
    private T data;

    // 데이터 있음
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "SUCCESS", "요청에 성공했습니다.", data);
    }

    // 데이터 없음
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(true,"SUCCESS", "요청에 성공했습니다.", null);
    }

    // 실패
    public static <T> ApiResponse<T> fail(String code, String message) {
        return new ApiResponse<>(false, code, message, null);
    }
}
