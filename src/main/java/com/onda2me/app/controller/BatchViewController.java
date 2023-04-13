package com.onda2me.app.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.onda2me.app.common.SearchMap;
import com.onda2me.app.entity.BatchJobExecutionEntity;
import com.onda2me.app.entity.BatchJobInstanceEntity;
import com.onda2me.app.entity.BatchStepExecutionEntity;
import com.onda2me.app.service.BatchService;
import com.onda2me.app.util.DateUtil;
import com.onda2me.app.util.IConstants;
import com.onda2me.app.util.PageUtil;

@Controller
@RequestMapping("/batch")
public class BatchViewController {
	
	private static final Logger logger = LoggerFactory.getLogger(BatchViewController.class);
	
	@Resource
	private BatchService batchService;
	
	@RequestMapping(value = "/jobList", method = RequestMethod.GET)
	public String list(Model model, @RequestParam HashMap<String, Object> paramMap, PageUtil pgtl) {
		
		String todayDate = DateUtil.getDate("yyyy-MM-dd");
		
	    logger.debug("----------------------------------------------------");
	    logger.debug("/batch/jobList");
	    logger.debug("paramMap : " + paramMap);   
	    logger.debug("----------------------------------------------------");
	        
	    //------------------------------------------------------------------------
	    // SearchMap Init
	    //------------------------------------------------------------------------
	    SearchMap searchMap = new SearchMap(paramMap);
	    searchMap.initParam("status", "COMPLETED");
	    searchMap.initParam("jobName", "");
	    searchMap.initParam("startDate", todayDate);
	    searchMap.initParam("endDate", todayDate);
	    
	    //------------------------------------------------------------------------
	    // pageLink init
	    //------------------------------------------------------------------------
	    int total = batchService.listCount(searchMap);
	    pgtl.init(total, "/batch/jobList", searchMap.getParams());
	    searchMap.setPgtl(pgtl);
	    
		List<BatchJobInstanceEntity> list = batchService.list(searchMap);
		

		BatchStatus[] status = org.springframework.batch.core.BatchStatus.values();
		
		for(int i=0; i<status.length; i++) {
		    logger.debug(i + "::"+status[i] +", " + status[i].name() +", " + status[i].toString() +", " + status[i].valueOf(status[i].name()));
		}
		
		model.addAttribute("list", list);
		model.addAttribute("searchMap", searchMap);
		model.addAttribute("statusList", status);
		return "/html/batch/jobList";		
	}
	
	@RequestMapping(value = "/jobDetail", method = RequestMethod.GET)
	public String jobDetail(Model model, BatchJobInstanceEntity paramVo) {
		
		BatchJobInstanceEntity job = null;
		List<BatchStepExecutionEntity> steps = null;
		
		job = batchService.selectJobDetail(paramVo.getInstanceId());
		
		logger.debug("BatchJobInstanceEntity : " + job.toString());
		
		if(job.getExec() != null) {
			steps = batchService.listStepDetail(job.getExec().getExecutionId());
		}
		
		model.addAttribute("job", job);
		model.addAttribute("steps", steps);
		
		return "/html/batch/jobDetail";		
	}	
	
	@RequestMapping(value = "/stepDetail", method = RequestMethod.GET)
	public String stepDetail(Model model, BatchJobExecutionEntity paramVo) {
		
		BatchJobExecutionEntity job = batchService.selectStepDetail(paramVo.getExecutionId());
		
		logger.debug("BatchJobExecutionEntity : " + job.toString());
		
		model.addAttribute("job", job);
		return "/html/batch/stepDetail";		
	}	

}

