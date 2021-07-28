package hello.upload.domain;

import lombok.Data;

@Data
public class UpLoadFile {

    private String uploadFileName;
    private String storeFileName;

    public UpLoadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
