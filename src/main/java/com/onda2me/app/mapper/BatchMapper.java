package com.onda2me.app.mapper;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.onda2me.app.entity.BatchJobExecutionEntity;
import com.onda2me.app.entity.BatchJobInstanceEntity;
import com.onda2me.app.entity.BatchStepExecutionEntity;

//@Configuration
@MapperScan("com.onda2me.app.mapper")
public interface BatchMapper {

	public int listCount(HashMap map);
	
	public List<BatchJobInstanceEntity> list(HashMap<String, Object> map);
	
	public BatchJobInstanceEntity selectJobDetail(int id);	
	
	public BatchJobExecutionEntity selectStepDetail(int id);
	
	public List<BatchStepExecutionEntity> listStepDetail(int id);

}
