package be.icc.service.imp;

import be.icc.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dorian scohier on 28-03-19.
 */
@Service
@Transactional
public class FileServiceImp implements FileService {

    private static final List<String> contentTypes = Arrays.asList("png", "jpeg", "jpg");

    @Override
    public String uploadFile(MultipartFile file, String userName, String fileOriginalName) {
        String[] location = fileOriginalName.split("\\\\");
        String fileName = location[location.length - 1];
        if (!contentTypes.contains(fileName.split("\\.")[1])) {
            return "error";
        }
        String directoryName = "D:/tmp/img/" + userName + "/";
        File dir = new File(directoryName);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);

        //write uploaded image to disk
        try {
            try (InputStream is = file.getInputStream(); BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                int i;
                while ((i = is.read()) != -1) {
                    stream.write(i);
                }
                stream.flush();
            }
        } catch (IOException e) {
            System.out.println("error : " + e.getMessage());
        }
        return dir.getAbsolutePath() + "\\" + fileName;
    }
}
