package com.nanoframework.plugin.rider.util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtils {
    public static void unzip(String zipFile, String destinationPath) throws Exception {
        var destDir = new File(destinationPath); //"src/main/resources/unzipTest"
        var buffer = new byte[1024];

        var zis = new ZipInputStream(ZipUtils.class.getClassLoader().getResourceAsStream(zipFile));
        var zipEntry = zis.getNextEntry();

        while (zipEntry != null) {
            var newFile = newFile(destDir, zipEntry);
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else {
                // fix for Windows-created archives
                var parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + parent);
                }

                var fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }

        zis.closeEntry();
        zis.close();
    }

    static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        var destFile = new File(destinationDir, zipEntry.getName());

        var destDirPath = destinationDir.getCanonicalPath();
        var destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }
}
