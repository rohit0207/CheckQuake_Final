package com.example3.checkquake.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example3.checkquake.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class Custom_info_window implements GoogleMap.InfoWindowAdapter {
    public View view;
    public LayoutInflater layoutInflater;
    public Context context;

    public Custom_info_window(Context context ) {
        this.context=context;

        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.custom_info_window,null);
    }

    @Override
    public View getInfoWindow(Marker marker) {

        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {

        TextView title = (TextView)view.findViewById(R.id.winTitle);
        title.setText(marker.getTitle());
        TextView magnitude= (TextView)view.findViewById(R.id.magnitude);
        magnitude.setText(marker.getSnippet());
        return view;
    }
}
