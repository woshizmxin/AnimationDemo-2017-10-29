#!/bin/sh
BUILD_DEBUG_PATH=../../../build/intermediates/classes/debug/
javah -o EncryptUtil.h -jni -classpath ${BUILD_DEBUG_PATH} com.marsthink.app.utils.EncryptUtil