package com.suzl.common.beans;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Description:
 * @author: zhenglongsu@163.com
 * @date: 2018.01.19 16:23
 */
@Getter
@Setter
@Builder
@ToString
public class PageResult<T> {

    private List<T> data = Lists.newArrayList();

    private int total;
}
