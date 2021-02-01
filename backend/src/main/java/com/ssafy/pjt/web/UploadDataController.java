package com.ssafy.pjt.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class UploadDataController {
//    @Secured({"ROLE_ADMIN", "ROLE_LAWYER", "ROLE_USER"})
//	 @RequestMapping(value = "/images/0/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	    @ResponseBody
//	    public List<ImageLink> uploadAndRetureLink(
//	    		@RequestParam(value = "objectType", defaultValue = "-1", required = false) Integer objectType,
//	    		@RequestParam(value = "objectId", defaultValue = "-1", required = false) Long objectId,
//	    		@RequestParam(value = "imageId", defaultValue = "0", required = false) Long imageId,
//	    		@RequestHeader MultiValueMap<String, String> headers,
//	    		MultipartHttpServletRequest request) throws NotFoundException, IOException, UnAuthorizedException { 
//			
//			List<ImageLink> list = new ArrayList<ImageLink>(); 		
//			Iterator<String> names = request.getFileNames();
//			while (names.hasNext()) {
//			    String fileName = names.next();
//			    log.debug("multipart name : {}", fileName );
//			    MultipartFile mpf = request.getFile(fileName);
//			    Image image = upload( user , objectType, objectId, imageId, mpf); 		    
//			    ImageLink link = imageService.getImageLink(image, true);
//			    link.setFilename(image.getName());
//			    list.add( link ) ; 
//			}
//			return list ; 
//	    } 
}
