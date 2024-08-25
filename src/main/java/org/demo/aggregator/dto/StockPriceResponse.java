package org.demo.aggregator.dto;

import org.demo.aggregator.domain.Ticker;

import java.time.LocalDateTime;

public record StockPriceResponse(Ticker ticker,
                                 Integer price) {
}
