package org.demo.aggregator.client;


import org.demo.aggregator.domain.Ticker;
import org.demo.aggregator.dto.PriceUpdate;
import org.demo.aggregator.dto.StockPriceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Objects;

public class StockServiceClient {
    private static final Logger log = LoggerFactory.getLogger(StockServiceClient.class);

    private final WebClient client;
    private Flux<PriceUpdate> flux;

    public StockServiceClient(WebClient webClient) {
        this.client = webClient;
    }

    public Mono<StockPriceResponse> getStockPrice(Ticker ticker) {
        return this.client.get()
                .uri("/stock/{ticker}", ticker)
                .retrieve()
                .bodyToMono(StockPriceResponse.class);
    }

    public Flux<PriceUpdate> getPriceUpdatesStream() {
        // If it is on production object is created only once but incase of stock service shutdown this will return error so we need retry
        if(Objects.isNull(this.flux)) {
            this.flux = this.getPriceUpdates();
        }

        return this.flux;
    }

    // Should only be invoked once
    private Flux<PriceUpdate> getPriceUpdates() {
        return this.client.get()
                .uri("/stock/price-stream")
                .accept(MediaType.APPLICATION_NDJSON)
                .retrieve()
                .bodyToFlux(PriceUpdate.class)
                .retryWhen(retry())
                .cache(1); // make it hot publisher

    }

    private Retry retry() {
        return Retry.fixedDelay(100, Duration.ofSeconds(1))
                .doBeforeRetry(rs -> log.error("Stock service price stream all failed. retrying: {}", rs.failure().getMessage()));
    }

}
