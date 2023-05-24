package org.raspberry.picast.schedule;

import java.io.File;

import org.raspberry.picast.pojos.entities.ArchiveDetailsVO;
import org.raspberry.picast.pojos.entities.DirectoryDetailsVO;
import org.raspberry.picast.service.ArchiveDetailsService;
import org.raspberry.picast.service.DirectoryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ArchiveDetailsSchedule {

	@Autowired
	private ArchiveDetailsService archiveDetailsService;

	@Autowired
	private DirectoryDetailsService directoryDetailsService;

	@Scheduled(fixedDelay = 5 * 60 * 1000)
	public void init() {
		for (DirectoryDetailsVO directoryDetailsVO : directoryDetailsService.findAll()) {
			updateOne(directoryDetailsVO);
		}

		for (ArchiveDetailsVO archiveDetailsVO : archiveDetailsService.findAll()) {
			updateOne(archiveDetailsVO);
		}
	}

	private void updateOne(DirectoryDetailsVO parentDirectoryVO) {
		File fileParent = new File(parentDirectoryVO.getFilePath());

		if (!fileParent.exists()) {
			directoryDetailsService.deleteOne(parentDirectoryVO);
		}

		if (fileParent.exists()) {
			for (File fileDetails : fileParent.listFiles()) {
				if (fileDetails.isDirectory()) {
					DirectoryDetailsVO directoryDetailsVO = new DirectoryDetailsVO();
					directoryDetailsVO.setIdParent(parentDirectoryVO.getIdDirectory());
					directoryDetailsVO.setFilePath(fileDetails.getAbsolutePath());
					directoryDetailsVO.setFileName(fileDetails.getName());

					if (!directoryDetailsService.existsOneByFilePath(directoryDetailsVO)) {
						directoryDetailsVO = directoryDetailsService.createOne(directoryDetailsVO);

						updateOne(directoryDetailsVO);
					}
				}

				if (fileDetails.isFile()) {
					ArchiveDetailsVO archiveDetailsVO = new ArchiveDetailsVO();
					archiveDetailsVO.setIdParent(parentDirectoryVO.getIdDirectory());
					archiveDetailsVO.setFilePath(fileDetails.getAbsolutePath());
					archiveDetailsVO.setFileName(fileDetails.getName());
					archiveDetailsVO.setFileSize(fileDetails.length());

					if (!archiveDetailsService.existsOneByFilePath(archiveDetailsVO)) {
						archiveDetailsVO = archiveDetailsService.createOne(archiveDetailsVO);
					}
				}
			}
		}
	}

	private void updateOne(ArchiveDetailsVO archiveDetailsVO) {
		File fileArchive = new File(archiveDetailsVO.getFilePath());

		if (!fileArchive.exists()) {
			archiveDetailsService.deleteOne(archiveDetailsVO);
		}
	}

}
