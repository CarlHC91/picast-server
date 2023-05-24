package org.raspberry.picast.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.raspberry.picast.dao.repositories.ArchiveDetailsDao;
import org.raspberry.picast.exception.ServiceException;
import org.raspberry.picast.model.entities.ArchiveDetails;
import org.raspberry.picast.model.entities.DirectoryDetails;
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
		archiveDetailsVO.setIdParent(archiveDetails.getIdParent());
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
			archiveDetailsVO.setIdParent(archiveDetails.getIdParent());
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
			archiveDetailsVO.setIdParent(archiveDetails.getIdParent());
			archiveDetailsVO.setFilePath(archiveDetails.getFilePath());
			archiveDetailsVO.setFileName(archiveDetails.getFileName());
			archiveDetailsVO.setFileSize(archiveDetails.getFileSize());

			archiveDetailsListVO.add(archiveDetailsVO);
		}

		return archiveDetailsListVO;
	}

	public Boolean existsOneByFilePath(ArchiveDetailsVO archiveDetailsVO) {
		ArchiveDetails archiveDetails = archiveDetailsDao.findOneByFilePath(archiveDetailsVO.getFilePath());

		return archiveDetails != null;
	}

	public ArchiveDetailsVO createOne(ArchiveDetailsVO archiveDetailsVO) {
		ArchiveDetails archiveDetails = new ArchiveDetails();
		archiveDetails.setIdParent(archiveDetailsVO.getIdParent());
		archiveDetails.setFilePath(archiveDetailsVO.getFilePath());
		archiveDetails.setFileName(archiveDetailsVO.getFileName());
		archiveDetails.setFileSize(archiveDetailsVO.getFileSize());
		archiveDetails = archiveDetailsDao.save(archiveDetails);

		archiveDetailsVO = new ArchiveDetailsVO();
		archiveDetailsVO.setIdArchive(archiveDetails.getIdArchive());
		archiveDetailsVO.setIdParent(archiveDetails.getIdParent());
		archiveDetailsVO.setFilePath(archiveDetails.getFilePath());
		archiveDetailsVO.setFileName(archiveDetails.getFileName());
		archiveDetailsVO.setFileSize(archiveDetails.getFileSize());

		return archiveDetailsVO;
	}

	public void deleteOne(ArchiveDetailsVO archiveDetailsVO) {
		ArchiveDetails archiveDetails = archiveDetailsDao.findOneById(archiveDetailsVO.getIdArchive());
		if (archiveDetails == null) {
			throw new ServiceException("Archive not exists");
		}

		archiveDetailsDao.delete(archiveDetails);
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
