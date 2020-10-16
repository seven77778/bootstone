package biz.limit.exception;

public class RateLimitException extends RuntimeException {

    public RateLimitException() {
        super("stone rate limit");
    }

    public RateLimitException(String str) {
        super(str);
    }

}
