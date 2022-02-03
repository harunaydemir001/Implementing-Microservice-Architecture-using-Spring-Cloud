package com.example.lottery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.lottery.service.LotteryService;

@RestController
@RequestScope
@RequestMapping("numbers")
public class LotteryRestController {
	@Autowired private LotteryService lotteryService;
	
	// http://localhost:9400/lottery/api/v1/numbers?column=10
	@GetMapping
	public List<List<Integer>> getNumbers(@RequestParam int column){
		return lotteryService.draw(column);
	}
}
