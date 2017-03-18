package eu.greyson.configuration;

public class Configuration {

    private Configuration() {}

    private static class LazyHolder {
        private static final Configuration INSTANCE = new Configuration();
    }

    public static Configuration getInstance() {
        return LazyHolder.INSTANCE;
    }
}
