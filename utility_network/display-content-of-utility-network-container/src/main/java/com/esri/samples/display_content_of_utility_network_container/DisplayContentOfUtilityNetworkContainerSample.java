/*
 * Copyright 2021 Esri.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.esri.samples.display_content_of_utility_network_container;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DisplayContentOfUtilityNetworkContainerSample extends Application {

  private DisplayContentOfUtilityNetworkContainerController controller;

  @Override
  public void start(Stage stage) throws Exception {

    // set up the scene
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/display_content_of_utility_network_container/main.fxml"));
    Parent root = loader.load();
    controller = loader.getController();
    Scene scene = new Scene(root);

    // set title, size, and add scene to stage
    stage.setTitle("Display Content of Utility Network Container Sample");
    stage.setWidth(800);
    stage.setHeight(700);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Stops and releases all resources used in application.
   */
  @Override
  public void stop() {
    controller.terminate();
  }

  /**
   * Opens and runs application.
   *
   * @param args arguments passed to this application
   */
  public static void main(String[] args) {

    Application.launch(args);
  }
}
