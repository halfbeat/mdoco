package es.jcyl.cs.mdoco.notificaciones;

import es.jcyl.cs.mdoco.security.Credentials;
import es.jcyl.cs.mdoco.util.RemoteProxy;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class NotificacionesServiceProxy extends RemoteProxy {
    private static final String NOTIFICACIONES_OPERATION = "usuario";
    private static final String NOTIFICACIONES_SERVICE = "notificaciones";
    private final Credentials credentials;

    public NotificacionesServiceProxy(Credentials credentials) {
        this.credentials = credentials;
    }

    public Notificaciones obtenerNotificaciones(String usuarioId) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 5000);
        HttpGet get = new HttpGet(REST_SERVICE_BASE + "/" + NOTIFICACIONES_SERVICE + "/" +
                NOTIFICACIONES_OPERATION + "/" + credentials.getUser());

        httpClient.getCredentialsProvider().setCredentials(
                new AuthScope(SERVER_NAME, SERVER_PORT),
                new UsernamePasswordCredentials(credentials.getUser(), credentials.getPassword()));

        HttpResponse resp = httpClient.execute(get);
        if (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throw new Exception("No se ha podido obtener una respuesta del servidor");
        }
        return Notificaciones.fromJSON(new JSONObject(EntityUtils.toString(resp.getEntity())));
    }
}
