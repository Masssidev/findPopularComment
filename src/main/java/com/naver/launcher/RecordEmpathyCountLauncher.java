package com.naver.launcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.naver.service.EmpathyService;

/**
 * RecordEmpathyCountLauncher class
 */
@Component
@EnableScheduling
public class RecordEmpathyCountLauncher {
	
	@Autowired
	private EmpathyService empathyService;
	
	@Scheduled (cron = "*/1 * * * * *")
	private void recordEmpathyCount(){
		empathyService.recordEmpathyCount();
	}
}
