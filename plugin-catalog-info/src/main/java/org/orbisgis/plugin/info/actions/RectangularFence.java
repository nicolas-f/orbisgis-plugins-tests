/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.orbisgis.plugin.info.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.orbisgis.plugin.info.automaton.RectangularFenceAutomaton;
import org.orbisgis.view.components.actions.ActionTools;
import org.orbisgis.view.map.ext.AutomatonHolder;
import org.orbisgis.view.map.ext.MapEditorAction;
import org.orbisgis.view.map.ext.MapEditorExtension;
import org.orbisgis.view.map.tool.Automaton;

/**
 *
 * @author Nicolas Fortin
 */
public class RectangularFence extends AbstractAction implements AutomatonHolder {
    private MapEditorExtension mapEditor;
    private RectangularFenceAutomaton automaton = new RectangularFenceAutomaton();

    public RectangularFence(MapEditorExtension mapEditor) {
        this.mapEditor = mapEditor;
        putValue(NAME, "Rectangular fence");
        putValue(SHORT_DESCRIPTION, "Draw a rectangular fence.");
        putValue(ActionTools.INSERT_AFTER_MENUID, MapEditorAction.A_FENCE);
        putValue(ActionTools.PARENT_ID, MapEditorAction.A_DRAWING_GROUP);
        putValue(ActionTools.TOGGLE_GROUP, MapEditorAction.TOGGLE_GROUP_AUTOMATONS);
    }

    public void actionPerformed(ActionEvent e) {
        if(getValue(Action.SELECTED_KEY).equals(Boolean.TRUE) && mapEditor.getToolManager()!=null) {
            mapEditor.getToolManager().setTool(automaton);
        }
    }

    public Automaton getAutomaton() {
        return automaton;
    }

}
