package org.raspberry.picast.pojos.operations.archivedetails;

import org.raspberry.picast.pojos.entities.archive.ArchiveDetailsVO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateOne_IN {

	@JsonProperty("archive_details")
	private ArchiveDetailsVO archiveDetails;

	public ArchiveDetailsVO getArchiveDetails() {
		return archiveDetails;
	}

	public void setArchiveDetails(ArchiveDetailsVO archiveDetails) {
		this.archiveDetails = archiveDetails;
	}

}
