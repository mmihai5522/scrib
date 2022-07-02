package com.scrib.scrib.constant;

public class SecurityConstant {

    public static final long EXPIRATION_TIME=518_400_000;

    public static final String TOKEN_PREFIX="Bearer";

    public static final String JWT_TOKEN_HEADER="Jwt-Token";

    public static final String INVALID_TOKEN="Jwt-Invalid";

    public static final String GET_ARRAYS_TOKEN="Get LL-C's";

    public static final String GET_ARRAYS_ADMINISTRATION="User management portal";

    public static final String AUTHORITIES="Authorities";

    public static final String FORBIDDEN_MESSAGE ="Log in first!";

    public static final String ACCESS_DENIES_MESSAGE="Denied permission!";

    public static final String OPTIONS_HTTP_METHOD="OPTIONS";

//    public static final String[] PUBLIC_URLS={"/user/login"
//            ,"/user/register"
//            ,"/user/resetpassword/**"
//            ,"/user/image/**"};

    public static final String[] PUBLIC_URLS={"**"};

}
