/*
 * Copyright (c) 2020 CEVA Logistics, Inc. All Rights Reserved.
 */

package com.ceva.cfastbi.transcation.actuator;

import java.util.Objects;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.binder.MeterBinder;
import io.prometheus.client.Gauge;

/**
 *  Transaction Time Frame meter binder.
 * 
 * @author Raul Garnacho
 */
public class TransactionTimeFrameMetrics implements MeterBinder {

  private static final String NAME = "cjf_transactions_time_frame";
  private static final String DESCRIPTION = "Number of transactions per time frame";
  private Iterable<Tag> tags;
  private final Gauge transactionsInTimeFrameGauge;

  /**
   * Constructor.
   * 
   * @param transactionsInTimeFrameGauge Gague to monitor the number of transactions per time frame.
   */
  public TransactionTimeFrameMetrics(Gauge transactionsInTimeFrameGauge) {
    Objects.requireNonNull(transactionsInTimeFrameGauge, "Gauge cannot be null");
    this.transactionsInTimeFrameGauge = transactionsInTimeFrameGauge;
  }

  @Override
  public void bindTo(final MeterRegistry meterRegistry) {
    io.micrometer.core.instrument.Gauge.builder(NAME, this, x -> x.getTransactionsAndReset()).description(DESCRIPTION)
      .tags(tags).baseUnit("transactions").register(meterRegistry);
  }

  private Double getTransactionsAndReset() {
    double transactions = this.transactionsInTimeFrameGauge.get();
    // Restart gauge
    transactionsInTimeFrameGauge.set(0);
    return transactions;
  }
}