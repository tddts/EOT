package com.github.jdtk0x5d.eve.jet.dao.impl;

import com.github.jdtk0x5d.eve.jet.config.spring.annotations.LoadContent;
import com.github.jdtk0x5d.eve.jet.config.spring.annotations.Profiling;
import com.github.jdtk0x5d.eve.jet.dao.CacheDao;
import com.github.jdtk0x5d.eve.jet.model.db.OrderSearchCache;
import com.github.jdtk0x5d.eve.jet.model.db.OrderSearchResult;
import com.github.jdtk0x5d.eve.jet.model.db.RouteCache;
import io.ebean.SqlRow;
import io.ebean.annotation.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class CacheDaoImpl extends AbstractDao implements CacheDao {

  @LoadContent("sql.file.delete.expired")
  private String sql_delete_expired;
  @LoadContent("sql.file.delete.duplicate")
  private String sql_delete_duplicate;

  @LoadContent("sql.file.search.update.create.tables")
  private String sql_update_searchCreateTables;
  @LoadContent("sql.file.search.update.insert.stations")
  private String sql_update_searchInsertStations;
  @LoadContent("sql.file.search.update.insert.sell.orders")
  private String sql_update_searchInsertSellOrders;
  @LoadContent("sql.file.search.update.insert.buy.orders")
  private String sql_update_searchInsertBuyOrders;

  @LoadContent("sql.file.search.select")
  private String sql_select_searchForType;


  @Override
  public RouteCache findCachedRoute(Long firstPointId, Long secondPointId) {
    return ebeans().find(RouteCache.class).where()
        .in("startPointId", firstPointId, secondPointId)
        .or()
        .in("endPointId", firstPointId, secondPointId).findUnique();
  }

  @Override
  public List<Integer> findTypeIds() {
    return ebeans().find(OrderSearchCache.class).select("typeID").setDistinct(true).findSingleAttributeList();
  }

  @Override
  public int removeSoonExpiredOrders(int time) {
    return ebeans().createSqlUpdate(sql_delete_expired).setParameter("diff", time).execute();
  }

  @Override
  public int removeDuplicateOrders() {
    return ebeans().createSqlUpdate(sql_delete_duplicate).execute();
  }

  @Override
  @Profiling
  @Transactional
  public List<OrderSearchResult> findOrdersForType(Integer typeId) {
    ebeans().createSqlUpdate(sql_update_searchCreateTables).execute();
    ebeans().createSqlUpdate(sql_update_searchInsertStations).setParameter("security", 0.5f).execute();
    ebeans().createSqlUpdate(sql_update_searchInsertSellOrders).setParameter("type_param", typeId).execute();
    ebeans().createSqlUpdate(sql_update_searchInsertBuyOrders).setParameter("type_param", typeId).execute();
    List<SqlRow> searchRows = ebeans().createSqlQuery(sql_select_searchForType).findList();
    return searchRows.stream().map(OrderSearchResult::new).collect(Collectors.toList());
  }

}
