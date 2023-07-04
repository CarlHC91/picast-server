package org.raspberry.picast.pojos.operations.archivedetails;

import org.raspberry.picast.pojos.entities.directory.DirectoryDetailsVO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FindAllByParent_IN {

	@JsonProperty("parent_directory")
	private DirectoryDetailsVO parentDirectory;

	public DirectoryDetailsVO getParentDirectory() {
		return parentDirectory;
	}

	public void setParentDirectory(DirectoryDetailsVO parentDirectory) {
		this.parentDirectory = parentDirectory;
	}

}
