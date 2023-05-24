package org.raspberry.picast.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.raspberry.picast.dao.repositories.ArchiveDetailsDao;
import org.raspberry.picast.exceptions.ServiceException;
import org.raspberry.picast.model.entities.ArchiveDetails;
import org.raspberry.picast.pojos.entities.ArchiveDetailsVO;
import org.raspberry.picast.pojos.entities.DirectoryDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class ArchiveDetailsService {

	@Autowired
	private ArchiveDetailsDao archiveDetailsDao;

	public ArchiveDetailsVO findOneById(ArchiveDetailsVO archiveDetailsVO) {
		ArchiveDetails archiveDetails = archiveDetailsDao.findOneById(archiveDetailsVO.getIdArchive());
		if (archiveDetails == null) {
			return null;
		}

		archiveDetailsVO = new ArchiveDetailsVO();
		archiveDetailsVO.setIdArchive(archiveDetails.getIdArchive());
		archiveDetailsVO.setFilePath(archiveDetails.getFilePath());
		archiveDetailsVO.setFileName(archiveDetails.getFileName());
		archiveDetailsVO.setFileSize(archiveDetails.getFileSize());

		return archiveDetailsVO;
	}

	public List<ArchiveDetailsVO> findAll() {
		List<ArchiveDetailsVO> archiveDetailsListVO = new ArrayList<>();

		for (ArchiveDetails archiveDetails : archiveDetailsDao.findAll()) {
			ArchiveDetailsVO archiveDetailsVO = new ArchiveDetailsVO();
			archiveDetailsVO.setIdArchive(archiveDetails.getIdArchive());
			archiveDetailsVO.setFilePath(archiveDetails.getFilePath());
			archiveDetailsVO.setFileName(archiveDetails.getFileName());
			archiveDetailsVO.setFileSize(archiveDetails.getFileSize());

			archiveDetailsListVO.add(archiveDetailsVO);
		}

		return archiveDetailsListVO;
	}

	public List<ArchiveDetailsVO> findAllByParent(DirectoryDetailsVO parentDirectoryVO) {
		List<ArchiveDetailsVO> archiveDetailsListVO = new ArrayList<>();

		for (ArchiveDetails archiveDetails : archiveDetailsDao.findAllByParent(parentDirectoryVO.getIdDirectory())) {
			ArchiveDetailsVO archiveDetailsVO = new ArchiveDetailsVO();
			archiveDetailsVO.setIdArchive(archiveDetails.getIdArchive());
			archiveDetailsVO.setFilePath(archiveDetails.getFilePath());
			archiveDetailsVO.setFileName(archiveDetails.getFileName());
			archiveDetailsVO.setFileSize(archiveDetails.getFileSize());

			archiveDetailsListVO.add(archiveDetailsVO);
		}

		return archiveDetailsListVO;
	}

	public Boolean existsOneById(ArchiveDetailsVO archiveDetailsVO) {
		ArchiveDetails archiveDetails = archiveDetailsDao.findOneById(archiveDetailsVO.getIdArchive());

		return archiveDetails != null;
	}

	public UrlResource downloadOneById(ArchiveDetailsVO archiveDetailsVO) {
		ArchiveDetails archiveDetails = archiveDetailsDao.findOneById(archiveDetailsVO.getIdArchive());
		if (archiveDetails == null) {
			throw new ServiceException("ArchiveDetails not exists");
		}

		try {
			return new UrlResource("file://" + archiveDetails.getFilePath());
		} catch (IOException ex) {
			throw new ServiceException("UrlArchive not created");
		}
	}

}
