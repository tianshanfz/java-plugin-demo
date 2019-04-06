
import java.util.ServiceLoader;
import demoplugin.PluginInterface;

import java.util.Iterator;

public class App {
    public static PluginInterface loadDemoPlugin() {
        PluginInterface ret = null;
        ClassLoader loader = PluginManager.getInstance().getLoader();
        if (loader != null) {
            ServiceLoader<PluginInterface> demoServiceLoader = ServiceLoader.load(PluginInterface.class,loader);
            Iterator<PluginInterface> it = demoServiceLoader.iterator();
            while (it.hasNext()) {
                ret = it.next();
                System.out.println("new demo plugin found: " + ret.getClass().getName());
            }

        }
        return ret;
    }


    public static void main(String[] args) {
        PluginInterface service = loadDemoPlugin();
        if ( service == null) {
            System.out.print("no demo plugin service found");
        } else {
            System.out.println("service call : " + service.sayHello("boy"));
            System.out.println("service call : " + service.sayHello("girl"));
        }
    }
}
