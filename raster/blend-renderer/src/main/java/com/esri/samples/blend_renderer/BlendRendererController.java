/*
 * Copyright 2017 Esri.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.esri.samples.blend_renderer;

import java.io.File;
import java.util.Collections;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;

import com.esri.arcgisruntime.layers.RasterLayer;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.raster.BlendRenderer;
import com.esri.arcgisruntime.raster.ColorRamp;
import com.esri.arcgisruntime.raster.Raster;
import com.esri.arcgisruntime.raster.SlopeType;

public class BlendRendererController {

  @FXML private MapView mapView;
  @FXML private ComboBox<SlopeType> slopeTypeComboBox;
  @FXML private ComboBox<ColorRamp.PresetType> colorRampComboBox;
  @FXML private Slider azimuthSlider;
  @FXML private Slider altitudeSlider;

  private String imageryRasterPath;
  private String elevationRasterPath;

  public void initialize() {

    // create rasters
    imageryRasterPath = new File(System.getProperty("data.dir"), "./samples-data/raster/Shasta.tif").getAbsolutePath();
    elevationRasterPath = new File(System.getProperty("data.dir"), "./samples-data/raster/Shasta_Elevation.tif").getAbsolutePath();

    // create and load a raster layer
    RasterLayer rasterLayer = new RasterLayer(new Raster(imageryRasterPath));
    // when the raster has loaded create a basemap from it and create a new ArcGISMap with the basemap
    rasterLayer.addDoneLoadingListener(() -> {
      if (rasterLayer.getLoadStatus() == LoadStatus.LOADED) {
        Basemap basemap = new Basemap(rasterLayer);
        ArcGISMap map = new ArcGISMap(basemap);
        // set the map to the map view
        mapView.setMap(map);
        updateRenderer();
      }
    });
    rasterLayer.loadAsync();

    // set defaults
    colorRampComboBox.getItems().setAll(ColorRamp.PresetType.values());
    colorRampComboBox.getSelectionModel().select(ColorRamp.PresetType.NONE);
    slopeTypeComboBox.getItems().setAll(SlopeType.values());
    slopeTypeComboBox.getSelectionModel().select(SlopeType.NONE);

    // add listeners
    altitudeSlider.valueChangingProperty().addListener(o -> {
      if (!altitudeSlider.isValueChanging()) {
        updateRenderer();
      }
    });
    azimuthSlider.valueChangingProperty().addListener(o -> {
      if (!azimuthSlider.isValueChanging()) {
        updateRenderer();
      }
    });

  }

  /**
   * Updates the raster layer renderer according to the chosen property values.
   */
  public void updateRenderer() {

    ColorRamp colorRamp;
    // if the color ramp selection is none, don't apply a color ramp otherwise apply the selected color ramp
    if (colorRampComboBox.getSelectionModel().getSelectedItem() == ColorRamp.PresetType.NONE) {
      colorRamp = null;
    } else {
      colorRamp = new ColorRamp(colorRampComboBox.getSelectionModel().getSelectedItem(), 800);
    }

    // if color ramp is NONE, use the satellite imagery raster color instead of coloring the hillshade elevation raster
    RasterLayer rasterLayer;
    if (colorRamp == null) {
      rasterLayer = new RasterLayer(new Raster(imageryRasterPath));
    } else {
      rasterLayer = new RasterLayer(new Raster(elevationRasterPath));
    }
    // set the basemap to the raster layer
    mapView.getMap().setBasemap(new Basemap(rasterLayer));

    // create blend renderer and set it to the raster layer
    BlendRenderer blendRenderer = new BlendRenderer(new Raster(elevationRasterPath), Collections.singletonList(9.0),
      Collections.singletonList(255.0), null, null, null, null, colorRamp, altitudeSlider.getValue(),
      azimuthSlider.getValue(), 1, slopeTypeComboBox.getSelectionModel().getSelectedItem(), 1, 1, 8);

    rasterLayer.setRasterRenderer(blendRenderer);
  }

  /**
   * Stops and releases all resources used in application.
   */
  void terminate() {

    if (mapView != null) {
      mapView.dispose();
    }
  }

}
