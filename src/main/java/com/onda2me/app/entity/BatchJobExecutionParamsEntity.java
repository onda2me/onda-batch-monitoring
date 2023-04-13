package com.onda2me.app.entity;

import java.util.Date;

//@Table(name = "t_demo_user")
//@Entity
public class BatchJobExecutionParamsEntity {
	
	private int executionId;	
	private String typeCd;	
	private String keyName;	
	private String stringVal;
	private Date dateVal;
	private long longVal;
	private double doubleVal;
	private String identifying;
	
	private PrimaryKey primaryKey = new PrimaryKey();
	

	public PrimaryKey getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(PrimaryKey primaryKey) {
		this.primaryKey = primaryKey;
	}
	public int getExecutionId() {
		return executionId;
	}
	public void setExecutionId(int executionId) {
		this.executionId = executionId;
	}
	public String getTypeCd() {
		return typeCd;
	}
	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getStringVal() {
		return stringVal;
	}
	public void setStringVal(String stringVal) {
		this.stringVal = stringVal;
	}
	public Date getDateVal() {
		return dateVal;
	}
	public void setDateVal(Date dateVal) {
		this.dateVal = dateVal;
	}
	public long getLongVal() {
		return longVal;
	}
	public void setLongVal(long longVal) {
		this.longVal = longVal;
	}
	public double getDoubleVal() {
		return doubleVal;
	}
	public void setDoubleVal(double doubleVal) {
		this.doubleVal = doubleVal;
	}
	public String getIdentifying() {
		return identifying;
	}
	public void setIdentifying(String identifying) {
		this.identifying = identifying;
	}
	
	public class PrimaryKey {
		private int executionId;
		private String keyName;
		
		public int getExecutionId() {
			return executionId;
		}		
		public void setExecutionId(int executionId) {
			this.executionId = executionId;
		}
		public String getKeyName() {
			return keyName;
		}
		public void setKeyName(String keyName) {
			this.keyName = keyName;
		}
		
	}
}
