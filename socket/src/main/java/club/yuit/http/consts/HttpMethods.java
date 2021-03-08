package club.yuit.http.consts;

/**
 * @author yuit
 * @date 2021/3/8
 */
public enum HttpMethods {

    /**
     * get
     */
    GET("GET"),
    POST("POST"),
    OPTIONS("OPTIONS"),
    DELETE("DELETE"),
    PUT("PUT");

    public String value;

    private HttpMethods(String value){
        this.value=value;
    }

}
