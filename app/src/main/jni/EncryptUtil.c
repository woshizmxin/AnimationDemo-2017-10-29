#include "EncryptUtil.h"
#include <string.h>
#include <jni.h>
 /*  * Class: io_github_yanbober_ndkapplication_NdkJniUtils  * Method: getCLanguageString  * Signature: ()Ljava/lang/String;  */
JNIEXPORT jstring JNICALL Java_com_marsthink_app_utils_EncryptUtil_aes_1encrypt (JNIEnv * env,jobject obj, jstring string)
{
             return (*env)->NewStringUTF(env,"ecrypt");

}

JNIEXPORT jstring JNICALL Java_com_marsthink_app_utils_EncryptUtil_aes_1decrypt (JNIEnv * env,jobject obj, jstring string)
{
          return (*env)->NewStringUTF(env,"decrypt");
}