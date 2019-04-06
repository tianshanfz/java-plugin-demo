import java.net.URLClassLoader;
import java.net.URL;
import java.io.File;
import java.util.ArrayList;

public class PluginManager {

    private final String PLUGIN_DIR = "plugins";
    private ClassLoader pluginClassLoader = null;

    private PluginManager() {}

    private static class InnerObject{
        private static PluginManager instance = new PluginManager();
    }

    public static PluginManager getInstance() {
        return InnerObject.instance;
    }

    private ClassLoader initLoader() {
        try {
            File dir = new File(PLUGIN_DIR);
            File[] files = dir.listFiles();
            ArrayList<URL> urls = new ArrayList<URL>();
            for (File file : files) {
                if (file.getName().endsWith(".jar")) {
                    System.out.println("loading new plugin " + file.getAbsolutePath() + "...");
                    urls.add(file.toURI().toURL());
                }
            }
            int validSize = urls.size();
            System.out.println("totally load " + validSize + " plugin jars." );
            pluginClassLoader = new URLClassLoader(urls.toArray(new URL[validSize]) );
            return pluginClassLoader;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public ClassLoader getLoader() {
        if (pluginClassLoader == null) {
            return initLoader();
        }
        return pluginClassLoader;
    }


}
