package nju.quadra.hms.model;

/**
 * Created by adn55 on 16/10/15.
 */
public class ResultMessage {

    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_GENERAL_ERROR = 1;
    public static final int RESULT_DB_ERROR = 2;
    public static final int RESULT_NET_ERROR = 3;

    public int result;
    public String message;

    public ResultMessage(int result) {
        this.result = result;
        switch (result) {
            case RESULT_DB_ERROR:
                this.message = "发生数据库访问错误，请稍后重试";
                break;
            case RESULT_NET_ERROR:
                this.message = "发生网络通信错误，请稍后重试";
                break;
            default:
                this.message = "";
        }
    }

    public ResultMessage(int result, String message) {
        this.result = result;
        this.message = message;
    }

    public ResultMessage(Exception e) {
        this.result = RESULT_GENERAL_ERROR;
        this.message = e.getClass().getSimpleName() + ": " + e.getLocalizedMessage();
    }

    @Override
    public String toString() {
        if (result == RESULT_SUCCESS) {
            return "Result: Success, Message: " + message;
        } else {
            return "Result: Error(code=" + result + "), Message: " + message;
        }
    }
}
