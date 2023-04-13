package com.onda2me.app.entity;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


//@Entity
@Getter
@Setter
@ToString
public class BatchJobExecutionEntity {
	
	private int executionId;	
	private int version;	
	private int instanceId;		
	private Date createTime;
	private Date startTime;
	private Date endTime;
	private String status;
	private String exitCode;
	private String exitMessage;
	private Date updateTime;
	
    private boolean isStatusComplete;
    private boolean isExitComplete;
	
	private List<BatchStepExecutionEntity> steps;
	
	public int getExecutionId() {
		return executionId;
	}
	public void setExecutionId(int executionId) {
		this.executionId = executionId;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(int instanceId) {
		this.instanceId = instanceId;
	}	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getStatus() {
		return status;
	} /*
	public void setStatus(String status) {
		this.status = status;
	}*/
	public String getExitCode() {
		return exitCode;
	}
	
	public String getExitMessage() {
		return exitMessage;
	}
	public void setExitMessage(String exitMessage) {
		this.exitMessage = exitMessage;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public List<BatchStepExecutionEntity> getSteps() {
		return steps;
	}
	public void setSteps(List<BatchStepExecutionEntity> steps) {
		this.steps = steps;
	}
	
    public void setStatus(String status) {
        this.status = status;
        
        setStatusComplete("COMPLETED".equals(status));  
    }   
    public void setExitCode(String exitCode) {
        this.exitCode = exitCode;
        
        setExitComplete("COMPLETED".equals(exitCode));  
    }      
    public void setStatusComplete(boolean isComplete) {
        this.isStatusComplete = isComplete;
    }    
    public void setExitComplete(boolean isComplete) {
        this.isExitComplete = isComplete;
    }
}
