package es.jcyl.cs.mdoco.util;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class JSONSerializable {
    public abstract JSONObject toJSON() throws JSONException;

    public static <T> T fromJSON(JSONObject o) throws JSONException {
        throw new IllegalAccessError("No implementado");
    }
}
