package shine.framework.core.util;

import shine.framework.core.dto.R;
import shine.framework.util.json.JacksonUtils;
import shine.framework.util.log.LogUtil;
import shine.framework.util.net.RetryUtil;

import java.util.function.Supplier;

import static shine.framework.core.exception.ResultStatus.OK;
import static shine.framework.core.exception.ResultStatus.SERVER_ERROR;

/**
 * @author SilenceShine
 * @since 1.0
 */
public class HttpUtil {

    private static final Integer RETRY_TIME = 3;
    private static final String TEMPLATE_ERROR = "远程调用[%s]接口失败, 原因: %s";
    private static final String NOT_CONNECT = "远程服务无法连接";
    private static final String RETURN_NULL = "调用接口成功,但返回值为空";

    /**
     * 默认消息构建
     */
    private static String format(String message, String cause) {
        String format = String.format(TEMPLATE_ERROR, message, cause);
        LogUtil.error(HttpUtil.class, "{}", () -> LogUtil.params(format));
        return format;
    }

    /**
     * 远程查询操作是否成功 3次重试机会
     *
     * @param hint          远程调用提示
     * @param rSupplier     远程返回值提供方法
     * @param valueNotEmpty 是否验证value不为空
     * @param <T>           返回值类型
     * @return 返回值
     */
    public static <T> T validQuery(String hint, Supplier<R<T>> rSupplier, boolean valueNotEmpty) {
        return validCommand(hint, () -> RetryUtil.retry(rSupplier, RETRY_TIME, r -> r.getCode() == OK), valueNotEmpty);
    }

    /**
     * 远程调用命令是否成功
     *
     * @param hint          远程调用提示
     * @param rSupplier     远程返回值提供方法
     * @param valueNotEmpty 是否验证value不为空
     * @param <T>           返回值类型
     * @return 返回值
     */
    public static <T> T validCommand(String hint, Supplier<R<T>> rSupplier, boolean valueNotEmpty) {
        R<T> r = rSupplier.get();
        AssertsUtil.isTrue(null != r, SERVER_ERROR, () -> format(hint, NOT_CONNECT));
        AssertsUtil.isTrue(r.getCode() == OK, SERVER_ERROR, r::getMessage);
        if (valueNotEmpty) AssertsUtil.isNotNull(r.getData(), () -> format(hint, RETURN_NULL));
        LogUtil.debug(HttpUtil.class, "远程访问结果:{}", () -> LogUtil.params(JacksonUtils.toJson(r)));
        return r.getData();
    }

}
