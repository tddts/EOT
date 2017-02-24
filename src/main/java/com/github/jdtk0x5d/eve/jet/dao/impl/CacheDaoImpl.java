package com.github.jdtk0x5d.eve.jet.dao.impl;

import com.github.jdtk0x5d.eve.jet.config.spring.annotations.LoadContent;
import com.github.jdtk0x5d.eve.jet.dao.CacheDao;
import com.github.jdtk0x5d.eve.jet.model.db.OrderSearchCache;
import com.github.jdtk0x5d.eve.jet.model.db.OrderSearchResult;
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
  @LoadContent("sql.file.delete.large")
  private String sql_delete_large;

  @LoadContent("sql.file.find.systems")
  private String sql_find_systems;

  @LoadContent("sql.file.search.update.create.tables")
  private String sql_update_searchCreateTables;
  @LoadContent("sql.file.search.update.insert.stations")
  private String sql_update_searchInsertStations;
  @LoadContent("sql.file.search.update.insert.orders")
  private String sql_update_searchInsertOrders;

  @LoadContent("sql.file.search.select")
  private String sql_select_search;

  @Override
  public int removeSoonExpiredOrders(int time) {
    return ebeans().createSqlUpdate(sql_delete_expired).setParameter("diff", time).execute();
  }

  @Override
  public int removeDuplicateOrders() {
    return ebeans().createSqlUpdate(sql_delete_duplicate).execute();
  }

  @Override
  public int removeLargeItemOrders(double volume) {
    return ebeans().createSqlUpdate(sql_delete_large).setParameter("volume", volume).execute();
  }

  @Override
  public int removeTooExpensiveOrders(long funds) {
    return ebeans().find(OrderSearchCache.class).where().eq("buyOrder", false).gt("price", funds).delete();
  }

  @Override
  @Transactional
  public List<OrderSearchResult> findProfitableOrders(double security, double cargoVolume, double taxRate) {
    // Create and fill temporary tables
    ebeans().createSqlUpdate(sql_update_searchCreateTables).execute();
    ebeans().createSqlUpdate(sql_update_searchInsertStations).setParameter("security_status", security).execute();
    ebeans().createSqlUpdate(sql_update_searchInsertOrders).execute();
    // Find orders
    List<SqlRow> searchRows = ebeans().createSqlQuery(sql_select_search)
        .setParameter("cargo_volume", cargoVolume)
        .setParameter("tax_rate", taxRate)
        .findList();
    // Convert SqlRow to OrderSearchResult
    return searchRows.stream().map(OrderSearchResult::new).collect(Collectors.toList());
  }

  @Override
  public String findStationSystemName(long station) {
    SqlRow system = ebeans().createSqlQuery(sql_find_systems).setParameter("station",station).findUnique();
    return system.getString("solar_system_name");
  }

}
