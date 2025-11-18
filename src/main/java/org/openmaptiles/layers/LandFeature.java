package org.openmaptiles.layers;

import com.onthegomap.planetiler.FeatureCollector;
import com.onthegomap.planetiler.config.PlanetilerConfig;
import com.onthegomap.planetiler.stats.Stats;
import com.onthegomap.planetiler.util.Translations;
import org.openmaptiles.generated.OpenMapTilesSchema;
import org.openmaptiles.generated.Tables;

public class LandFeature implements
    OpenMapTilesSchema.LandFeature,
    Tables.OsmLandFeaturePoint.Handler {

  public LandFeature(Translations translations, PlanetilerConfig config, Stats stats) {}

  @Override
  public void process(Tables.OsmLandFeaturePoint element, FeatureCollector features) {
    String className = element.source().getString("natural");
    if (className == null) className = element.source().getString("geological");
    if (className == null) className = element.source().getString("mountain_pass");
    features.point(LAYER_NAME)
        .setMinZoom(11)
        .setAttr(Fields.NAME, element.name())
        .setAttr(Fields.CLASS, className);
  }
}