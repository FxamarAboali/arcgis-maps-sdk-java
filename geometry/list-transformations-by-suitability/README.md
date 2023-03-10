# List transformations by suitability

Get a list of suitable transformations for projecting a geometry between two spatial references with different horizontal datums.

![Image of list transformations by suitability](ListTransformationsBySuitability.png)

## Use case

Transformations (sometimes known as datum or geographic transformations) are used when projecting data from one spatial reference to another when there is a difference in the underlying datum of the spatial references. Transformations can be mathematically defined by specific equations (equation-based transformations), or may rely on external supporting files (grid-based transformations). Choosing the most appropriate transformation for a situation can ensure the best possible accuracy for this operation. Some users familiar with transformations may wish to control which transformation is used in an operation.

## How to use the sample

Select a transformation from the list and click 'Transform' to see the result of projecting the point from EPSG:27700 to EPSG:3857 using that transformation. The result is shown as a red cross; you can visually compare the original blue point with the projected red cross.

Select 'Order by extent suitability' to limit the transformations that are appropriate for the current extent.

If the selected transformation is not usable (has missing grid files) then an error is displayed.

## How it works

1. Pass the input and output spatial references to `TransformationCatalog.getTransformationsBySuitability()` for transformations based on the map's spatial reference OR additionally provide an extent argument to only return transformations suitable to the extent. This returns a list of ranked transformations.
2. Use one of the `DatumTransformation` objects returned to project the input geometry to the output spatial reference.

## Relevant API

* DatumTransformation
* GeometryEngine
* TransformationCatalog

## About the data

The map starts out zoomed into the grounds of the Royal Observatory, Greenwich. The initial point is in the [British National Grid](https://epsg.io/27700) spatial reference, which was created by the United Kingdom Ordnance Survey. The spatial reference after projection is in [web mercator](https://epsg.io/3857).

## Additional information

Some transformations aren't available until transformation data is provided.

`DatumTransformation` has two subclasses, `GeographicTransformation` and `HorizontalVerticalTransformation`. `GeographicTransformation` is used to transform coordinates of geometries between spatial references that have two different geographic coordinate systems. `HorizontalVerticalTransformation` is used to transform coordinates of z-aware geometries between spatial references that have different geographic and/or vertical coordinate systems.

This sample can be used with or without provisioning projection engine data to your device. If you do not provision data, a limited number of transformations will be available.

To download projection engine data to your device:

1. Log in to the [ArcGIS for Developers site](https://developers.arcgis.com/sign-in/) using your Developer account.
2. In the Dashboard page, click '[Downloads](https://developers.arcgis.com/downloads/)' and go to the `Projection Engine Data` tab.
3. Click the download button next to `Projection Engine Data` to download projection engine data to your computer.
4. Unzip the downloaded data on your computer.
5. Create a `resources/pedata` directory on your device if it doesn't already exist in the ArcGIS Maps SDK for Java install directory, and copy the files to this directory.

## Tags

datum, geodesy, projection, spatial reference, transformation
