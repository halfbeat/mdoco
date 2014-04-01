package es.jcyl.cs.mdoco.notificaciones;


import com.google.common.base.Objects;
import es.jcyl.cs.mdoco.util.ISO8601DateParser;
import es.jcyl.cs.mdoco.util.JSONSerializable;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Notificacion extends JSONSerializable implements Serializable {
    private java.lang.String titulo;
    private java.lang.String contenido;
    private java.util.Date fechaInicio;
    private java.util.Date fechaFin;

    public Notificacion() {
    }

    public Notificacion(Date fechaInicio, Date fechaFin, String titulo, String contenido) {
        this.contenido = contenido;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.titulo = titulo;
    }

    public static Notificacion fromJSON(JSONObject o) throws JSONException {
        try {
            return new Notificacion(
                    ISO8601DateParser.parse(o.getString("fechaInicio")),
                    o.isNull("fechaFin") ? null : ISO8601DateParser.parse(o.getString("fechaFin")),
                    o.getString("titulo"),
                    o.getString("contenido")
            );
        } catch (ParseException e) {
            throw new JSONException(e.getMessage());
        }
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject jsonCreds = new JSONObject();
        jsonCreds.put("fechaInicio", getFechaInicio());
        jsonCreds.put("fechaFin", getFechaFin());
        jsonCreds.put("titulo", getTitulo());
        jsonCreds.put("contenido", getContenido());
        return jsonCreds;
    }
}
