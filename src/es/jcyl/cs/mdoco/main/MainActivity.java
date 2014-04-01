package es.jcyl.cs.mdoco.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import es.jcyl.cs.mdoco.R;
import es.jcyl.cs.mdoco.detalle.DetailActivity;
import es.jcyl.cs.mdoco.notificaciones.Notificaciones;
import es.jcyl.cs.mdoco.notificaciones.NotificacionesServiceProxy;
import es.jcyl.cs.mdoco.notificaciones.NotiticacionesAdapter;
import es.jcyl.cs.mdoco.security.Credentials;
import es.jcyl.cs.mdoco.security.SessionManager;
import es.jcyl.cs.mdoco.util.AlertDialogManager;

public class MainActivity extends Activity implements View.OnClickListener {
    protected SessionManager session;
    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();
    private Credentials credentials;
    private NotificacionesServiceProxy notificacionesService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        Intent i = getIntent();
        credentials = (Credentials) i.getSerializableExtra("credentials");
        if (credentials == null) {
            session.forceLogin();
        }

        Button btnNotificaciones = (Button) findViewById(R.id.btnNotificaciones);
        btnNotificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificacionesService = new NotificacionesServiceProxy(credentials);
                new ObtenerNotificacionesTask().execute(credentials.getUser());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.getItem(2).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                session.logoutUser();
                return true;
            }
        });
        return true;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, DetailActivity.class));
    }

    private class ObtenerNotificacionesTask extends AsyncTask<String, Integer, Boolean> {
        private String message;
        private String detail;
        private ProgressDialog pdia;
        private Notificaciones notificaciones;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdia = new ProgressDialog(MainActivity.this);
            pdia.setMessage("Obteniendo notificaciones ...");
            pdia.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                notificaciones = notificacionesService.obtenerNotificaciones(params[0]);
                if (notificaciones != null) {
                    return true;
                }
            } catch (Exception e) {
                message = "Login failed..";
                detail = e.getMessage();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            pdia.dismiss();
            if (result) {
                ListView lvNotificaciones = (ListView) findViewById(R.id.lvNotificaciones);
                lvNotificaciones.setAdapter(
                        new NotiticacionesAdapter(MainActivity.this, notificaciones.getNotificaciones())
                );
            } else {
                alert.showAlertDialog(MainActivity.this, message, detail, false);
            }
        }
    }
}
