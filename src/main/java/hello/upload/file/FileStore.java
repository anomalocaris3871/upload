package hello.upload.file;


import hello.upload.domain.UpLoadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public List<UpLoadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UpLoadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    public UpLoadFile storeFile(MultipartFile multiPartFile) throws IOException {
        if (multiPartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multiPartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        multiPartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UpLoadFile(originalFilename, storeFileName);
    }

    private String createStoreFileName(String originalFilename) {
        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(originalFilename);
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }


}
