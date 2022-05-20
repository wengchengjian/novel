package io.github.wxj.novel.core.common.req;

import lombok.Data;

@Data
public class PageReqDto {
    /**
     * 请求页码，默认第一页
     */
    private long pageNum = 1;

    /**
     * 每页大小，默认10条
     */
    private long pageSize = 10;

    /**
     * 是否查询所有，true时，pageNum和pageSize失效
     */
    private boolean fetchAll = false;
}
