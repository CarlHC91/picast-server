package org.raspberry.picast.app;

import java.io.IOException;
import java.util.List;

import org.raspberry.picast.pojos.entities.ArchiveDetailsVO;
import org.raspberry.picast.pojos.operations.archivedetails.CreateOne_IN;
import org.raspberry.picast.pojos.operations.archivedetails.CreateOne_OUT;
import org.raspberry.picast.pojos.operations.archivedetails.DeleteOne_IN;
import org.raspberry.picast.pojos.operations.archivedetails.DeleteOne_OUT;
import org.raspberry.picast.pojos.operations.archivedetails.FindAllByParent_IN;
import org.raspberry.picast.pojos.operations.archivedetails.FindAllByParent_OUT;
import org.raspberry.picast.service.ArchiveDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArchiveDetailsRest {

	@Autowired
	private ArchiveDetailsService archiveDetailsService;

	@PreAuthorize("hasAuthority('/picast/archiveDetails/findAllByParent')")
	@PostMapping(produces = "application/json", consumes = "application/json", value = "/archiveDetails/findAllByParent")
	public ResponseEntity<FindAllByParent_OUT> findAllByParent(RequestEntity<FindAllByParent_IN> requestEntityVO) {
		FindAllByParent_IN findAllByParent_IN = requestEntityVO.getBody();

		List<ArchiveDetailsVO> archiveDetailsListVO = archiveDetailsService.findAllByParent(findAllByParent_IN.getParentDirectory());

		FindAllByParent_OUT findAllByParent_OUT = new FindAllByParent_OUT();
		findAllByParent_OUT.setArchiveDetailsList(archiveDetailsListVO);

		return ResponseEntity.ok(findAllByParent_OUT);
	}
	
	@PreAuthorize("hasAuthority('/picast/archiveDetails/createOne')")
	@PostMapping(produces = "application/json", consumes = "application/json", value = "/archiveDetails/createOne")
	public ResponseEntity<CreateOne_OUT> createOne(RequestEntity<CreateOne_IN> requestEntityVO) {
		CreateOne_IN createOne_IN = requestEntityVO.getBody();

		ArchiveDetailsVO archiveDetailsVO = archiveDetailsService.createOne(createOne_IN.getArchiveDetails());

		CreateOne_OUT createOne_OUT = new CreateOne_OUT();
		createOne_OUT.setArchiveDetails(archiveDetailsVO);

		return ResponseEntity.ok(createOne_OUT);
	}
	
	@PreAuthorize("hasAuthority('/picast/archiveDetails/deleteOne')")
	@PostMapping(produces = "application/json", consumes = "application/json", value = "/archiveDetails/deleteOne")
	public ResponseEntity<DeleteOne_OUT> deleteOne(RequestEntity<DeleteOne_IN> requestEntityVO) {
		DeleteOne_IN deleteOne_IN = requestEntityVO.getBody();

		archiveDetailsService.deleteOne(deleteOne_IN.getArchiveDetails());

		DeleteOne_OUT deleteOne_OUT = new DeleteOne_OUT();

		return ResponseEntity.ok(deleteOne_OUT);
	}

	@PreAuthorize("hasAuthority('/picast/archiveDetails/downloadOne')")
	@GetMapping(value = "/archiveDetails/downloadOne")
	public ResponseEntity<UrlResource> downloadOne(@RequestParam("id_archive") Long idArchive) throws IOException {
		ArchiveDetailsVO archiveDetailsVO = new ArchiveDetailsVO();
		archiveDetailsVO.setIdArchive(idArchive);

		UrlResource UrlResourceVO = archiveDetailsService.downloadOne(archiveDetailsVO);

		return ResponseEntity.ok(UrlResourceVO);
	}

}
