package es.jcyl.cs.mdoco.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import es.jcyl.cs.mdoco.R;
import es.jcyl.cs.mdoco.detalle.DetailActivity;
import es.jcyl.cs.mdoco.security.SessionManager;

public class MainActivity extends Activity implements View.OnClickListener {
    protected SessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
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
}
