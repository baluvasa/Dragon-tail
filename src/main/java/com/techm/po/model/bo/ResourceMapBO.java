package com.techm.po.model.bo;

import java.util.List;

public class ResourceMapBO {
	private List<ResourceMap> resources;
	private List<ResourceMap> contractToPid;
	private List<String> toContractPid;
	public List<ResourceMap> getResources() {
		return resources;
	}
	public void setResources(List<ResourceMap> resources) {
		this.resources = resources;
	}
	public List<ResourceMap> getContractToPid() {
		return contractToPid;
	}
	public void setContractToPid(List<ResourceMap> contractToPid) {
		this.contractToPid = contractToPid;
	}
	
	public List<String> getToContractPid() {
		return toContractPid;
	}
	public void setToContractPid(List<String> toContractPid) {
		this.toContractPid = toContractPid;
	}
	@Override
	public String toString() {
		return "ResourceMapBO [resources=" + resources + ", contractToPid=" + contractToPid + ", toContractPid="
				+ toContractPid + "]";
	}
	
}
