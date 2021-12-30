package com.example.lottery.application;

import java.util.ServiceLoader;

import com.example.lottery.service.business.StandardLotteryService;
import com.example.random.service.RandomNumberService;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class LotteryApp {

	public static void main(String[] args) {
		var lotterySrv = new StandardLotteryService();
		var services = 
				ServiceLoader.load(RandomNumberService.class);
		var rns = services.findFirst().get();
		lotterySrv.setRandomNumberService(rns);
		lotterySrv.draw(60, 6, 10)
		          .forEach(System.out::println);
	}

}
