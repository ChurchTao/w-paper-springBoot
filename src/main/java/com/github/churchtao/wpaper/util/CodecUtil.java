package com.github.churchtao.wpaper.util;

import com.github.churchtao.wpaper.constant.DigestAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;


/**
 * 编码与解码操作工具类
 *
 * @author huangyong
 * @since 1.0
 */
public class CodecUtil {

    private static final Logger logger = LoggerFactory.getLogger(CodecUtil.class);

    /**
     * 将 URL 编码
     */
    public static String encodeURL(String str) {
        String target;
        try {
            target = URLEncoder.encode(str, "utf-8");
        } catch (Exception e) {
            logger.error("编码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将 URL 解码
     */
    public static String decodeURL(String str) {
        String target;
        try {
            target = URLDecoder.decode(str, "utf-8");
        } catch (Exception e) {
            logger.error("解码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }
    /**
     * 获取 UUID（32位）
     */
    public static String createUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * MD5加密，生成16进制MD5字符串<br>
     *
     * @return MD5字符串
     */
    public static String md5(String data) {
        return new Digester(DigestAlgorithm.MD5).digestHex(data);
    }
    /**
     * SHA1加密，生成16进制SHA1字符串<br>
     *
     * @return SHA1字符串
     */
    public static String sha1(String data) {
        return new Digester(DigestAlgorithm.SHA1).digestHex(data);
    }

}
