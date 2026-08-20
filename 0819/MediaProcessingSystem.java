abstract class MediaFile {
    String fileName;

    MediaFile(String fileName) {
        this.fileName = fileName;
    }

    abstract void showInfo();
}

interface Playable {
    void play();
}

interface Compressible {
    void compress();
}

class ImageFile extends MediaFile implements Compressible {

    ImageFile(String fileName) {
        super(fileName);
    }

    void showInfo() {
        System.out.println("圖片：" + fileName);
    }

    public void compress() {
        System.out.println("圖片壓縮");
    }
}

class AudioFile extends MediaFile implements Playable, Compressible {

    AudioFile(String fileName) {
        super(fileName);
    }

    void showInfo() {
        System.out.println("音樂：" + fileName);
    }

    public void play() {
        System.out.println("播放音樂");
    }

    public void compress() {
        System.out.println("音樂壓縮");
    }
}

class VideoFile extends MediaFile implements Playable, Compressible {

    VideoFile(String fileName) {
        super(fileName);
    }

    void showInfo() {
        System.out.println("影片：" + fileName);
    }

    public void play() {
        System.out.println("播放影片");
    }

    public void compress() {
        System.out.println("影片壓縮");
    }
}

public class MediaProcessingSystem {
    public static void main(String[] args) {

        MediaFile[] files = {
            new ImageFile("photo.jpg"),
            new AudioFile("music.mp3"),
            new VideoFile("movie.mp4")
        };

        for (int i = 0; i < files.length; i++) {
            files[i].showInfo();

            if (files[i] instanceof Playable) {
                ((Playable) files[i]).play();
            }

            if (files[i] instanceof Compressible) {
                ((Compressible) files[i]).compress();
            }

            System.out.println();
        }
    }
}