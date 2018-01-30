package bundle;

import java.util.ResourceBundle;

/**
 * Bundle manager retrieves properties from file "config.properties"
 */
public class ConfigurationManager {

  private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

  public static String getProperty(String key) {
    return resourceBundle.getString(key);
  }

}
