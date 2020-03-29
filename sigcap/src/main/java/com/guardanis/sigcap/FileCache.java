package com.guardanis.sigcap;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class FileCache {

    private File cacheDir;

    FileCache(@NotNull Context context) {
        cacheDir = new File(context.getFilesDir().getAbsolutePath() + "/sigcap/");

        if(!cacheDir.exists())
            cacheDir.mkdirs();
    }

    File getFile(@NotNull String url) {
        return new File(cacheDir, String.valueOf(url.hashCode()));
    }

    private void clear() {
        File[] files = cacheDir.listFiles();

        if (files != null) {
            for (File file : files)
                file.delete();
        }
    }

    public static void clear(Context context){
        new FileCache(context)
                .clear();
    }
}