package org.demo.aggregator.dto;

import org.demo.aggregator.domain.Ticker;
import org.demo.aggregator.domain.TradeAction;

public record StockTradeRequest(Ticker ticker,
                                Integer price,
                                Integer quantity,
                                TradeAction action) {
}
