package org.demo.aggregator.dto;

import org.demo.aggregator.domain.Ticker;
import org.demo.aggregator.domain.TradeAction;

public record TradeRequest(Ticker ticker,
                           TradeAction action,
                           Integer quantity) {
}
