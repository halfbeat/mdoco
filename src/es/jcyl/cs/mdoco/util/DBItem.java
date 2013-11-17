package es.jcyl.cs.mdoco.util;

import android.database.Cursor;

public abstract class DBItem {
    protected DBItem() {
    }

    protected DBItem(Cursor cursor) {
        buildFrom(cursor);
    }

    abstract void buildFrom(Cursor cursor);
}
