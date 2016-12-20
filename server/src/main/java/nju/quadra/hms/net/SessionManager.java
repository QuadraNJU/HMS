package nju.quadra.hms.net;

import nju.quadra.hms.model.LoginSession;

import java.util.HashMap;

class SessionManager {

    private static final HashMap<String, LoginSession> sessions = new HashMap<>();

    public static void add(LoginSession session) {
        sessions.put(session.id, session);
    }

    public static LoginSession get(String sessid) {
        return sessions.get(sessid);
    }

    public static boolean has(String sessid) {
        return sessions.containsKey(sessid);
    }

}
