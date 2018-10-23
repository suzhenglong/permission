package com.csii.util;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: zhenglongsu@163.com
 * @date: 2018.01.17 14:30
 */
public class LevelUtil {

    public static final String SEPARATOR = ".";

    public static final String ROOT = "0";

    /**
     * 规则:
     * 0
     * 0.1
     * 0.1.2
     * 0.1.3
     * 0.4
     *
     * @param parentLevel
     * @param parentId
     * @return
     */
    public static String calculateLevel(String parentLevel, int parentId) {
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }
    }

}
