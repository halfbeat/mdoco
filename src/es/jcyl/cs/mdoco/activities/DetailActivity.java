package es.jcyl.cs.mdoco.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import es.jcyl.cs.mdoco.R;
import es.jcyl.cs.mdoco.util.ImageAdapter;

public class DetailActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        GridView gridView = (GridView) findViewById(R.id.grid_view);

        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(this));
    }
}