package com.suzl.param;

/**
 * @Description:
 * @author: zhenglongsu@163.com
 * @date: 2018.02.01 16:19
 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchLogParam {

    /**
     * LogType
     */
    private Integer type;

    private String beforeSeg;

    private String afterSeg;

    private String operator;
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    private String fromTime;

    private String toTime;
}

