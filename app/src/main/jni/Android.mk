LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
TARGET_PLATFORM := android-19
LOCAL_MODULE := jniencrypt
LOCAL_SRC_FILES := EncryptUtil.c
LOCAL_C_INCLUDES += $(LOCAL_PATH)/include/ $(LOCAL_PATH)/include/openssl/
LOCAL_LDLIBS += -L$(LOCAL_PATH)/ -lssl  -lcrypto
LOCAL_LDLIBS += -llog

NDK_APP_DST_DIR := ../jniLibs/$(TARGET_ARCH_ABI)
include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := openssl_ssl
LOCAL_SRC_FILES := libssl.so


include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := openssl_crypto
LOCAL_SRC_FILES := libcrypto.so

include $(PREBUILT_SHARED_LIBRARY)