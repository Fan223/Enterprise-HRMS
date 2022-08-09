package fan.utils;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Result
 * @Description TODO
 * @Author Fan
 * @Date 2022/5/3 13:23
 * @Version 1.0
 */
@Data
@Builder
public class Result implements Serializable {

    private static final long serialVersionUID = -1L;
    private Integer code;
    private String message;
    private Object data;

    public static Result success(Object data) {
        return Result.builder().code(200).message("操作成功").data(data).build();
    }

    public static Result success(String message, Object data) {
        return Result.builder().code(200).message(message).data(data).build();
    }

    public static Result success(int code, String message, Object data) {
        return Result.builder().code(code).message(message).data(data).build();
    }

    public static Result fail(String message) {
        return Result.builder().code(400).message(message).build();
    }

    public static Result fail(int code, String message) {
        return Result.builder().code(code).message(message).build();
    }

    public static Result fail(int code, String message, Object data) {
        return Result.builder().code(code).message(message).data(data).build();
    }
}
