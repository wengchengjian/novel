package io.github.wxj.novel.controller.front;

import io.github.wxj.novel.core.common.constants.ApiRouterConsts;
import io.github.wxj.novel.core.common.resp.RestResp;
import io.github.wxj.novel.dto.resp.HomeBookRespDto;
import io.github.wxj.novel.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRouterConsts.API_FRONT_HOME_URL_PREFIX)
public class HomeController {
    private final HomeService homeService;

    @RequestMapping("/books")
    public RestResp<List<HomeBookRespDto>> listHomeBook(){

        return RestResp.ok(homeService.listHomeBooks());
    }
}
