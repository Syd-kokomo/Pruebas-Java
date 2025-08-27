#include "com_examplejni_lib_native_cargarLibNative.h"
#include <iostream>

using namespace std;

JNIEXPORT void JNICALL Java_com_examplejni_lib_1native_cargarLibNative_datosLibro(JNIEnv *env, jclass clase, jobject object_libro){
    //Clases
    jclass class_libro = env->GetObjectClass(object_libro);

    //Métodos
    jmethodID getAutor = env->GetMethodID(class_libro, "getAutor", "()Ljava/lang/String;");
    jmethodID getTitulo = env->GetMethodID(class_libro, "getTitulo", "()Ljava/lang/String;");
    jmethodID getFecha = env->GetMethodID(class_libro, "getFecha", "()Ljava/lang/String;");
    jmethodID getEditorial = env->GetMethodID(class_libro, "getEditorial", "()Ljava/lang/String;");
    jmethodID getPais = env->GetMethodID(class_libro, "getPais", "()Ljava/lang/String;");
    jmethodID getCiudad = env->GetMethodID(class_libro, "getCiudad", "()Ljava/lang/String;");
    jmethodID getEdicion = env->GetMethodID(class_libro, "getEdicion", "()I"); 
    jmethodID getGenero = env->GetMethodID(class_libro, "getGenero", "()Ljava/lang/String;");
    jmethodID getPaginas = env->GetMethodID(class_libro, "getPaginas", "()I"); 

    //Para obtener el ISBN
    jmethodID getIsbn = env->GetMethodID(class_libro, "getIsbn", "()Ljava/lang/Long;"); //Se toma el ISBN de tipo Long (no primitivo)
    jobject obj_isbn = env->CallObjectMethod(object_libro, getIsbn); 
    jclass classLong = env->FindClass("java/lang/Long");
    jmethodID longValue = env->GetMethodID(classLong, "longValue", "()J");
    

    //Atributos
    jstring autor = (jstring) env->CallObjectMethod(object_libro, getAutor);
    jstring titulo = (jstring) env->CallObjectMethod(object_libro, getTitulo);
    jstring fecha = (jstring) env->CallObjectMethod(object_libro, getFecha);
    jstring editorial = (jstring) env->CallObjectMethod(object_libro, getEditorial);
    jstring pais = (jstring) env->CallObjectMethod(object_libro, getPais);
    jstring ciudad = (jstring) env->CallObjectMethod(object_libro, getCiudad);
    jint edicion = env->CallIntMethod(object_libro, getEdicion);
    jstring genero = (jstring) env->CallObjectMethod(object_libro, getGenero);
    jint pags = env->CallIntMethod(object_libro, getPaginas);
    jlong isbn = env->CallLongMethod(obj_isbn, longValue);

    //Creación de const char * para guardar los jstring a un String c
    const char *c_autor = env->GetStringUTFChars(autor, nullptr);
    const char *c_titulo = env->GetStringUTFChars(titulo, nullptr);
    const char *c_fecha = env->GetStringUTFChars(fecha, nullptr);
    const char *c_genero = env->GetStringUTFChars(genero, nullptr);
    const char *c_editorial = env->GetStringUTFChars(editorial, nullptr);
    const char *c_pais = env->GetStringUTFChars(pais, nullptr);
    const char *c_ciudad = env->GetStringUTFChars(ciudad, nullptr);


    cout<<"-----------------\n";
    cout<<"Datos del libro\n";
    cout<<"-----------------\n";
    cout<<"Autor: "<<c_autor<<"\n";
    cout<<"Titulo: "<<c_titulo<<"\n";
    cout<<"Fecha de publicacion: "<<c_fecha<<"\n";
    cout<<"Editorial: "<<c_editorial<<"\n";
    cout<<"Pais: "<<c_pais<<"\n";
    cout<<"Ciudad: "<<c_ciudad<<"\n";
    cout<<"Edicion: "<<edicion<<"\n";
    cout<<"Genero literario: "<<c_genero<<"\n";
    cout<<"Cantidad de paginas: "<<pags<<"\n";
    cout<<"ISBN: "<<isbn<<"\n";

    env->ReleaseStringUTFChars(autor, c_autor);
    env->ReleaseStringUTFChars(titulo, c_titulo);
    env->ReleaseStringUTFChars(fecha, c_fecha);
    env->ReleaseStringUTFChars(editorial, c_editorial);
    env->ReleaseStringUTFChars(pais, c_pais);
    env->ReleaseStringUTFChars(ciudad, c_ciudad);
    env->ReleaseStringUTFChars(genero, c_genero);
}