package be.icc.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Scohier Dorian on 28-03-19.
 */
public interface FileService {
     String uploadFile(MultipartFile file, String userName, String fileOriginalName);
}
