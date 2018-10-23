package com.csii.common.beans;

import lombok.*;

import java.util.Set;

/**
 * @Description:
 * @author: zhenglongsu@163.com
 * @date: 2018.01.22 10:40
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Mail {

    private String subject;

    private String message;

    private Set<String> receivers;
    
}
