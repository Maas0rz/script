import java.io.*;
import java.util.Scanner;

public class DocumentManager{
    public static void main(String[] args) throws Exception{
        Scanner sc=new Scanner(System.in);
        System.out.println("1:copy directory\n2:delete directory\n3:exit\n");
        while(true){
            System.out.print("your choice: ");
            int command=sc.nextInt();
            switch(command){
                case 1:
                    copyDirectory();
                    break;
                case 2:
                    deleteDir();
                    break;
                case 3:
                    exit();
                    break;
                default:
                    System.out.println("input error!");
                    break;
            }
        }
    }
    //复制
    private static void copyDirectory() throws Exception{
        Scanner sc=new Scanner(System.in);
        System.out.print("source path: ");
        String srcDirectory=sc.next();
        File srcFile=new File(srcDirectory);
        System.out.print("destination path: ");
        String destDirectory=sc.next();
        File destFile=new File(destDirectory);
        copySrcPathToDestPath(srcFile, destFile);
        System.out.println("success!");
    }
    public static void copySrcPathToDestPath(File srcDir, File destDir) throws Exception{
        if(!destDir.exists()){
            destDir.mkdir();
        }
        File[] files=srcDir.listFiles();
        int i=0;
        for(File file: files){
            if(file.isDirectory()){
                String destDirName=destDir.getAbsolutePath().concat("\\"+file.getName());
                copySrcPathToDestPath(file, new File(destDirName));
                i++;
            }
            else{
                while(i<files.length){
                    File copiedFile=new File(srcDir, files[i].getName());
                    InputStream in=new FileInputStream(copiedFile);
                    File destFile=new File(destDir, files[i].getName());
                    OutputStream out=new FileOutputStream(destFile);
                    byte[] buff=new byte[1024];
                    int len;
                    while((len = in.read(buff))!=-1){
                        out.write(buff, 0, len);
                    }
                    in.close();
                    out.close();
                    i++;
                }
            }
        }
    }

    //删除
    public static void deleteDir(){
        Scanner sc=new Scanner(System.in);
        System.out.print("path: ");
        String delpath=sc.next();
        File Dir = new File(delpath);
        deleteFile(Dir);
        System.out.println("success!");
    }
    public static void deleteFile(File dir){
        if(dir.exists()){
            File[] files=dir.listFiles();
            for (File file: files){
                if (file.isDirectory())
                    deleteFile(file);
                else
                    file.delete();
            }
            dir.delete();
        }
    }

    //退出
    private static void exit() {
        System.out.println("bye~");
        System.exit(0);
    }
}