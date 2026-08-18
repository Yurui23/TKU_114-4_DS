interface Playable {
    void play();
}

interface Compressible {
    void compress();
}

abstract class MediaFile {
    protected String fileName;

    public MediaFile(String fileName) {
        this.fileName = (fileName == null || fileName.trim().isEmpty()) ? "Unknown_File" : fileName;
    }

    public String getFileName() {
        return fileName;
    }
}

class ImageFile extends MediaFile implements Compressible {
    public ImageFile(String fileName) {
        super(fileName);
    }

    @Override
    public void compress() {
        System.out.println(fileName + " 影像已壓縮，減少畫質以降低檔案大小。");
    }
}

class AudioFile extends MediaFile implements Playable {
    public AudioFile(String fileName) {
        super(fileName);
    }

    @Override
    public void play() {
        System.out.println(fileName + " 音訊播放中...");
    }
}

class VideoFile extends MediaFile implements Playable, Compressible {
    public VideoFile(String fileName) {
        super(fileName);
    }

    @Override
    public void play() {
        System.out.println(fileName + " 影片播放中...");
    }

    @Override
    public void compress() {
        System.out.println(fileName + " 影片已壓縮，降低解析度與比特率。");
    }
}

public class MediaProcessingSystem {
    public static void main(String[] args) {
        MediaFile[] files = new MediaFile[]{
            new ImageFile("vacation.jpg"),
            new AudioFile(""),
            new VideoFile(null)
        };

        for (MediaFile file : files) {
            System.out.println("處理檔案: " + file.getFileName());
            
            if (file instanceof Playable playable) {
                playable.play();
            }
            
            if (file instanceof Compressible compressible) {
                compressible.compress();
            }
            
            System.out.println("---");
        }
    }
}