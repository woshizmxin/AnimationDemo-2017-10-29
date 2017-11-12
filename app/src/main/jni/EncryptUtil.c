#include "EncryptUtil.h"
#include <string.h>
#include <jni.h>
#include <openssl/aes.h>
 /*  * Class: io_github_yanbober_ndkapplication_NdkJniUtils  * Method: getCLanguageString  * Signature: ()Ljava/lang/String;  */
JNIEXPORT jstring JNICALL Java_com_marsthink_app_utils_EncryptUtil_aes_1encrypt (JNIEnv * env,jobject obj, jstring string)
{
 //将需要加密的字符串转化为const char*类型
        const char* str = (*env)->GetStringUTFChars(env,string, 0);
       //密钥字符串转化成char*
        char* key = "1234567890123456afhiu$^&682036490";

        int i;
        char source[LEN];
        char dst[LEN];
        memset((char*)source, 0 ,LEN);
        memset((char*)dst, 0 ,LEN);
        strcpy(source, str);
        if(!aes_encrypt(source,key,dst))//(in,key,out)//加密
        {
            printf("encrypt error\n");
        }
        char t[3];
        char*  tempStr ="";
        int realLen=LEN;
        for(i=LEN-1;!dst[i];i--){// 加密结果中可能包含‘\0’，而‘\0’是C++中字符串的结尾标志，所以为了保证‘\0’之后的密文可以被取出，从数组尾部开始往前，第一个不是‘\0’的元素就是我们要取的最后一个值
            realLen = i;
        }
        for(i= 0;i<=realLen-1;i+=1){//将加密结果转化为十六进制，拼接成字符串输出
            sprintf(t, "%x", (unsigned char)dst[i]);
            if((unsigned char)dst[i]<=0x0f){
                strcpy (tempStr,"0");
                strcat (tempStr,t);
            }else{
                strcpy (tempStr,t);
            }

        }
        return (*env)->NewStringUTF(env,tempStr);//通过JNI提供的转化方法将char*转化为jstring作为结果返回
}

JNIEXPORT jstring JNICALL Java_com_marsthink_app_utils_EncryptUtil_aes_1decrypt (JNIEnv * env,jobject obj, jstring string)
{
        char*  source = "response decrypt";
          return (*env)->NewStringUTF(env,source);
}

int aes_encrypt(char* in, char* key, char* out)
{
  AES_KEY aes;
  int len = strlen(in), en_len = 0;
  if (!in || !key || !out) return 0;

  if (AES_set_encrypt_key((unsigned char*)key, 128, &aes) < 0)
	 {
         return 0;
	 }

    while (en_len < len)
	 {
	  AES_encrypt((unsigned char*)in, (unsigned char*)out, &aes);
	  in += AES_BLOCK_SIZE;
	  out += AES_BLOCK_SIZE;
	  en_len += AES_BLOCK_SIZE;
	 }
	return 1;
  }
