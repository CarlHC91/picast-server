package org.raspberry.picast.pojos.operations.directorydetails;

import java.util.List;

import org.raspberry.picast.pojos.entities.DirectoryDetailsVO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FindAllByParent_OUT {

	@JsonProperty("directory_details_list")
	private List<DirectoryDetailsVO> directoryDetailsList;

	public List<DirectoryDetailsVO> getDirectoryDetailsList() {
		return directoryDetailsList;
	}

	public void setDirectoryDetailsList(List<DirectoryDetailsVO> directoryDetailsList) {
		this.directoryDetailsList = directoryDetailsList;
	}

}
