package org.raspberry.picast.model.directory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DIRECTORY_DETAILS")
public class DirectoryDetails {

	@Id
	@Column(name = "ID_DIRECTORY")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDirectory;

	@Column(name = "ID_PARENT")
	private Long idParent;

	@Column(name = "FILE_PATH")
	private String filePath;

	@Column(name = "FILE_NAME")
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
