package com.coldlight.backend.common.utils;

import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.util.Date;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/20 17:21
 * @description 时间工具类
 */
@UtilityClass
public class DateUtils {

    /**
     * 获取当前时间戳（毫秒）
     */
    public static long getCurrentTimestamp() {
        return Instant.now().toEpochMilli();
    }

    /**
     * 将时间戳转换为 Date 对象
     * @param timestamp 毫秒级时间戳
     * @return Date 对象
     */
    public static Date timestampToDate(long timestamp) {
        return Date.from(Instant.ofEpochMilli(timestamp));
    }
}
