package com.onda2me.app.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class BatchJobInstanceEntity {

	private int instanceId;	
	private int version;
	private String jobName;
	private String jobKey;
	
	private BatchJobExecutionEntity exec;
	
	private List<BatchJobExecutionParamsEntity> execParams;
	
}
