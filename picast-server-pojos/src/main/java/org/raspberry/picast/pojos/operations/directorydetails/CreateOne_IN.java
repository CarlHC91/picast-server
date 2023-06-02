package org.raspberry.picast.pojos.operations.directorydetails;

import org.raspberry.picast.pojos.entities.DirectoryDetailsVO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateOne_IN {

	@JsonProperty("directory_details")
	private DirectoryDetailsVO directoryDetails;

	public DirectoryDetailsVO getDirectoryDetails() {
		return directoryDetails;
	}

	public void setDirectoryDetails(DirectoryDetailsVO directoryDetails) {
		this.directoryDetails = directoryDetails;
	}

}
