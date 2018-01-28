package bundle;

import java.util.ResourceBundle;

/**
 * Bundle manager retrieves properties from file "messages.properties"
 */

public class MessageManager {

  private final static ResourceBundle resourceBundle = ResourceBundle
      .getBundle("messages");

  public static String getProperty(String key) {
    return resourceBundle.getString(key);
  }
}