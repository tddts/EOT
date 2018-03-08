MERGE INTO CACHED_ORDER KEY(ORDER_ID) VALUES(
:order_id,
:location_id,
:type_id,
:buy_order,
:duration,
:price,
:range,
:issued,
:min_volume,
:volume_remain,
:volume_total);
