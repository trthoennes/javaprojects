package org.tylley.bowspleef.Utils;

import org.bukkit.Bukkit;

import java.io.*;

public final class FileUtil {
    public static void copy(File source, File dest) throws IOException {
        if (source.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
            }

            String[] files = source.list();
            if (files == null) return;
            for (String file : files) {
                File newSource = new File(source, file);
                File newDestination = new File(dest, file);
                copy(newSource, newDestination);
            }
        } else {
            InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(dest);

            byte[] buf = new byte[1024];

            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
    }

    public static void delete(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) return;
            for (File f : files) {
                delete(f);
            }
        }
        file.delete();

        Bukkit.broadcastMessage("Deleted " + file.getName());
    }
}
