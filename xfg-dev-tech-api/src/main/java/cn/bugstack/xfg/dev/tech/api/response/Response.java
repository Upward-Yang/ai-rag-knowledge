package cn.bugstack.xfg.dev.tech.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: yangjian
 * @Date: 2025-10-14 - 10 - 14 - 17:25
 * @Description: cn.bugstack.xfg.dev.tech.api.response
 * @version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {
    private String code;
    private String info;
    private T data;
}
