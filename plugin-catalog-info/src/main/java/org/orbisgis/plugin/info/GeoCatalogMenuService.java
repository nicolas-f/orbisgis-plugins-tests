package org.orbisgis.plugin.info;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import org.orbisgis.plugin.info.actions.PrintSourceURI;
import org.orbisgis.view.geocatalog.ext.PopupMenu;
import org.orbisgis.view.geocatalog.ext.PopupTarget;

/**
 * Action factory
 * @author Nicolas Fortin
 */
public class GeoCatalogMenuService implements PopupMenu {

    @Override
    public List<Action> createActions(PopupTarget target) {
        List<Action> actions = new ArrayList<Action>();
        actions.add(new PrintSourceURI(target));
        return actions;
    }

    @Override
    public void disposeActions(PopupTarget target, List<Action> actions) {
        //no listeners
    }

}
