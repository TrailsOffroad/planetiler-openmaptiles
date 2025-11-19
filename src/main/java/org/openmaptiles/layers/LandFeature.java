package org.openmaptiles.layers;

import static org.openmaptiles.util.Utils.nullIfEmpty;

import com.onthegomap.planetiler.FeatureCollector;
import com.onthegomap.planetiler.config.PlanetilerConfig;
import com.onthegomap.planetiler.stats.Stats;
import com.onthegomap.planetiler.util.Translations;
import org.openmaptiles.generated.OpenMapTilesSchema;
import org.openmaptiles.generated.Tables;
import org.openmaptiles.util.OmtLanguageUtils;

public class LandFeature implements
    OpenMapTilesSchema.LandFeature,
    Tables.OsmLandFeaturePoint.Handler,
    Tables.OsmLandFeatureLinestring.Handler
{

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

  @Override
  public void process(Tables.OsmLandFeatureLinestring element, FeatureCollector features) {
    String className = element.source().getString("natural");
    if (className == null) className = element.source().getString("geological");
    if (className == null) className = element.source().getString("mountain_pass");
    features.line(LAYER_NAME)
        .setAttr(Fields.NAME, element.name())
        .setAttr(Fields.CLASS, className)
        .setMinZoom(13)
        .setBufferPixels(100);
  }
}