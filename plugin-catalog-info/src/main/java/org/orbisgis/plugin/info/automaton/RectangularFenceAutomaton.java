/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.orbisgis.plugin.info.automaton;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Observable;
import javax.swing.ImageIcon;
import org.orbisgis.core.layerModel.MapContext;
import org.orbisgis.view.map.tool.Automaton;
import org.orbisgis.view.map.tool.DrawingException;
import org.orbisgis.view.map.tool.FinishedAutomatonException;
import org.orbisgis.view.map.tool.NoSuchTransitionException;
import org.orbisgis.view.map.tool.ToolManager;
import org.orbisgis.view.map.tool.TransitionException;

/**
 *
 * @author Nicolas Fortin
 */
public class RectangularFenceAutomaton implements Automaton {

    public void init(MapContext vc, ToolManager tm) throws TransitionException, FinishedAutomatonException {

    }

    public String[] getTransitionLabels() {

    }

    public Code[] getTransitionCodes() {
        
    }

    public void transition(Code code) throws NoSuchTransitionException, TransitionException, FinishedAutomatonException {

    }

    public void draw(Graphics g) throws DrawingException {

    }

    public String getTooltip() {

    }

    public String getName() {

    }

    public ImageIcon getImageIcon() {

    }

    public ImageIcon getCursor() {

    }

    public boolean isEnabled(MapContext vc, ToolManager tm) {

    }

    public boolean isVisible(MapContext vc, ToolManager tm) {

    }

    public void toolFinished(MapContext vc, ToolManager tm) throws NoSuchTransitionException, TransitionException, FinishedAutomatonException {

    }

    public Point getHotSpotOffset() {

    }

    public void update(Observable o, Object arg) {

    }

}
