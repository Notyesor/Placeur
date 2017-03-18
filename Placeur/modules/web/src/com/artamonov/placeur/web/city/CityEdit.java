package com.artamonov.placeur.web.city;

import com.haulmont.charts.gui.components.map.MapViewer;
import com.haulmont.charts.gui.map.model.GeoPoint;
import com.haulmont.charts.gui.map.model.Marker;
import com.haulmont.cuba.gui.WindowParams;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.artamonov.placeur.entity.City;
import com.haulmont.cuba.gui.components.CheckBox;
import com.haulmont.cuba.gui.data.Datasource;

import javax.inject.Inject;
import java.util.Map;

public class CityEdit extends AbstractEditor<City> {

    @Inject
    MapViewer map;

    @Inject
    Datasource<City> cityDs;

    @Inject
    CheckBox markerLock;

    @Override
    public void init(Map<String, Object> params) {
        City city = (City) WindowParams.ITEM.getEntity(params);
        if (city.getLatitude() == null) city.setLatitude(50d);
        if (city.getLongitude() == null) city.setLongitude(50d);
        cityDs.addItemPropertyChangeListener(e -> {
            if (e.getItem().getLatitude() == null) e.getItem().setLatitude(50d);
            if (e.getItem().getLongitude() == null) e.getItem().setLongitude(50d);
            GeoPoint position = map.createGeoPoint(e.getItem().getLatitude(), e.getItem().getLongitude());
            map.getMarkers().iterator().next().setPosition(position);
            map.setCenter(position);
        });
        GeoPoint point = map.createGeoPoint(city.getLatitude(), city.getLongitude());
        Marker marker = map.createMarker("location", point, true);
        map.addMarker(marker);
        map.setCenter(marker.getPosition());
        map.addMarkerDragListener(event -> {
            City editableCity = getItem();
            GeoPoint position = event.getMarker().getPosition();
            editableCity.setLatitude(position.getLatitude());
            editableCity.setLongitude(position.getLongitude());
        });
        markerLock.addValueChangeListener(e -> {
            boolean value = (boolean) e.getValue();
            if (value) {
                map.getMarkers().iterator().next().setDraggable(false);
            } else {
                map.getMarkers().iterator().next().setDraggable(true);
            }
        });
    }
}