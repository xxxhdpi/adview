#include <jni.h>
#include <string>

extern "C" JNIEXPORT void JNICALL
Java_com_ad_onlyinterad_AdClass_preloadingAd(JNIEnv *env, jobject obs) {
    jclass cls = env->FindClass("com/ad/onlyinterad/AdClass");
    jmethodID id = env->GetMethodID(cls, "preloadAd", "()V");
    jmethodID init = env->GetMethodID(cls, "<init>", "()V");
    jobject oj = env->NewObject(cls, init);
    env->CallVoidMethod(oj, id);
    env->DeleteLocalRef(oj);
}
