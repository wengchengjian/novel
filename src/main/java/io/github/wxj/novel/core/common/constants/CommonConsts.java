package io.github.wxj.novel.core.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class CommonConsts {
    public static final Integer YES = 1;

    public static final Integer NO = 0;

    @Getter
    @AllArgsConstructor
    public enum Sex{
        /**
         * 男
         */
        MALE(0,"男"),

        /**
         * 男
         */
        FEMALE(1,"女");

        private Integer code;

        private String desc;
    }
}
