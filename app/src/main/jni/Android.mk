LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
TARGET_PLATFORM := android-19
LOCAL_MODULE := jniencrypt
LOCAL_SRC_FILES := EncryptUtil.c
LOCAL_LDLIBS := -llog
NDK_APP_DST_DIR := ../jniLibs/$(TARGET_ARCH_ABI)
include $(BUILD_SHARED_LIBRARY)