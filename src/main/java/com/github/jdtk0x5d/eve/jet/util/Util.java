package com.github.jdtk0x5d.eve.jet.util;

import com.github.jdtk0x5d.eve.jet.context.Context;
import com.github.jdtk0x5d.eve.jet.exception.FileLoadingException;
import com.github.jdtk0x5d.eve.jet.model.api.dotlan.DotlanJump;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class Util {

  public static String loadContent(String filename) throws FileLoadingException {
    try {
      URL fileUrl = Util.class.getClassLoader().getResource(filename);
      return fileUrl == null ? "" : new String(Files.readAllBytes(Paths.get(fileUrl.toURI())), "UTF-8");
    } catch (Exception e) {
      throw new FileLoadingException(e);
    }
  }

  public static void initBean(Object object, String name) {
    AutowireCapableBeanFactory beanFactory = Context.getBeanFactory();
    beanFactory.autowireBean(object);
    beanFactory.initializeBean(object, name);
  }

  public static DotlanJump parseJump(String value) {
    String[] jumps = value.split("-");
    return new DotlanJump(Integer.valueOf(jumps[0]), Integer.valueOf(jumps[1]));
  }

}
