package utils;

public class FileStruct {
    public String name = "";
    public String contents = "";
    public boolean isFolder = false;
    public long size = 0; //bytes

    public FileStruct(String name, String contents) {
        this.name = name;
        this.contents = contents;
    }

    public FileStruct(String name, boolean isFolder) {
        this.name = name;
        this.isFolder = isFolder;
    }

    public FileStruct(String name, boolean isFolder, long size) {
        this.name = name;
        this.isFolder = isFolder;
        this.size = size;
    }
}
