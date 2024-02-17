package com.codeless.api.automation.repository;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.timeseries.TSCreateParams;
import redis.clients.jedis.timeseries.TSElement;

public class TimeSeriesRepository {

  private final Duration retention;
  private final JedisPooled jedisPool;

  public TimeSeriesRepository(Duration retention, JedisPooled jedisPool) {
    this.retention = retention;
    this.jedisPool = jedisPool;
  }

  public void create(String key) {
    this.create(key, retention.toMillis());
  }

  public void create(String key, Long retentionPeriodInMills) {
    TSCreateParams tsCreateParams = new TSCreateParams();
    tsCreateParams.retention(retentionPeriodInMills);
    jedisPool.tsCreate(key, tsCreateParams);
  }

  public void add(String key, Long timestamp, Double value, Map<String, String> labels) {
    TSCreateParams tsCreateParams = new TSCreateParams();
    tsCreateParams.labels(labels);
    tsCreateParams.retention(retention.toMillis());
    jedisPool.tsAdd(key, timestamp, value);
  }

  public List<TSElement> getRange(String key, Long fromTimestamp, Long toTimestamp) {
    return jedisPool.tsRange(key, fromTimestamp, toTimestamp);
  }

  public void delete(String key) {
    jedisPool.del(key);
  }

}
