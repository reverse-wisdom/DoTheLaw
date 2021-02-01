package com.ssafy.pjt.web;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.pjt.util.UploadFileUtils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/*
 * @Author https://github.com/fatihyildizli/springboot-tesseract-ocr
 * 
 */
@RestController
public class OcrController {

	
	private final String path;
	
	@Autowired
	public OcrController(String lawyerImagePath) {
		path = lawyerImagePath;
	}

	@PostMapping("/api/ocr")
	public ResponseEntity<?> DoOCR(/* @RequestParam("DestinationLanguage") String destinationLanguage, */
			/* @RequestParam("Image") */ MultipartFile image) throws IOException {

		ITesseract instance = new Tesseract();
		String result = "";
		try {
			System.out.println(path);
			BufferedImage in = ImageIO.read(UploadFileUtils.convert(path, image));

			BufferedImage newImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);

			Graphics2D g = newImage.createGraphics();
			g.drawImage(in, 0, 0, null);
			g.dispose();

			// instance.setLanguage(destinationLanguage);

			instance.setLanguage("kor");
			instance.setDatapath(ResourceUtils.getFile("classpath:static/tessdata/").getAbsolutePath());

			result = instance.doOCR(newImage);

		} catch (TesseractException | IOException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>("Error while reading image", HttpStatus.INTERNAL_SERVER_ERROR);
		}
//		System.out.println(result);	
		for (String s : Arrays.asList("명 서", "위의 사람은", "변호사", "합격")) {
			if (!result.contains(s))
				return new ResponseEntity<>("잘못된 증명서 입니다.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("정식 변호사 합격 증명서 입니다.", HttpStatus.OK);

	}
}
