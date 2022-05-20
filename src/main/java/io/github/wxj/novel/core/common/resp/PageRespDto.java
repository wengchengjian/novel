package io.github.wxj.novel.core.common.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageRespDto<T> {
    /**
     * 页码
     */
    private long pageNum;

    /**
     * 每页大小
     */
    private long pageSize;

    /**
     * 总数
     */
    private long total;

    private List<? extends T> records;

    public  static <T> PageRespDto<T> of(long pageNum,long pageSize,long total,List<? extends T> list){
        return new PageRespDto<>(pageNum,pageSize,total,list);
    }

    /**
     * TODO 存疑 pageNum不就是分页数吗
     * 获取分页数
     * */
    public long getPages() {
        if (this.pageSize == 0L) {
            return 0L;
        } else {
            long pages = this.total / this.pageSize;
            if (this.total % this.pageSize != 0L) {
                ++pages;
            }

            return pages;
        }
    }
}
