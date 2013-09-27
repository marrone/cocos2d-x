LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := cocos2dxouya_static

LOCAL_MODULE_FILENAME := libcocos2douya

LOCAL_SRC_FILES := \
../android/CCApplication.cpp \
../android/CCCommon.cpp \
../android/CCDevice.cpp \
../android/CCEGLView.cpp \
../android/CCFileUtilsAndroid.cpp \
../android/CCImage.cpp \
nativeactivity.cpp \
../android/jni/DPIJni.cpp \
../android/jni/IMEJni.cpp \
../android/jni/Java_org_cocos2dx_lib_Cocos2dxBitmap.cpp \
../android/jni/Java_org_cocos2dx_lib_Cocos2dxHelper.cpp \
../android/jni/JniHelper.cpp \
jni/Java_com_levire_ouyabind_Controller.cpp \
CCOuyaController.cpp \

LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH) 

LOCAL_C_INCLUDES := $(LOCAL_PATH) \
                    $(LOCAL_PATH)/../android \
                    $(LOCAL_PATH)/../.. \
                    $(LOCAL_PATH)/../../include \
                    $(LOCAL_PATH)/../../kazmath/include \
                    $(LOCAL_PATH)/../../platform/third_party/common/etc

LOCAL_LDLIBS := -lGLESv1_CM \
                -lGLESv2 \
                -lEGL \
                -llog \
                -lz \
                -landroid

LOCAL_EXPORT_LDLIBS := -lGLESv1_CM \
                       -lGLESv2 \
                       -lEGL \
                       -llog \
                       -lz \
                       -landroid

LOCAL_WHOLE_STATIC_LIBRARIES := android_native_app_glue cocos_libpng_static cocos_jpeg_static cocos_libxml2_static cocos_libtiff_static cocos_libwebp_static

# define the macro to compile through support/zip_support/ioapi.c
LOCAL_CFLAGS   := -DOUYA=1
LOCAL_EXPORT_CFLAGS   := -DOUYA=1

include $(BUILD_STATIC_LIBRARY)

$(call import-module,libjpeg)
$(call import-module,libpng)
$(call import-module,libtiff)
$(call import-module,libwebp)
$(call import-module,android/native_app_glue)
