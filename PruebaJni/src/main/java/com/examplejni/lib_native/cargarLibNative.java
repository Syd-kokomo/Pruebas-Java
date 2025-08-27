package com.examplejni.lib_native;

import com.examplejni.biblioteca.Libro;

public class cargarLibNative {
    static{
        System.loadLibrary("native"); 
        // Windows: native.dll
        // Linux: libnative.so
        // MacOS: libnative.so
    }

    public static native void datosLibro(Libro libro);
}
