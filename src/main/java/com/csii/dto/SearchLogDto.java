package com.csii.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class SearchLogDto {
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
    private Date fromTime;
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    private Date toTime;
}
