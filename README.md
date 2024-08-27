## Introduction
The Aggregator Service acts as the central hub for the Spring WebFlux project. It connects the frontend with the Stock Service and the Customer Service, aggregating data from these sources to provide comprehensive information about stocks and customer portfolios. It also manages the buying and selling of stocks based on the latest prices.

## What is Reactive Programming?
Reactive Programming is a programming model focused on data streams and the propagation of changes. It is particularly suited for applications that need to process a large number of events asynchronously and handle high throughput.

## What is Spring Boot?
Spring Boot simplifies the development of Spring-based applications by providing default configurations and tools to create production-ready applications with minimal setup.

## What is Spring WebFlux?
Spring WebFlux is a fully non-blocking, reactive web framework that supports asynchronous processing and backpressure. It is ideal for building applications that need to scale and handle a large number of concurrent requests.

## Advantages of Spring WebFlux
<b>Scalability:</b> Efficiently manages a large number of concurrent users. <br>
<b>Reactive Streams Support:</b> Ensures that the system remains responsive under load by handling backpressure.<br>
<b>Improved Resource Utilization:</b> Uses fewer threads to handle more requests, reducing resource consumption.<br>

## Communication with Other Services 
The Aggregator Service communicates with: <br>
<b>Stock Service:</b> Queries the Stock Service for real-time stock price updates. <br>
<b>Customer Service:</b> Retrieves customer portfolio information, including account balance and owned stocks. <br>
<b>Stock Transactions:</b> When buying or selling stocks, the Aggregator Service first queries the Stock Service for the current price, then updates the Customer Service with the new portfolio details.<br>

```
+----------+          +-------------------+          +---------------+
| Frontend | <------> | Aggregator Service| <------> | Stock Service |
+----------+          +-------------------+          +---------------+
                                |
                                |
                                v
                        +-------------------+
                        | Customer Service  |
                        +-------------------+
```

