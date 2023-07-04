package org.raspberry.picast.pojos.entities.archive;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArchiveDetailsVO {

	@JsonProperty("id_archive")
	private Long idArchive;

	@JsonProperty("id_parent")
	private Long idParent;

	@JsonProperty("file_path")
	private String filePath;

	@JsonProperty("file_name")
	private String fileName;

	@JsonProperty("file_size")
	private Long fileSize;

	public Long getIdArchive() {
		return idArchive;
	}

	public void setIdArchive(Long idArchive) {
		this.idArchive = idArchive;
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

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

}