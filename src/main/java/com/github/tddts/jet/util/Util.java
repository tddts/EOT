/*
 * Copyright 2017 Tigran Dadaiants
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.tddts.jet.util;

import com.github.tddts.jet.exception.ApplicationException;
import com.github.tddts.jet.exception.FileLoadingException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Class providing various utility methods.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class Util {

  /**
   * Load content of given resource as String.
   *
   * @param resourceName path to resource
   * @return file content as String
   * @throws FileLoadingException in case of any errors
   */
  public static String loadContent(String resourceName) throws FileLoadingException {
    try (InputStream inputStream = Util.class.getResourceAsStream(resourceName)) {
      if (inputStream == null) {
        throw new FileLoadingException("Not found: " + resourceName);
      }
      return new String(IOUtils.toByteArray(inputStream), StandardCharsets.UTF_8);
    } catch (Exception e) {
      throw new FileLoadingException(e);
    }
  }

  /**
   * Sleep for given timeout.
   * Simple wrap for {@link Thread#sleep(long)}
   *
   * @param timeout timeout
   */
  public static void sleepForTimeout(long timeout) {
    try {
      Thread.sleep(timeout);
    } catch (InterruptedException e) {
      throw new ApplicationException(e);
    }
  }

  /**
   * Save given property to given propeorty file.
   *
   * @param fileName property file path
   * @param key      property key
   * @param value    property value
   */
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

  /**
   * Load property with given key from given property file.
   *
   * @param fileName property file path
   * @param key      property key
   * @return property value
   */
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


  /**
   * Get URL of a classpath resource
   *
   * @param resourceName name of a resource
   * @return resource URL
   */
  public static URL getClasspathResourceURL(String resourceName) {
    return Thread.currentThread().getContextClassLoader().getResource(resourceName);
  }

  /**
   * Round a value to given precision.
   *
   * @param value     value
   * @param precision precision
   * @return rounded value
   */
  public static double round(double value, int precision) {
    int scale = (int) Math.pow(10, precision);
    return (double) Math.round(value * scale) / scale;
  }

  /**
   * Cut a value to given number of decimals.
   *
   * @param val      number
   * @param decimals decimals
   * @return rounded value
   */
  public static double roundDown(double val, int decimals) {
    double pow = Math.pow(10, decimals);
    return Math.floor(val * pow) / pow;
  }

}
