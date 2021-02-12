package com.ssafy.pjt.web;

import java.net.URI;
import java.nio.file.Path;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.pjt.core.service.dto.UploadFileDTO;
import com.ssafy.pjt.provider.service.ImageService;
import com.ssafy.pjt.provider.service.MemberService;
import com.ssafy.pjt.util.MediaUtils;
import com.ssafy.pjt.util.UploadFileUtils;
import com.ssafy.pjt.web.dto.MemberRequestDTO;

/**
 * @author https://github.com/woobong/spring-boot-jpa-summernote-image-upload-example/
 */
@Controller
@RequestMapping("/api")
public class ImageController {

	@Autowired
	ImageService imageService;
	@Autowired
	MemberService memberService;
	@Autowired
	ImageController self;

	/**
	 * 파일 업로드
	 * 
	 * @param file
	 * @return 고유URL주소
	 */
	@PostMapping({ "/data", "/image" })
	@ResponseBody
	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
		try {
			UploadFileDTO uploadedFile = imageService.store(file);
			return ResponseEntity.ok().body("/api/data/" + uploadedFile.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * 파일 다운로드
	 * 
	 * @param 파일고유번호
	 * 
	 * @return HTTP프로토콜처리된파일
	 */
	@GetMapping({ "/data/{fileId}", "/image/{fileId}" })
	@ResponseBody
	public ResponseEntity<?> serveFile(@PathVariable int fileId) {
		try {
			UploadFileDTO uploadedFile = imageService.load(fileId);
			HttpHeaders headers = new HttpHeaders();

			String fileName = uploadedFile.getOriginFileName();
			headers.add(HttpHeaders.CONTENT_DISPOSITION,
					"attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");

			if (MediaUtils.containsImageMediaType(uploadedFile.getContentType())) {
				headers.setContentType(MediaType.valueOf(uploadedFile.getContentType()));
			} else {
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			}

			Resource resource = imageService.loadAsResource(uploadedFile.getSaveFileName());
			return ResponseEntity.ok().headers(headers).body(resource);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * 프로필 이미지 업로드
	 */
	@PostMapping({ "/member/image/update/{uuid}" })
	@ResponseBody
	public ResponseEntity<?> handleProfileImageUpload(@PathVariable int uuid,
			@RequestParam("file") MultipartFile file) {
		try {
			UploadFileDTO uploadedFile = imageService.store(file, "/member", String.valueOf(uuid), true);
			MemberRequestDTO member = memberService.getMember(uuid);
			member.setImage(String.valueOf(uploadedFile.getId()));
			memberService.updateUser(member);
			return ResponseEntity.ok().body("/api/image/" + uploadedFile.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e);
		}
	}

	@Autowired
	private boolean isWindows;

	/**
	 * 프로필 이미지 다운로드
	 * 
	 * @param 파일고유번호
	 * 
	 * @return HTTP프로토콜처리된파일
	 */
//	@Cacheable(value = "cacheProfileImage", key = "{ #uuid, #size.orElse(64) }")
	@GetMapping({ "/member/image/{uuid}", "/member/image/{uuid}/{size}" })
	@ResponseBody
	public ResponseEntity<?> serveProfileImage(@PathVariable int uuid, @PathVariable Optional<Integer> size) {
		int imageSize = size.orElse(64);
		if (imageSize % 16 != 0 || imageSize / 512 > 1)
			imageSize = 64;
		try {
			MemberRequestDTO member = memberService.getMember(uuid);
			if (member == null || member.getImage() == null || member.getImage().length() == 0) {
				return self.serveProfileDefaultImage(imageSize);
			}
			String ino = member.getImage();
			if (ino.startsWith("http") || ino.chars().allMatch(Character::isDigit) == false) { // 숫자의 형태가 아니라면
				URI redirectUri = new URI(ino);
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.setLocation(redirectUri);
				return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
			}
			UploadFileDTO uploadedFile = imageService.load(Integer.parseInt(ino));

			HttpHeaders headers = new HttpHeaders();
			Resource resource = null;

			if ((isWindows && uploadedFile.getFilePath().toLowerCase().startsWith("c:"))
					|| (!isWindows && uploadedFile.getFilePath().startsWith("/"))) {
				String fileName = String.valueOf(uuid); /* uploadedFile.getOriginFileName(); */
				headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
						+ new String(member.getName().getBytes("UTF-8"), "ISO-8859-1") + "\"");

				if (MediaUtils.containsImageMediaType(uploadedFile.getContentType())) {
					headers.setContentType(MediaType.valueOf(uploadedFile.getContentType()));
				} else {
					headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				}
				if (imageSize == 0)
					resource = imageService.loadAsResource(uploadedFile.getSaveFileName());
				else {
					String filename = UploadFileUtils.getThumbnailFileName(uploadedFile.getSaveFileName(), imageSize);
					resource = imageService.loadAsResource(filename);
				}
			} else {
				return self.serveProfileDefaultImage(imageSize);
			}
			return ResponseEntity.ok().headers(headers).body(resource);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e);
		}
	}

	@ResponseBody
	@Cacheable(value = "defaultImage", key = "#imageSize")
	public ResponseEntity<?> serveProfileDefaultImage(int imageSize) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"default\"");
			headers.setContentType(MediaType.IMAGE_JPEG);
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//			Resource[] resources = resolver.getResources("classpath*:static/*.png");
			Resource resource = resolver.getResource("classpath:static/user-" + imageSize + ".jpg");
//			Path file = ResourceUtils.getFile("classpath:static/user-" + imageSize + ".png").toPath();
//			Resource resource = new UrlResource(file.toUri());
			return ResponseEntity.ok().headers(headers).body(resource);
		} catch (Exception e) {
			return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
