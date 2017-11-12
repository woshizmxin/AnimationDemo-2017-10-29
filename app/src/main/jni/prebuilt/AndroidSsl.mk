LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := openssl_ssl
LOCAL_SRC_FILES := libssl.so
LOCAL_SRC_FILES += libcrypto.so


include $(PREBUILT_SHARED_LIBRARY)