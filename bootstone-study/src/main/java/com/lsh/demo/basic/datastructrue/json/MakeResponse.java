package com.lsh.demo.basic.datastructrue.json;

import lombok.Data;

/**
 * Created by lsh on 2020-06-11.
 */
@Data
public class MakeResponse {
    private String errno;
    private String errmsg;
    private String statusCode ;

    private Data data;

    @lombok.Data
    public class Data{

        private AccessToken accessToken;
        @lombok.Data
        public class AccessToken{

            private String accessToken;
            private String expire;
        }

    }
}
