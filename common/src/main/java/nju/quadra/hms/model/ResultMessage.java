package nju.quadra.hms.model;

/**
 * Created by adn55 on 16/10/15.
 */
public class ResultMessage {
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_ERROR = 1;

    public int result;
    public String message;

    public ResultMessage(int result) {
        this.result = result;
        this.message = "";
    }

    public ResultMessage(int result, String message) {
        this.result = result;
        this.message = message;
    }
}
