package es.jcyl.cs.mdoco.security;

import es.jcyl.cs.mdoco.util.JSONSerializable;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginResult extends JSONSerializable {
    private int code;
    private String message;
    private String sessionId;

    public LoginResult() {
    }

    public LoginResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject jsonCreds = new JSONObject();
        jsonCreds.put("code", getCode());
        jsonCreds.put("message", getMessage());
        return jsonCreds;
    }

    public static LoginResult fromJSON(JSONObject o) throws JSONException {
        return new LoginResult(o.getInt("code"),o.getString("message"));
    }
}
