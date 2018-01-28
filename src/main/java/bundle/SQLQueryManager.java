package bundle;

import java.util.ResourceBundle;

/**
 * Bundle manager retrieves properties from file "sql.properties"
 */

public class SQLQueryManager {

  private final static ResourceBundle resourceBundle = ResourceBundle
      .getBundle("sql");

  public static String getProperty(String key) {
    return resourceBundle.getString(key);
  }
}

