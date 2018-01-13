package bundle;

import java.util.ResourceBundle;

public class TextManager {

  private final static ResourceBundle resourceBundle = ResourceBundle
      .getBundle("text");

  // класс извлекает информацию из файла messages.properties
  private TextManager() {
  }

  public static String getProperty(String key) {
    return resourceBundle.getString(key);
  }
}