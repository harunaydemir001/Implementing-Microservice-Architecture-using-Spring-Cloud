package com.example.crm.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.crm.dto.Ticker;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@SuppressWarnings("unused")
public class ReactiveBinanceRestClient {
	private static final String BINANCE_BASE_URL = "https://api.binance.com/api/v3";
	private static final List<String> SYMBOLS = List.of(
			"BTCUSDT","LTCBTC","ETHBTC","BNBBTC","NEOBTC","EOSETH",
			"SNTETH","BNTETH","BCCBTC","SALTBTC","SALTETH","XVGETH",
			"XVGBTC", "SUBETH","EOSBTC","MTHBTC","ETCETH","DNTBTC","ENGBTC"	
	);
	// reactive client -> spring reactive web
	private WebClient webClient = WebClient.builder().baseUrl(BINANCE_BASE_URL).build();
	private static final Comparator<Ticker> compareByPrice = (t1,t2) -> {
		var price1 = Double.parseDouble(t1.getPrice());
		var price2 = Double.parseDouble(t2.getPrice());
		return price1 < price2 ? -1 : +1;
	};
	
	@Scheduled(fixedRate = 5_000)
	public void callBinance() {
		System.err.println(String.format("Calling binance api %d times...",SYMBOLS.size()));
		Flux.fromIterable(SYMBOLS)
		    .parallel()
		    .runOn(Schedulers.boundedElastic())
		    .flatMap(this::getTicker)
		    //.sorted(compareByPrice)
		    .subscribe(ticker -> {System.err.println(Thread.currentThread().getName()+": "+ticker);});
		System.err.println(String.format("Calling binance api %d times...✔",SYMBOLS.size()));
	}
	
	public Mono<Ticker> getTicker(String symbol){ // async
		return webClient.get()     
				        .uri(uriBuilder -> uriBuilder.path("/ticker/price").queryParam("symbol",symbol).build())
				        .retrieve().bodyToMono(Ticker.class);
				                  
	}
}
