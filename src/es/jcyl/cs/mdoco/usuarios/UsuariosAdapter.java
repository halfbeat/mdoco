package es.jcyl.cs.mdoco.usuarios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import es.jcyl.cs.mdoco.R;

import java.util.List;

public class UsuariosAdapter extends ArrayAdapter<Usuario> {
    public UsuariosAdapter(Context context, List<Usuario> usuarios) {
        super(context, R.layout.usuario_list_item, usuarios);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Usuario user = getItem(position);
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.usuario_list_item, null);
            viewHolder.id = (TextView) convertView.findViewById(R.id.usuario_id);
            viewHolder.nif = (TextView) convertView.findViewById(R.id.usuario_nif);
            viewHolder.password = (TextView) convertView.findViewById(R.id.usuario_password);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.id.setText(Long.valueOf(user.getId()).toString());
        viewHolder.nif.setText(user.getNif());
        viewHolder.password.setText(user.getPassword());
        return convertView;
    }

    private static class ViewHolder {
        TextView id;
        TextView nif;
        TextView password;
    }
}
