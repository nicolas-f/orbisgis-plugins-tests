package org.orbisgis.plugin.info;

import org.orbisgis.view.geocatalog.ext.PopupMenu;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
/**
 * Registers services provided by this plugin bundle.
 */
public class Activator implements BundleActivator {
        /**
         * Starting bundle, register services.
         * @param bc
         * @throws Exception
         */
        @Override
        public void start(BundleContext bc) throws Exception {
                bc.registerService(PopupMenu.class.getName(),
                        new GeoCatalogMenuService(),
                        null);
        }

        /**
         * Called before the bundle is unloaded.
         * @param bc
         * @throws Exception
         */
        @Override
        public void stop(BundleContext bc) throws Exception {

        }
}
