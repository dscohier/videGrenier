package be.icc.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Student on 24-12-18.
 */
public class FileModel {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
