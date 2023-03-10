/*
 * Copyright 2022 Esri.
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

package com.esri.samples.add_features_with_contingent_values;

import javafx.scene.control.ListCell;

import com.esri.arcgisruntime.data.CodedValue;

/**
 * Shows the name of the Coded Value in the attribute selection ComboBox.
 */
public class CodedValueListCell extends ListCell<CodedValue> {
  @Override
  protected void updateItem(CodedValue item, boolean empty) {
    super.updateItem(item, empty);
    if (empty) {
      setText(null);
    } else {
      setText(item.getName());
    }
  }
}
