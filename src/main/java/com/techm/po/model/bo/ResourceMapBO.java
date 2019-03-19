package com.techm.po.model.bo;

import java.util.List;

public class ResourceMapBO {
	private List<ResourceMap> resources;
	private List<ResourceMap> contractToPid;
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
	@Override
	public String toString() {
		return "ResourceMapBO [resources=" + resources + ", contractToPid=" + contractToPid + "]";
	}
}
