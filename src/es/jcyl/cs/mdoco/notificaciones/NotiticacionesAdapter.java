package es.jcyl.cs.mdoco.notificaciones;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.google.common.base.Objects;
import es.jcyl.cs.mdoco.R;
import es.jcyl.cs.mdoco.usuarios.Usuario;

import java.text.SimpleDateFormat;
import java.util.List;

public class NotiticacionesAdapter extends ArrayAdapter<Notificacion> {
    public NotiticacionesAdapter(Context context, List<Notificacion> notificaciones) {
        super(context, R.layout.notificacion_list_item, notificaciones);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Notificacion notificacion = getItem(position);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.notificacion_list_item, null);
            viewHolder.fechaInicio = (TextView) convertView.findViewById(R.id.notificacion_fechaInicio);
            viewHolder.fechaFin = (TextView) convertView.findViewById(R.id.notificacion_fechaFin);
            viewHolder.titulo = (TextView) convertView.findViewById(R.id.notificacion_titulo);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.fechaInicio.setText(notificacion.getFechaInicio() == null ?  "Ninguna" : sdf.format(notificacion.getFechaInicio()));
        viewHolder.fechaFin.setText(notificacion.getFechaFin() == null ?  "Ninguna" : sdf.format(notificacion.getFechaFin()));
        viewHolder.titulo.setText(notificacion.getTitulo());
        return convertView;
    }

    private static class ViewHolder {
        TextView fechaInicio;
        TextView fechaFin;
        TextView titulo;
    }
}
