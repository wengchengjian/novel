package io.github.wxj.novel.service;

import io.github.wxj.novel.core.common.resp.RestResp;
import io.github.wxj.novel.dto.resp.HomeBookRespDto;

import java.util.List;

public interface HomeService {
    /**
     * 查询首页小说推荐列表
     *
     * @return 首页小说推荐列表的 rest 响应结果
     * */
    List<HomeBookRespDto> listHomeBooks();
}
