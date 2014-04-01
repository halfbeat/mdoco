package es.jcyl.cs.mdoco.security;

import es.jcyl.cs.mdoco.util.RemoteProxy;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class SecurityServiceProxy extends RemoteProxy {
    private static final String LOGIN_OPERATION = "login";
    private static final String SECURITY_SERVICE = "security";

    public LoginResult login(Credentials creds) throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 5000);
        HttpPost post = new HttpPost(REST_SERVICE_BASE + "/" + SECURITY_SERVICE + "/" + LOGIN_OPERATION);
        post.setHeader("content-type", "application/json");
        post.setHeader("accept", "application/json");
        StringEntity entity = new StringEntity(creds.toJSON().toString());
        post.setEntity(entity);
        HttpResponse resp = httpClient.execute(post);
        if (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throw new Exception("No se ha podido obtener una respuesta del servidor");
        }
        return LoginResult.fromJSON(new JSONObject(EntityUtils.toString(resp.getEntity())));
    }
}
