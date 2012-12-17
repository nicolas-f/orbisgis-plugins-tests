/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.orbisgis.plugin.info.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.orbisgis.view.components.actions.ActionTools;
import org.orbisgis.view.map.ext.MapEditorAction;
import org.orbisgis.view.map.ext.MapEditorExtension;

/**
 *
 * @author Nicolas Fortin
 */
public class RectangularFence extends AbstractAction {
    private MapEditorExtension mapEditor;

    public RectangularFence(MapEditorExtension mapEditor) {
        this.mapEditor = mapEditor;
        putValue(NAME, "Rectangular fence");
        putValue(SHORT_DESCRIPTION, "Draw a rectangular fence.");
        putValue(ActionTools.INSERT_AFTER_MENUID, MapEditorAction.A_FENCE);
        putValue(ActionTools.PARENT_ID, MapEditorAction.A_DRAWING_GROUP);
    }

    public void actionPerformed(ActionEvent e) {
        //Do things
    }

}
