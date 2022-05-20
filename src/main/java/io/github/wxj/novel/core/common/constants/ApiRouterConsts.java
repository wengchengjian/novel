package io.github.wxj.novel.core.common.constants;

public class ApiRouterConsts {
    public static final String API_PREFIX = "/api";

    public static final String API_FRONT_PREFIX = API_PREFIX +  "/front";

    public static final String API_ADMIN_PREFIX = API_PREFIX + "/admin";

    public static final String API_AUTHOR_PREFIX = API_PREFIX + "/author";

    public static final String HOME_PREFIX = "/home";

    public static final String BOOK_PREFIX = "/book";

    public static final String USER_PREFIX = "/user";

    /**
     * 前台门户首页API请求路径前缀
     */
    public static final String API_FRONT_HOME_URL_PREFIX = API_FRONT_PREFIX + HOME_PREFIX;

    /**
     * 前台门户小说相关API请求路径前缀
     */
    public static final String API_FRONT_BOOK_URL_PREFIX = API_FRONT_PREFIX + BOOK_PREFIX;

    /**
     * 前台门户会员相关API请求路径前缀
     */
    public static final String API_FRONT_USER_URL_PREFIX = API_FRONT_PREFIX + USER_PREFIX;


}
