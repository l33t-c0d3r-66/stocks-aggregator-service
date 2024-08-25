package org.demo.aggregator.controller;

import org.demo.aggregator.client.StockServiceClient;
import org.demo.aggregator.dto.PriceUpdate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
@RequestMapping("stock")
public class StockPriceStreamController {
    private final StockServiceClient stockServiceClient;

    public StockPriceStreamController(StockServiceClient stockServiceClient) {
        this.stockServiceClient = stockServiceClient;
    }

    @GetMapping(value="/price-stream", produces= TEXT_EVENT_STREAM_VALUE)
    public Flux<PriceUpdate> getPriceUpdatesStream() {
        return this.stockServiceClient.getPriceUpdatesStream();
    }
}
