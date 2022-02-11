package com.devtyagi.maalgaadi.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public class Endpoints {

    public static final String BASE_URL = "/api/v1";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class AuthAPI {

        public static final String AUTH_BASE_URL = "/auth";

        public static final String DEALER_LOGIN = AUTH_BASE_URL + "/dealer/login";

        public static final String DEALER_SIGNUP = AUTH_BASE_URL + "/dealer/signup";

        public static final String DRIVER_LOGIN = AUTH_BASE_URL + "/driver/login";

        public static final String DRIVER_SIGNUP = AUTH_BASE_URL + "/driver/signup";

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class DealerAPI {

        public static final String DEALER_BASE_URL = "/dealer";

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class DriverAPI {

        public static final String DRIVER_BASE_URL = "/driver";

    }

}
