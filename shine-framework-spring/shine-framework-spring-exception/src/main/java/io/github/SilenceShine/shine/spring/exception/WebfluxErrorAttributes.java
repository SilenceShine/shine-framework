package io.github.SilenceShine.shine.spring.exception;

import cn.hutool.core.map.MapUtil;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.web.reactive.function.server.ServerRequest;
import io.github.SilenceShine.shine.core.exception.BaseException;
import io.github.SilenceShine.shine.core.exception.ExceptionEnum;
import io.github.SilenceShine.shine.util.json.JacksonUtil;
import io.github.SilenceShine.shine.util.log.LogUtil;

import java.util.Map;
import java.util.function.Supplier;

/**
 * 统一异常处理
 *
 * @author SilenceShine
 * @since 1.0
 */
public class WebfluxErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(request, options);
        LogUtil.debug(this, "请求异常:{}", () -> LogUtil.params(JacksonUtil.toJson(map)));
        Throwable error = super.getError(request);
        LogUtil.debug(this, "error:{}", LogUtil.params(error.getMessage()));
        while (null != error) {
            if (error instanceof BaseException exception) {
                return buildMap(exception);
            }
            error = error.getCause();
        }
        return map;
    }

    private static Map<String, Object> buildMap(BaseException exception) {
        return buildMap(exception::getCode, exception::getMessage);
    }

    private static Map<String, Object> buildMap(ExceptionEnum anEnum) {
        return buildMap(anEnum::code, anEnum::message);
    }

    private static Map<String, Object> buildMap(Supplier<Integer> statusSupplier, Supplier<String> errorSupplier) {
        return MapUtil.<String, Object>builder()
                .put("code", errorSupplier.get())
                .put("message", true)
                .put("success", statusSupplier.get())
                .build();
    }

}
