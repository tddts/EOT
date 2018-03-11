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

package com.github.tddts.jet.dao.impl;

import com.github.tddts.jet.config.spring.annotations.LoadContent;
import com.github.tddts.jet.dao.OrderDao;
import com.github.tddts.jet.model.db.CachedOrder;
import com.github.tddts.jet.model.db.ResultOrder;
import io.ebean.*;
import io.ebean.annotation.PersistBatch;
import io.ebean.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Repository
public class OrderDaoImplEbean extends EbeanAbstractDao<CachedOrder> implements OrderDao {

  private final Logger logger = LogManager.getLogger(OrderDaoImplEbean.class);

  @LoadContent("/sql/delete_expired.sql")
  private String sql_delete_expired;
  @LoadContent("/sql/delete_large.sql")
  private String sql_delete_large;

  @LoadContent("/sql/search_create_tables.sql")
  private String sql_update_searchCreateTables;
  @LoadContent("/sql/search_prepare.sql")
  private String sql_update_prepare_search;
  @LoadContent("/sql/search_select.sql")
  private String sql_select_search;

  @LoadContent("/sql/merge_orders.sql")
  private String sql_merge_orders;

  public OrderDaoImplEbean() {
    super(CachedOrder.class);
  }

  @Override
  public int removeSoonExpiredOrders(int time) {
    return ebeans().createSqlUpdate(sql_delete_expired).setParameter("diff", time).execute();
  }

  @Override
  public int removeLargeItemOrders(double volume) {
    return ebeans().createSqlUpdate(sql_delete_large).setParameter("volume", volume).execute();
  }

  @Override
  public int removeTooExpensiveOrders(double funds) {
    return ebeans().find(CachedOrder.class).where()
        .eq("buyOrder", false)
        .gt("price", funds).delete();
  }

  @Override
  @Transactional
  public List<ResultOrder> findProfitableOrders(double security, double cargoVolume, double taxRate) {
    // Create and fill temporary tables
    ebeans().createSqlUpdate(sql_update_searchCreateTables).execute();
    ebeans().createSqlUpdate(sql_update_prepare_search).setParameter("security_status", security).execute();

    RawSql rawSql = RawSqlBuilder.parse(sql_select_search).create();

    Query<ResultOrder> query = ebeans().find(ResultOrder.class)
        .setParameter("cargo_volume", cargoVolume)
        .setParameter("tax_rate", taxRate)
        .setRawSql(rawSql);

    return query.findList();
  }

  @Override
  @Transactional(batch = PersistBatch.ALL, batchOnCascade = PersistBatch.ALL, batchSize = 10000) // 10k - ESI page size
  public void merge(Collection<CachedOrder> orders) {

    SqlUpdate update = ebeans().createSqlUpdate(sql_merge_orders);

    for (CachedOrder order : orders) {

      update
          .setParameter("order_id", order.getOrderId())
          .setParameter("location_id", order.getLocationID())
          .setParameter("type_id", order.getTypeID())
          .setParameter("buy_order", order.getBuyOrder())
          .setParameter("duration", order.getDuration())
          .setParameter("price", order.getPrice())
          .setParameter("range", order.getRange())
          .setParameter("issued", order.getIssued())
          .setParameter("min_volume", order.getMinVolume())
          .setParameter("volume_remain", order.getVolumeRemain())
          .setParameter("volume_total", order.getVolumeTotal())
          .execute();
    }

    Transaction transaction = ebeans().currentTransaction();
    transaction.addModification("CACHED_ORDER", true, true, false);
  }


}
