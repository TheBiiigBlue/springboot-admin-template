package com.thebigblue.web.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分页dto")
public class PageDto<T> implements Serializable {

    @ApiModelProperty(value = "当前页码", position = 1)
    private int page;
    @ApiModelProperty(value = "每页显示数量", position = 2)
    private int pageSize;
    @ApiModelProperty(value = "总记录数", position = 3)
    private long total;
    @ApiModelProperty(value = "数据", position = 4)
    private List<T> dataList;

}
