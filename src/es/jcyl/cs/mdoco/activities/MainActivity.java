package es.jcyl.cs.mdoco.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import es.jcyl.cs.mdoco.R;
import es.jcyl.cs.mdoco.activities.DetailActivity;
import es.jcyl.cs.mdoco.security.SessionManager;
import es.jcyl.cs.mdoco.util.DAO;

public class MainActivity extends Activity implements View.OnClickListener {
    SessionManager session;
    DAO datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        datasource = new DAO(this);
        datasource.open();
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
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, DetailActivity.class));
    }
}
