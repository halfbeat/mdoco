package es.jcyl.cs.mdoco.detalle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import es.jcyl.cs.mdoco.R;
import es.jcyl.cs.mdoco.security.SessionManager;
import es.jcyl.cs.mdoco.usuarios.Usuario;
import es.jcyl.cs.mdoco.usuarios.UsuariosAdapter;
import es.jcyl.cs.mdoco.usuarios.UsuariosService;
import es.jcyl.cs.mdoco.util.ImageAdapter;

import java.util.List;

public class DetailActivity extends Activity {
    protected SessionManager session;
    private UsuariosService usuariosService;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(new ImageAdapter(this));

        usuariosService = new UsuariosService(this);
        usuariosService.open();
        ListView  usuarios = (ListView) findViewById(R.id.usuarios);
        List<Usuario> dbUsers = usuariosService.obtenerTodosUsuarios();

        UsuariosAdapter adapter = new UsuariosAdapter(this, dbUsers);
        usuarios.setAdapter(adapter);

        ImageButton ib = (ImageButton)findViewById(R.id.addUser);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuariosService.altaUsuario("PEPE", "POPOP");
                List<Usuario> dbUsers = usuariosService.obtenerTodosUsuarios();

                UsuariosAdapter adapter = new UsuariosAdapter(DetailActivity.this, dbUsers);
                ((ListView) findViewById(R.id.usuarios)).setAdapter(adapter);
            }
        });
    }

    @Override
    protected void onResume() {
        usuariosService.open();
        session.checkLogin();
        super.onResume();
    }

    @Override
    protected void onPause() {
        usuariosService.close();
        super.onPause();
    }
}