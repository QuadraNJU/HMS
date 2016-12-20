package nju.quadra.hms.model;

public class ResultMessage {

    public static final int RESULT_ACCESS_DENIED = -1;
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_GENERAL_ERROR = 1;
    public static final int RESULT_DB_ERROR = 2;
    public static final int RESULT_NET_ERROR = 3;
    public static final int RESULT_DATA_INVALID = 4;

    public final int result;
    public final String message;

    public ResultMessage(int result) {
        this.result = result;
        switch (result) {
            case RESULT_ACCESS_DENIED:
                this.message = "非法访问";
                break;
            case RESULT_DB_ERROR:
                this.message = "发生数据库访问错误，请稍后重试";
                break;
            case RESULT_NET_ERROR:
                this.message = "发生网络通信错误，请稍后重试";
                break;
            case RESULT_DATA_INVALID:
                this.message = "输入内容不完整，请重新输入";
                break;
            default:
                this.message = "";
        }
    }

    public ResultMessage(int result, String message) {
        this.result = result;
        this.message = message;
    }

    public ResultMessage(String message) {
        this.result = RESULT_GENERAL_ERROR;
        this.message = message;
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
