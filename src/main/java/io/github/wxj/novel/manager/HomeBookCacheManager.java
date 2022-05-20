package io.github.wxj.novel.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import io.github.wxj.novel.core.constant.CacheConsts;
import io.github.wxj.novel.dao.entity.BookInfo;
import io.github.wxj.novel.dao.entity.HomeBook;
import io.github.wxj.novel.dao.mapper.BookInfoMapper;
import io.github.wxj.novel.dao.mapper.HomeBookMapper;
import io.github.wxj.novel.dto.resp.HomeBookRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HomeBookCacheManager {
    private final HomeBookMapper homeBookMapper;

    private final BookInfoMapper bookInfoMapper;

    @Cacheable(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER,value = CacheConsts.HOME_BOOK_CACHE_NAME)
    public List<HomeBookRespDto> listHomeBook(){
        List<HomeBook> homeBooks = homeBookMapper.selectList(null);

        if(!CollectionUtils.isEmpty(homeBooks)){
            List<Long> bookIds  = homeBooks.stream().map(HomeBook::getBookId).collect(Collectors.toList());

            List<BookInfo> bookInfos = bookInfoMapper.selectList(new LambdaQueryWrapper<BookInfo>().in(BookInfo::getId, bookIds));
            if(!CollectionUtils.isEmpty(bookInfos)){
                Map<Long, BookInfo> bookInfoMap = bookInfos.stream()
                        .collect(Collectors.toMap(BookInfo::getId, Function.identity()));
                return homeBooks.stream().map(v->{
                    BookInfo bookInfo = bookInfoMap.get(v.getBookId());
                    HomeBookRespDto bookRespDto = new HomeBookRespDto();
                    bookRespDto.setBookId(v.getBookId());
                    bookRespDto.setBookName(bookInfo.getBookName());
                    bookRespDto.setPicUrl(bookInfo.getPicUrl());
                    bookRespDto.setAuthorName(bookInfo.getAuthorName());
                    bookRespDto.setBookDesc(bookInfo.getBookDesc());
                    return bookRespDto;
                }).collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }
}
