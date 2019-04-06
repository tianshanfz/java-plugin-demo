package demoplugin;

public class PluginImpl implements PluginInterface {
    private int callCount = 0;

    public synchronized int sayHello(String name) {
        assert (name != null);
        callCount += 1;
        System.out.println("Hello " + name);
        return callCount;
    }
}
