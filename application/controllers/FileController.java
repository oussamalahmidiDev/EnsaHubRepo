package application.controllers;

import java.io.*;

public class FileController {

    String rootPath;
    String downloadPath;

    public FileController(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public FileController() {
        this.rootPath = "/Users/oussamalahmidi/eclipse-workspace/JavaProject/";
        this.downloadPath = "/Users/oussamalahmidi/Downloads/";
    }

    public String uploadFile (File selectedFile, String fileName) {
        FileInputStream fin;
        FileOutputStream fout;
        String name = fileName + "_" + System.currentTimeMillis() + ".pdf";
        System.out.println(name);
        int bytein;
        try {

            fin = new FileInputStream(selectedFile);
            fout = new FileOutputStream(this.rootPath + name);
            while((bytein=fin.read())!= -1){

                fout.write(bytein);
            }
            fin.close();
            fout.close();

        } catch (FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    public boolean downloadFile(String path) {
        System.out.println("Saving file ...");
        FileInputStream fin;
        FileOutputStream fout;
        int bytein;
        try{
            fin = new FileInputStream(this.rootPath + path);
            fout=new FileOutputStream(this.downloadPath + path);
            while((bytein=fin.read())!= -1){

                fout.write(bytein);
            }
            fin.close();
            fout.close();
            return true;
        }
        catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
