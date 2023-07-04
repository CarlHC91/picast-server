package org.raspberry.picast.pojos.entities.directory;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DirectoryDetailsVO {

	@JsonProperty("id_directory")
	private Long idDirectory;

	@JsonProperty("id_parent")
	private Long idParent;

	@JsonProperty("file_path")
	private String filePath;

	@JsonProperty("file_name")
	private String fileName;

	public Long getIdDirectory() {
		return idDirectory;
	}

	public void setIdDirectory(Long idDirectory) {
		this.idDirectory = idDirectory;
	}

	public Long getIdParent() {
		return idParent;
	}

	public void setIdParent(Long idParent) {
		this.idParent = idParent;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}