# Export vector tiles

Export tiles from an online vector tile service.

![Image of export vector tiles](ExportVectorTiles.png)

## Use case

Field workers with limited network connectivity can use exported vector tiles as a basemap for use while offline.

## How to use the sample

When the vector tiled layer loads, zoom in to the extent you want to export. The red box shows the extent that will be exported. Click the "Export Vector Tiles" button to start the job. An error will show if the extent is larger than the maximum limit allowed. When finished, a dialog will show the exported result in a new map view.

## How it works

1. Create an `ExportVectorTilesTask`, passing in the URI of the vector tiled layer.
2. Create default `ExportVectorTilesParameters` from the task, specifying extent and maximum scale.
3. Create a `ExportVectorTilesJob` from the task using the parameters, and specifying a vector tile cache path and an item resource path. The resource path is required if you want to export the tiles with the style.
4. Start the job, and once it completes successfully, get the resulting `ExportVectorTilesResult`.
5. Get the `VectorTileCache` and `ItemResourceCache` from the result to create an `ArcGISVectorTiledLayer` that can be displayed to the map view.

## Relevant API

* ArcGISVectorTiledLayer
* ExportVectorTilesJob
* ExportVectorTilesParameters
* ExportVectorTilesResult
* ExportVectorTilesTask
* ItemResourceCache
* VectorTileCache

## Additional information

Vector tiles have high drawing performance and smaller file size compared to regular tiled layers, due to consisting solely of points, lines, and polygons. However, in the ArcGIS Maps SDKs for Native Apps, they cannot be displayed in scenes.

## Tags

cache, download, offline, vector
