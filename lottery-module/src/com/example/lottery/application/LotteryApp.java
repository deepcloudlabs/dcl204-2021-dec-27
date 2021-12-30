package com.example.lottery.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.ServiceLoader;

import com.example.lottery.service.business.StandardLotteryService;
import com.example.random.service.QualityLevel;
import com.example.random.service.RandomNumberService;
import com.example.random.service.ServiceQuality;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class LotteryApp {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		var lotterySrv = new StandardLotteryService();
		var services = 
				ServiceLoader.load(RandomNumberService.class);
		Properties props = new Properties();
		File file = new File("src","application.properties");
		props.load(new FileInputStream(file));
		var qualityLevel = 
				QualityLevel.valueOf(props.getProperty("service.quality"));
		for (var service : services) {
			var clazz = service.getClass();
			if (clazz.isAnnotationPresent(ServiceQuality.class)) {
				var serviceQuality = clazz.getAnnotation(ServiceQuality.class);
				if (serviceQuality.value()==qualityLevel) {
					lotterySrv.setRandomNumberService(service);	
					break;
				}
			}
		}
		lotterySrv.draw(60, 6, 10).forEach(System.out::println);
	}

}
