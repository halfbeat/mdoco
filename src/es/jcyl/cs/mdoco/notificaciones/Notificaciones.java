package es.jcyl.cs.mdoco.notificaciones;

import es.jcyl.cs.mdoco.util.JSONSerializable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Notificaciones extends JSONSerializable implements Serializable {
    private List<Notificacion> notificaciones;

    public Notificaciones() {
    }

    public Notificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    public static Notificaciones fromJSON(JSONObject o) throws JSONException {
        JSONArray arr = (JSONArray) o.get("notificaciones");
        ArrayList<Notificacion> notificaciones = new ArrayList<Notificacion>();
        for (int i = 0; i < arr.length(); i++) {
            notificaciones.add(Notificacion.fromJSON(arr.getJSONObject(i)));
        }
        return new Notificaciones(notificaciones);
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("notificaciones", getNotificaciones());
        return json;
    }
}
