package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
public class LotteryService {
	@Value("${lottery.max}") private int max;
	@Value("${lottery.size}") private int size;
	@Value("${server.port}") private int port;
	
	public List<List<Integer>> draw(int column){
		System.err.println("Lottery service @ "+port+" is serving...");
	   return IntStream.range(0, column)
			           .mapToObj(i -> getLotteryNumbers())
			           .toList();
	}
	
	public List<Integer> getLotteryNumbers(){
		return ThreadLocalRandom.current()
				                .ints(1,max)
				                .distinct()
				                .limit(size)
				                .sorted()
				                .boxed()
				                .toList();
	}
}