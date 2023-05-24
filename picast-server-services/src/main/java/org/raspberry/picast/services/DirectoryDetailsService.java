package org.raspberry.picast.services;

import java.util.ArrayList;
import java.util.List;

import org.raspberry.picast.dao.repositories.DirectoryDetailsDao;
import org.raspberry.picast.model.entities.DirectoryDetails;
import org.raspberry.picast.pojos.entities.DirectoryDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectoryDetailsService {

	@Autowired
	private DirectoryDetailsDao directoryDetailsDao;

	public DirectoryDetailsVO findOneById(DirectoryDetailsVO directoryDetailsVO) {
		DirectoryDetails directoryDetails = directoryDetailsDao.findOneById(directoryDetailsVO.getIdDirectory());
		if (directoryDetails == null) {
			return null;
		}

		directoryDetailsVO = new DirectoryDetailsVO();
		directoryDetailsVO.setIdDirectory(directoryDetails.getIdDirectory());
		directoryDetailsVO.setFilePath(directoryDetails.getFilePath());
		directoryDetailsVO.setFileName(directoryDetails.getFileName());

		return directoryDetailsVO;
	}

	public List<DirectoryDetailsVO> findAll() {
		List<DirectoryDetailsVO> directoryDetailsListVO = new ArrayList<>();

		for (DirectoryDetails directoryDetails : directoryDetailsDao.findAll()) {
			DirectoryDetailsVO directoryDetailsVO = new DirectoryDetailsVO();
			directoryDetailsVO.setIdDirectory(directoryDetails.getIdDirectory());
			directoryDetailsVO.setFilePath(directoryDetails.getFilePath());
			directoryDetailsVO.setFileName(directoryDetails.getFileName());

			directoryDetailsListVO.add(directoryDetailsVO);
		}

		return directoryDetailsListVO;
	}

	public List<DirectoryDetailsVO> findAllByParent(DirectoryDetailsVO parentDirectoryVO) {
		List<DirectoryDetailsVO> directoryDetailsListVO = new ArrayList<>();

		for (DirectoryDetails directoryDetails : directoryDetailsDao
				.findAllByParent(parentDirectoryVO.getIdDirectory())) {
			DirectoryDetailsVO directoryDetailsVO = new DirectoryDetailsVO();
			directoryDetailsVO.setIdDirectory(directoryDetails.getIdDirectory());
			directoryDetailsVO.setFilePath(directoryDetails.getFilePath());
			directoryDetailsVO.setFileName(directoryDetails.getFileName());

			directoryDetailsListVO.add(directoryDetailsVO);
		}

		return directoryDetailsListVO;
	}

	public Boolean existsOneById(DirectoryDetailsVO directoryDetailsVO) {
		DirectoryDetails directoryDetails = directoryDetailsDao.findOneById(directoryDetailsVO.getIdDirectory());

		return directoryDetails != null;
	}

}
