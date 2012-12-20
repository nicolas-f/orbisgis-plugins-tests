/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.orbisgis.plugin.info.automaton;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import javax.swing.ImageIcon;
import org.apache.log4j.Logger;
import org.gdms.data.DataSourceFactory;
import org.gdms.data.schema.DefaultMetadata;
import org.gdms.data.schema.Metadata;
import org.gdms.data.types.Type;
import org.gdms.data.types.TypeFactory;
import org.gdms.data.values.Value;
import org.gdms.data.values.ValueFactory;
import org.gdms.driver.DriverException;
import org.gdms.driver.driverManager.DriverLoadException;
import org.gdms.driver.gdms.GdmsWriter;
import org.orbisgis.core.DataManager;
import org.orbisgis.core.Services;
import org.orbisgis.core.layerModel.ILayer;
import org.orbisgis.core.layerModel.LayerException;
import org.orbisgis.core.layerModel.MapContext;
import org.orbisgis.core.renderer.se.CompositeSymbolizer;
import org.orbisgis.core.renderer.se.LineSymbolizer;
import org.orbisgis.core.renderer.se.Rule;
import org.orbisgis.core.renderer.se.Style;
import org.orbisgis.core.renderer.se.fill.SolidFill;
import org.orbisgis.core.renderer.se.parameter.color.ColorLiteral;
import org.orbisgis.core.renderer.se.parameter.real.RealLiteral;
import org.orbisgis.core.renderer.se.stroke.PenStroke;
import org.orbisgis.view.icons.OrbisGISIcon;
import org.orbisgis.view.map.tool.ToolManager;
import org.orbisgis.view.map.tool.TransitionException;
import org.orbisgis.view.map.tools.AbstractPolygonTool;

/**
 * Just two points is sufficent to define a rectangular fence.
 * @author Nicolas Fortin
 */
public class RectangularFenceAutomaton  extends AbstractPolygonTool {
        private static Logger UILOGGER = Logger.getLogger("gui."+RectangularFenceAutomaton.class);
        private ILayer layer;
        private String fenceFile = "fence.gdms";
        private final String fenceLayerName = "fence";

        @Override
        public void update(Observable o, Object arg) {
                //PlugInContext.checkTool(this);
        }

        @Override
        protected void polygonDone(Polygon g, MapContext vc, ToolManager tm)
                throws TransitionException {
                try {
                        if (null != layer) {
                                vc.getLayerModel().remove(layer);
                        }

                        layer = createFenceLayer(g);
                        //Create a style and add it to the fence layer
                        Style fenceStyle = new Style(layer, false);
                        CompositeSymbolizer symbolizer = new CompositeSymbolizer();
                        LineSymbolizer symb = new LineSymbolizer();
                        PenStroke ps = (PenStroke) symb.getStroke();
                        ((SolidFill) (ps).getFill()).setColor(new ColorLiteral(Color.ORANGE));
                        ps.setWidth(new RealLiteral(1));
                        symbolizer.addSymbolizer(symb);
                        Rule r = new Rule();
                        r.setCompositeSymbolizer(symbolizer);
                        fenceStyle.addRule(r);
                        layer.addStyle(fenceStyle);

                        vc.getLayerModel().insertLayer(layer, 0);
                } catch (LayerException e) {
                        UILOGGER.error(I18N.tr("Cannot use fence tool"), e);
                }
        }

        @Override
        public boolean isEnabled(MapContext vc, ToolManager tm) {
                return vc.getLayerModel().getLayerCount() > 0;
        }

        @Override
        public boolean isVisible(MapContext vc, ToolManager tm) {
                return true;
        }

        private ILayer createFenceLayer(Geometry g) {
                try {
                        DataSourceFactory dsf = Services.getService(DataManager.class).getDataSourceFactory();



                        File file = new File(dsf.getResultDir() + File.separator + fenceFile);

                        if (file.exists()) {
                                file.delete();
                        }

                        if (dsf.getSourceManager().exists(fenceLayerName)) {
                                dsf.getSourceManager().remove(fenceLayerName);
                        }

                        GdmsWriter writer;
                        writer = new GdmsWriter(file);


                        Metadata md = new DefaultMetadata(new Type[]{TypeFactory.createType(Type.POLYGON)}, new String[]{"the_geom"});

                        writer.writeMetadata(1, md);
                        writer.addValues(new Value[]{ValueFactory.createValue(g)});

                        // write the row indexes
                        writer.writeRowIndexes();
                        // write envelope
                        writer.writeExtent();
                        writer.close();
                        dsf.getSourceManager().register(fenceLayerName, file);

                        DataManager dataManager = Services.getService(DataManager.class);
                        return dataManager.createLayer(fenceLayerName);


                } catch (DriverLoadException e) {
                        UILOGGER.error(I18N.tr("Error while recovering fence vectorial layer"), e);
                } catch (DriverException e) {
                        UILOGGER.error(I18N.tr("Cannot create fence layer"), e);

                } catch (LayerException e) {
                        UILOGGER.error(I18N.tr("Cannot create fence layer"), e);

                } catch (IOException e) {
                        UILOGGER.error(I18N.tr("Cannot create fence layer"), e);
                }
                return null;
        }

        @Override
        public String getName() {
                return I18N.tr("Draw a fence");
        }

        @Override
        public ImageIcon getImageIcon() {
            return OrbisGISIcon.getIcon("shape_polygon_edit");
        }
}