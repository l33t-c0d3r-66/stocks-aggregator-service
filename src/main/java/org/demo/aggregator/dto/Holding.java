package org.demo.aggregator.dto;

import org.demo.aggregator.domain.Ticker;

public record Holding(Ticker ticker,
                      Integer quantity) {
}
