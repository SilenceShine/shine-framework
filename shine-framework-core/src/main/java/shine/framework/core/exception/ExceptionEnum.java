package shine.framework.core.exception;

/**
 * @author SilenceShine
 * @since 1.0
 */
public interface ExceptionEnum {

    /**
     * 业务异常状态码
     *
     * @return 业务异常状态码
     */
    Integer code();

    /**
     * 业务异常错误信息
     *
     * @return 业务异常错误信息
     */
    String message();

}
