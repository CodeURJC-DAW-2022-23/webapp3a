package es.webapp3.movieframe.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@Service
public class ImageService {

    private static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "images");

	private Path createFilePath(long imageId, Path folder) {
		return folder.resolve("image-" + imageId + ".jpg");
	}

    public void saveImage(String folderName, long imageId, MultipartFile image) throws IOException {

		Path folder = FILES_FOLDER.resolve(folderName);

		Files.createDirectories(folder);
		
		Path newFile = createFilePath(imageId, folder);

		image.transferTo(newFile);
	}

	public ResponseEntity<Object> createResponseFromImage(String folderName, long imageId) throws MalformedURLException {

		Path folder = FILES_FOLDER.resolve(folderName);
		
		Path imagePath = createFilePath(imageId, folder);
		
		Resource file = new UrlResource(imagePath.toUri());
		
		if(!Files.exists(imagePath)) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpg").body(file);
		}		
	}
}
