package com.example.kotlinrest.api

import com.example.kotlinrest.dtos.StockPrice
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.ThreadLocalRandom

@RestController
class ExampleController {

    @GetMapping(value = ["/stocks/{symbol}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun prices(@PathVariable(name = "symbol") symbol: String) : Flux<StockPrice> {
        return Flux.interval(Duration.ofSeconds(1))
                .map { StockPrice(symbol, randomPrice(), LocalDateTime.now()) }
    }

    private fun randomPrice(): Double {
        return ThreadLocalRandom.current().nextDouble(100.00);
    }
}