package nju.quadra.hms.model;

public class LoginSession {

    /**
     * 会话 id
     */
    public final String id;

    /**
     * 会话的用户名
     */
    public final String username;

    /**
     * 会话的用户类型
     */
    public final UserType userType;

    public LoginSession(String id, String username, UserType userType) {
        this.id = id;
        this.username = username;
        this.userType = userType;
    }

}
