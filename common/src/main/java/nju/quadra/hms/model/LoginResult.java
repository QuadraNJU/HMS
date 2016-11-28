package nju.quadra.hms.model;

/**
 * Created by adn55 on 2016/11/28.
 */
public class LoginResult extends ResultMessage {

    public final LoginSession session;

    public LoginResult(LoginSession session) {
        super(RESULT_SUCCESS);
        this.session = session;
    }

    public LoginResult(int result) {
        super(result);
        this.session = null;
    }

    public LoginResult(int result, String message) {
        super(result, message);
        this.session = null;
    }

}
