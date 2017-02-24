package com.github.jdtk0x5d.eve.jet.util;

import com.github.jdtk0x5d.eve.jet.exception.ApplicationException;
import com.github.jdtk0x5d.eve.jet.exception.FileLoadingException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class Util {

  public static String loadContent(String resourceName) throws FileLoadingException {
    try (InputStream inputStream = Util.class.getResourceAsStream(resourceName)) {
      if (inputStream == null) {
        throw new FileLoadingException("Not found: " + resourceName);
      }
      return new String(IOUtils.toByteArray(inputStream), "UTF-8");
    } catch (Exception e) {
      throw new FileLoadingException(e);
    }
  }

  public static void sleepForTimeout(long timeout) {
    try {
      Thread.sleep(timeout);
    } catch (InterruptedException e) {
      throw new ApplicationException(e);
    }
  }

  public static void saveProperty(String fileName, String key, String value) {
    try {
      File file = new File(fileName);
      if (!file.exists()) file.createNewFile();
      Properties properties = new Properties();

      try (InputStream in = FileUtils.openInputStream(file)) {
        properties.load(in);
      }

      properties.setProperty(key, value);

      try (OutputStream out = FileUtils.openOutputStream(file)) {
        properties.store(out, "");
      }

    } catch (IOException e) {
      throw new ApplicationException(e);
    }
  }

  public static String loadProperty(String fileName, String key) {
    try {
      File file = new File(fileName);

      if (!file.exists()) return null;

      Properties properties = new Properties();

      try (InputStream in = FileUtils.openInputStream(file)) {
        properties.load(in);
      }

      return properties.getProperty(key);

    } catch (IOException e) {
      throw new ApplicationException(e);
    }
  }
}
