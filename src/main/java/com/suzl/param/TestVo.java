package com.suzl.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description:
 * @author: zhenglongsu@163.com
 * @date: 2018.01.16 18:14
 */
@Getter
@Setter
public class TestVo {

    /**
     * 字符串
     */
    @NotBlank
    private String msg;

    @NotNull
    private Integer id;
    /**
     * 数字或对象
     */
    @NotEmpty
    private List<String> list;


}
