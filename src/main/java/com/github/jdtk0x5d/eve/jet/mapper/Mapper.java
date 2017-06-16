package com.github.jdtk0x5d.eve.jet.mapper;

import io.ebean.SqlRow;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface Mapper<T> {

  T convert(SqlRow sqlRow);
}
