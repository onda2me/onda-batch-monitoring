package com.onda2me.app.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onda2me.app.entity.BatchJobExecutionEntity;
import com.onda2me.app.entity.BatchJobInstanceEntity;
import com.onda2me.app.entity.BatchStepExecutionEntity;
import com.onda2me.app.mapper.BatchMapper;

//@Configuration
@Service("com.onda2me.app.service")
public class BatchService {

	@Autowired 
	private BatchMapper batchMapper;
	
	public BatchService(BatchMapper batchMapper) {
		this.batchMapper = batchMapper;
	}
	
	public int listCount(HashMap map) {
		
		return batchMapper.listCount(map);
	}
	
	public List<BatchJobInstanceEntity> list(HashMap<String, Object> map) {
		
		return batchMapper.list(map);
	}
	
	public BatchJobInstanceEntity selectJobDetail(int id) {
		
		return batchMapper.selectJobDetail(id);
	}
	
	public BatchJobExecutionEntity selectStepDetail(int id) {
		
		return batchMapper.selectStepDetail(id);
	}	
	
	public List<BatchStepExecutionEntity> listStepDetail(int id) {
		
		return batchMapper.listStepDetail(id);
	}	
}
