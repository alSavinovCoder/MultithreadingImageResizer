import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int numberOfProcessors = Runtime.getRuntime().availableProcessors();
    private static int newWidth = 300;
    public static void main(String[] args){
        String srcFolder = "src/main/resources";
        String dstFolder = "src/main/result";

        System.out.println("number Of Processors: " + numberOfProcessors);

        File srcDir = new File(srcFolder);
        long start = System.currentTimeMillis();
        File[] files = srcDir.listFiles();

        List<File[]> fileList = new ArrayList<>();

        int currentFileNumber = 0;
        int arrayLength = (int) Math.ceil((double)files.length / (double) numberOfProcessors);
        for (int i = 0; i < numberOfProcessors; i++) {
            fileList.add(new File[arrayLength]);
            if (currentFileNumber < files.length) {
                for (int j = 0; (j < fileList.get(i).length); j++) {
                    fileList.get(i)[j] = files[currentFileNumber];
                    currentFileNumber++;
                }
            }
            new Thread(new ImageResizer(fileList.get(i), newWidth, dstFolder, start)).start();
        }
    }
}
