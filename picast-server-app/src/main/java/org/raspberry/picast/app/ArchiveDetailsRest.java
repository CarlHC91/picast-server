package org.raspberry.picast.app;

import java.io.IOException;
import java.util.List;

import org.raspberry.picast.pojos.entities.ArchiveDetailsVO;
import org.raspberry.picast.pojos.operations.archivedetails.FindAllByParent_IN;
import org.raspberry.picast.pojos.operations.archivedetails.FindAllByParent_OUT;
import org.raspberry.picast.service.ArchiveDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArchiveDetailsRest {

	@Autowired
	private ArchiveDetailsService archiveDetailsService;

	@PostMapping(produces = "application/json", consumes = "application/json", value = "/archiveDetails/findAllByParent")
	public ResponseEntity<FindAllByParent_OUT> findAllByParent(RequestEntity<FindAllByParent_IN> requestEntityVO) {
		FindAllByParent_IN findAllByParent_IN = requestEntityVO.getBody();

		List<ArchiveDetailsVO> archiveDetailsListVO = archiveDetailsService.findAllByParent(findAllByParent_IN.getParentDirectory());

		FindAllByParent_OUT findAllByParent_OUT = new FindAllByParent_OUT();
		findAllByParent_OUT.setArchiveDetailsList(archiveDetailsListVO);

		return ResponseEntity.ok(findAllByParent_OUT);
	}

	@GetMapping(value = "/archiveDetails/downloadOneById")
	public ResponseEntity<UrlResource> downloadOneById(@RequestParam("id_archive") Long idArchive) throws IOException {
		ArchiveDetailsVO archiveDetailsVO = new ArchiveDetailsVO();
		archiveDetailsVO.setIdArchive(idArchive);

		UrlResource UrlResourceVO = archiveDetailsService.downloadOneById(archiveDetailsVO);

		return ResponseEntity.ok(UrlResourceVO);
	}

}
