 /*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.diviso.graeshoppe.report.service.dto;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * TODO Provide a detailed description here 
 * @author MayaSanjeev
 * mayabytatech, maya.k.k@lxisoft.com
 */
public class OrderSummary {

	private String storeName;
	
	private String reportName;
	
	private Date endOfDaySummary;
	
	private Instant dateTime;
	
	private List<TypeDetailLine> typeDetail;

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public Date getEndOfDaySummary() {
		return endOfDaySummary;
	}

	public void setEndOfDaySummary(Date endOfDaySummary) {
		this.endOfDaySummary = endOfDaySummary;
	}

	public Instant getDateTime() {
		return dateTime;
	}

	public void setDateTime(Instant dateTime) {
		this.dateTime = dateTime;
	}

	public List<TypeDetailLine> getTypeDetail() {
		return typeDetail;
	}

	public void setTypeDetail(List<TypeDetailLine> typeDetail) {
		this.typeDetail = typeDetail;
	}

	@Override
	public String toString() {
		return "OrderSummary [storeName=" + storeName + ", reportName=" + reportName + ", endOfDaySummary="
				+ endOfDaySummary + ", dateTime=" + dateTime + ", typeDetail=" + typeDetail + "]";
	}
	
	
}
