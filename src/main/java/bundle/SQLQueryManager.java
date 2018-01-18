package bundle;

import java.util.ResourceBundle;

public class SQLQueryManager {

  private final static ResourceBundle resourceBundle = ResourceBundle
      .getBundle("sql");

  private SQLQueryManager() {
  }

  public static String getProperty(String key) {
    return resourceBundle.getString(key);
  }
}

