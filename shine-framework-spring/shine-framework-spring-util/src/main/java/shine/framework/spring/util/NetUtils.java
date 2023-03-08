package shine.framework.spring.util;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.http.HttpHeaders;
import shine.framework.util.log.LogUtil;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * @author SilenceShine
 * @since 1.0
 */
public class NetUtils extends NetUtil {

    private static final String UNKNOWN = "unknown";
    private static final String LOCALHOST = "127.0.0.1";
    private static final String SEPARATOR = ",";
    private static final String HEADER_X_FORWARDED_FOR = "x-forwarded-for";
    private static final String HEADER_PROXY_CLIENT_IP = "Proxy-Client-IP";
    private static final String HEADER_WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    public static String getRealIpAddress(HttpHeaders headers, InetSocketAddress remoteAddress) {
        var ipAddress = getIpAddress(headers);
        return getRealIpAddress(ipAddress, remoteAddress);
    }

    public static String getIpAddress(HttpHeaders headers) {
        String ipAddress;
        // 1.根据常见的代理服务器转发的请求ip存放协议，从请求头获取原始请求ip。值类似于203.98.182.163, 203.98.182.163
        ipAddress = headers.getFirst(HEADER_X_FORWARDED_FOR);
        if (StrUtil.isBlank(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = headers.getFirst(HEADER_PROXY_CLIENT_IP);
        }
        if (StrUtil.isBlank(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = headers.getFirst(HEADER_WL_PROXY_CLIENT_IP);
        }
        return ipAddress;
    }

    /**
     * 获取真实客户端IP
     */
    public static String getRealIpAddress(String ipAddress, InetSocketAddress remoteAddress) {
        try {
            // 1.根据常见的代理服务器转发的请求ip存放协议，从请求头获取原始请求ip。值类似于203.98.182.163, 203.98.182.163
            // 2.如果没有转发的ip，则取当前通信的请求端的ip
            if (StrUtil.isBlank(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                if (remoteAddress != null) {
                    ipAddress = remoteAddress.getAddress().getHostAddress();
                }
                // 如果是127.0.0.1，则取本地真实ip
                if (LOCALHOST.equals(ipAddress)) {
                    InetAddress localAddress = NetUtil.getLocalhost();
                    if (localAddress.getHostAddress() != null) {
                        ipAddress = localAddress.getHostAddress();
                    }
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            // "***.***.***.***"
            if (ipAddress != null) {
                ipAddress = ipAddress.split(SEPARATOR)[0].trim();
            }
        } catch (Exception e) {
            LogUtil.error(NetUtils.class, "解析请求IP失败", () -> LogUtil.params(e.getMessage()));
            return "";
        }
        return ipAddress == null ? "" : ipAddress;
    }

}
