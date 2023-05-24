package org.raspberry.picast.service;

import java.util.ArrayList;
import java.util.List;

import org.raspberry.picast.dao.repositories.DirectoryDetailsDao;
import org.raspberry.picast.exception.ServiceException;
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
		directoryDetailsVO.setIdParent(directoryDetails.getIdParent());
		directoryDetailsVO.setFilePath(directoryDetails.getFilePath());
		directoryDetailsVO.setFileName(directoryDetails.getFileName());

		return directoryDetailsVO;
	}

	public List<DirectoryDetailsVO> findAll() {
		List<DirectoryDetailsVO> directoryDetailsListVO = new ArrayList<>();

		for (DirectoryDetails directoryDetails : directoryDetailsDao.findAll()) {
			DirectoryDetailsVO directoryDetailsVO = new DirectoryDetailsVO();
			directoryDetailsVO.setIdDirectory(directoryDetails.getIdDirectory());
			directoryDetailsVO.setIdParent(directoryDetails.getIdParent());
			directoryDetailsVO.setFilePath(directoryDetails.getFilePath());
			directoryDetailsVO.setFileName(directoryDetails.getFileName());

			directoryDetailsListVO.add(directoryDetailsVO);
		}

		return directoryDetailsListVO;
	}

	public List<DirectoryDetailsVO> findAllByParent(DirectoryDetailsVO parentDirectoryVO) {
		List<DirectoryDetailsVO> directoryDetailsListVO = new ArrayList<>();

		for (DirectoryDetails directoryDetails : directoryDetailsDao.findAllByParent(parentDirectoryVO.getIdDirectory())) {
			DirectoryDetailsVO directoryDetailsVO = new DirectoryDetailsVO();
			directoryDetailsVO.setIdDirectory(directoryDetails.getIdDirectory());
			directoryDetailsVO.setIdParent(directoryDetails.getIdParent());
			directoryDetailsVO.setFilePath(directoryDetails.getFilePath());
			directoryDetailsVO.setFileName(directoryDetails.getFileName());

			directoryDetailsListVO.add(directoryDetailsVO);
		}

		return directoryDetailsListVO;
	}

	public Boolean existsOneByFilePath(DirectoryDetailsVO directoryDetailsVO) {
		DirectoryDetails directoryDetails = directoryDetailsDao.findOneByFilePath(directoryDetailsVO.getFilePath());

		return directoryDetails != null;
	}

	public DirectoryDetailsVO createOne(DirectoryDetailsVO directoryDetailsVO) {
		DirectoryDetails directoryDetails = new DirectoryDetails();
		directoryDetails.setIdParent(directoryDetailsVO.getIdParent());
		directoryDetails.setFilePath(directoryDetailsVO.getFilePath());
		directoryDetails.setFileName(directoryDetailsVO.getFileName());
		directoryDetails = directoryDetailsDao.save(directoryDetails);
		
		directoryDetailsVO = new DirectoryDetailsVO();
		directoryDetailsVO.setIdDirectory(directoryDetails.getIdDirectory());
		directoryDetailsVO.setIdParent(directoryDetails.getIdParent());
		directoryDetailsVO.setFilePath(directoryDetails.getFilePath());
		directoryDetailsVO.setFileName(directoryDetails.getFileName());
		
		return directoryDetailsVO;
	}
	
	public void deleteOne(DirectoryDetailsVO directoryDetailsVO) {
		DirectoryDetails directoryDetails = directoryDetailsDao.findOneById(directoryDetailsVO.getIdDirectory());
		if (directoryDetails == null) {
			throw new ServiceException("Directory not exists");
		}

		directoryDetailsDao.delete(directoryDetails);
	}

}
