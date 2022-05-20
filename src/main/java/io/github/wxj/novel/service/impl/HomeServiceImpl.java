package io.github.wxj.novel.service.impl;

import io.github.wxj.novel.core.common.resp.RestResp;
import io.github.wxj.novel.dto.resp.HomeBookRespDto;
import io.github.wxj.novel.manager.HomeBookCacheManager;
import io.github.wxj.novel.service.HomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 首页模块 服务实现类
 *
 * @author xiongxiaoyang
 * @date 2022/5/13
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class HomeServiceImpl implements HomeService {
    private final HomeBookCacheManager homeBookCacheManager;

    @Override
    public List<HomeBookRespDto> listHomeBooks() {
        return homeBookCacheManager.listHomeBook();
    }
}
