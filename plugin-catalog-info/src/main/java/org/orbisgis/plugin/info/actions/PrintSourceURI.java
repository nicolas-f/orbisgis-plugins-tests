/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.orbisgis.plugin.info.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.apache.log4j.Logger;
import org.gdms.driver.DriverException;
import org.gdms.source.Source;
import org.orbisgis.core.DataManager;
import org.orbisgis.core.Services;
import org.orbisgis.view.components.actions.ActionTools;
import org.orbisgis.view.geocatalog.ext.PopupMenu;
import org.orbisgis.view.geocatalog.ext.PopupTarget;

/**
 *
 * @author Nicolas Fortin
 */
public class PrintSourceURI extends AbstractAction {
    PopupTarget target;
    private static final Logger GUI_LOGGER = Logger.getLogger("gui."+PrintSourceURI.class);

    public PrintSourceURI(PopupTarget target) {
        super("Print source URI");
        this.target = target;
        putValue(ActionTools.INSERT_AFTER_MENUID, PopupMenu.M_OPEN_ATTRIBUTES);
    }

    @Override
    public boolean isEnabled() {
        return !target.getListSelectionModel().isSelectionEmpty();
    }

    public void actionPerformed(ActionEvent e) {
        DataManager dm = Services.getService(DataManager.class);
        String[] sources = target.getSelectedSources();
        StringBuilder str = new StringBuilder();
        for(String sourceName : sources) {
            str.append(sourceName);
            str.append(" :\n");
            Source src = dm.getSourceManager().getSource(sourceName);
            if(src!=null) {
                try {
                    str.append(src.getURI().toString());
                    str.append("\n");
                } catch(DriverException ex) {
                    GUI_LOGGER.error(ex.getLocalizedMessage(),ex);
                }
            }
        }
        GUI_LOGGER.info(str.toString());
    }

}
