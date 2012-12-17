/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.orbisgis.plugin.info;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import org.orbisgis.view.map.ext.MapEditorAction;
import org.orbisgis.view.map.ext.MapEditorExtension;

/**
 *
 * @author Nicolas Fortin
 */
public class MapEditorMenuService implements MapEditorAction {

    public List<Action> createActions(MapEditorExtension target) {
        List<Action> actions = new ArrayList<Action>();

        return actions;
    }

    public void disposeActions(MapEditorExtension target, List<Action> actions) {
        //If the current tool is our automaton reset to default automaton
        target.setTool(null);
    }

}
