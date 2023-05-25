package org.raspberry.picast.app;

import java.util.List;

import org.raspberry.picast.pojos.entities.DirectoryDetailsVO;
import org.raspberry.picast.pojos.operations.directorydetails.FindAllByParent_IN;
import org.raspberry.picast.pojos.operations.directorydetails.FindAllByParent_OUT;
import org.raspberry.picast.service.DirectoryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirectoryDetailsRest {

	@Autowired
	private DirectoryDetailsService directoryDetailsService;

	@PostMapping(produces = "application/json", consumes = "application/json", value = "/directoryDetails/findAllByParent")
	public ResponseEntity<FindAllByParent_OUT> findAllByParent(RequestEntity<FindAllByParent_IN> requestEntityVO) {
		FindAllByParent_IN findAllByParent_IN = requestEntityVO.getBody();

		List<DirectoryDetailsVO> directoryDetailsListVO = directoryDetailsService.findAllByParent(findAllByParent_IN.getParentDirectory());

		FindAllByParent_OUT findAllByParent_OUT = new FindAllByParent_OUT();
		findAllByParent_OUT.setDirectoryDetailsList(directoryDetailsListVO);

		return ResponseEntity.ok(findAllByParent_OUT);
	}

}
