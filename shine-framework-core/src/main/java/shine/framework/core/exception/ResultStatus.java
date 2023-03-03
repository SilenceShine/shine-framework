package shine.framework.core.exception;

/**
 * 异常状态码 对照 {@link HttpStatus} 相同的描述 在第二位和第三位直接加入1 其他的业务用5位或者更高的位数
 *
 * @author SilenceShine
 * @since 1.0
 */
public interface ResultStatus {

    /**
     * 正确返回码
     */
    int OK = 200;

    // ------------------------------------------------- 业务异常 -----------------------------------------

    /**
     * 错误请求 header错误 参数错误等等
     */
    int BAD_REQUEST = 4000;

    // ------------------------------------------------- 系统异常 -----------------------------------------

    /**
     * 未知异常错误码
     */
    int SERVER_ERROR = 500;

    /**
     * 调用异常错误码
     */
    int GATEWAY_TIMEOUT = 5004;

}
