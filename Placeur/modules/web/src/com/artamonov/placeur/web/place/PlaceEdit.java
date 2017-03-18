package com.artamonov.placeur.web.place;

import com.haulmont.charts.gui.components.map.MapViewer;
import com.haulmont.charts.gui.map.model.GeoPoint;
import com.haulmont.charts.gui.map.model.Marker;
import com.haulmont.cuba.gui.WindowParams;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.artamonov.placeur.entity.Place;
import com.haulmont.cuba.gui.components.CheckBox;
import com.haulmont.cuba.gui.data.Datasource;

import javax.inject.Inject;
import java.util.Map;

public class PlaceEdit extends AbstractEditor<Place> {

    @Inject
    MapViewer map;

    @Inject
    Datasource<Place> placeDs;

    @Inject
    CheckBox markerLock;

    @Override
    public void init(Map<String, Object> params) {
        Place place = (Place) WindowParams.ITEM.getEntity(params);
        if (place.getLatitude() == null) place.setLatitude(50d);
        if (place.getLongitude() == null) place.setLongitude(50d);
        placeDs.addItemPropertyChangeListener(e -> {
            if (e.getItem().getLatitude() == null) e.getItem().setLatitude(50d);
            if (e.getItem().getLongitude() == null) e.getItem().setLongitude(50d);
            GeoPoint position = map.createGeoPoint(e.getItem().getLatitude(), e.getItem().getLongitude());
            map.getMarkers().iterator().next().setPosition(position);
            map.setCenter(position);
        });
        GeoPoint point = map.createGeoPoint(place.getLatitude(), place.getLongitude());
        Marker marker = map.createMarker("location", point, true);
        map.addMarker(marker);
        map.setCenter(marker.getPosition());
        map.addMarkerDragListener(event -> {
            Place editablePlace = getItem();
            GeoPoint position = event.getMarker().getPosition();
            editablePlace.setLatitude(position.getLatitude());
            editablePlace.setLongitude(position.getLongitude());
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