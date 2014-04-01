package es.jcyl.cs.mdoco.security;

import es.jcyl.cs.mdoco.util.JSONSerializable;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Credentials extends JSONSerializable implements Serializable {
    private String user;
    private String password;

    public Credentials() {
    }

    public Credentials(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject jsonCreds = new JSONObject();
        jsonCreds.put("user", getUser());
        jsonCreds.put("password", getPassword());
        return jsonCreds;
    }

    public static Credentials fromJSON(JSONObject o) throws JSONException {
        return new Credentials(o.getString("user"),o.getString("password"));
    }
}
