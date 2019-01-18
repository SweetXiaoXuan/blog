package com.mf.feel.utils;

import com.github.pagehelper.PageHelper;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;

@Data
public class Page {

    //当前页
    @Range(min = 1, message = "当前页最小值大于0")
    @NotNull(message = "当前页不为空")
    private Integer pageNum;
    //每页的数量
    @Range(min = 1, message = "每页显示数量最小值大于0")
    @NotNull(message = "每页显示数量不为空")
    private Integer pageSize;

    private String order;

    private String sort;

    public String getSortStr() {
        return order + " " + sort;
    }

    /**
     * 排序
     */
    public void sort() {
        if (!StringUtils.isEmpty(order) && !StringUtils.isEmpty(sort)) {
            PageHelper.orderBy(getSortStr());
        }
    }

    public void sort(String defaultOrder) {
        sort(defaultOrder, null);
    }

    public void sort(String defaultOrder, String defaultSort) {
        if (!StringUtils.isEmpty(order) && !StringUtils.isEmpty(sort)) {
            PageHelper.orderBy(getSortStr());
            return;
        }
        if (StringUtils.isEmpty(defaultSort)) {
            defaultSort = "asc";
        }
        if (!StringUtils.isEmpty(defaultOrder)) {
            order = defaultOrder;
            sort = defaultSort;
            PageHelper.orderBy(getSortStr());
        }
    }
}
