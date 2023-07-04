package org.raspberry.picast.pojos.operations.archivedetails;

import java.util.List;

import org.raspberry.picast.pojos.entities.archive.ArchiveDetailsVO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FindAllByParent_OUT {

	@JsonProperty("archive_details_list")
	private List<ArchiveDetailsVO> archiveDetailsList;

	public List<ArchiveDetailsVO> getArchiveDetailsList() {
		return archiveDetailsList;
	}

	public void setArchiveDetailsList(List<ArchiveDetailsVO> archiveDetailsList) {
		this.archiveDetailsList = archiveDetailsList;
	}

}
