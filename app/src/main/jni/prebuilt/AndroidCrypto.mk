LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := openssl_crypto
LOCAL_SRC_FILES := libcrypto.so

include $(PREBUILT_SHARED_LIBRARY)