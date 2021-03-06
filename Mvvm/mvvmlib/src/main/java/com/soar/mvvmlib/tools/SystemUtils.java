package com.soar.mvvmlib.tools;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;

import java.io.File;

/**
 * 系统级调用帮助类
 *  create by gaofei
 */
public class SystemUtils {

    private static final String TAG = "SystemUtils";

    public static final int CROP_IMAGE_WIDTH = 150;
    public static final int CROP_IMAGE_HEIGHT = 150;


    /**
     * 打开其他应用
     *
     * @param packageName 目标应用包名
     * @param className   目标应用类名
     * @param context
     */
    public static void startAppWithPackageName(String packageName, String className, Context context) {

        Intent intent = new Intent();
        ComponentName cmp = new ComponentName(packageName, className);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(cmp);
        context.startActivity(intent);
    }

    /**
     * 调用系统相机  通过startActivityResult返回结果   ，若拍照成功  通过返回的intent getData() 获取所拍摄图片uri
     *
     * @param activity
     * @param requestCode 约定的code 便于在onActivityResult()方法中辨识结果
     * @param imgFile     存放所拍摄图片的文件地址（目录+文件名） store the requested image or video.
     */
    public static void callCamera(Activity activity, int requestCode, File imgFile) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imgFile));
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 调用系统相册  通过startActivityResult返回结果   ，若拍照成功  通过返回的intent getData() 获取所拍摄图片uri
     *
     * @param activity
     * @param requestCode 约定的code 便于在onActivityResult()方法中辨识结果
     */
    public static void callGallery(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 调用系统级裁剪  通过startActivityResult返回结果 ，若裁剪成功，通过返回的Intent  getParcelable() 方法获取裁剪所得bitmap
     *
     * @param uri         本地图片uri
     * @param requestCode 约定的code 便于在onActivityResult()方法中辨识结果
     */
    public static void cropPhoto(Uri uri, int requestCode, Activity activity) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", CROP_IMAGE_WIDTH);
        intent.putExtra("outputY", CROP_IMAGE_HEIGHT);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 跳转至系统设置  默认调至设置首页
     *
     * @param context
     * @param action  具体到设置某详情页   通过Settings. 进行选择 ， 传入null 则跳转至设置首页
     */
    public static void dropToSetting(Context context, String action) {

        if (TextUtils.isEmpty(action)) {
            context.startActivity(new Intent(Settings.ACTION_SETTINGS));
            return;
        }
        context.startActivity(new Intent(action));
    }


    /**
     *  拨打电话
     * @param context
     * @param phone 电话号码
     */
    public static void makeAcall(Context context, String phone){

        Uri uri = Uri.parse("tel:"+phone);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        context.startActivity(intent);
    }


    /**
     * 选择联系人
     * @param context
     */
    public static void choicePeople(Activity context , int requestCode){
        Intent intent = new Intent(Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI);
        context.startActivityForResult(intent, requestCode);
    }


    /**
     * 获取手机号码
     * @param context
     * @param cursor
     * @return
     */
    public static String getContactPhone(Context context , Cursor cursor) {
        // TODO Auto-generated method stub
        int phoneColumn = cursor
                .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
        int phoneNum = cursor.getInt(phoneColumn);
        String result = "";
        if (phoneNum > 0) {
            // 获得联系人的ID号
            int idColumn = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            String contactId = cursor.getString(idColumn);
            // 获得联系人电话的cursor
            Cursor phone = context.getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "="
                            + contactId, null, null);
            if (phone.moveToFirst()) {
                for (; !phone.isAfterLast(); phone.moveToNext()) {
                    int index = phone
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    int typeindex = phone
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE);
                    int phone_type = phone.getInt(typeindex);
                    String phoneNumber = phone.getString(index);
                    result = phoneNumber;
//                  switch (phone_type) {//此处请看下方注释 这个type是区分手机  电话 住在电话
//                  case 2:
//                      result = phoneNumber;
//                      break;
//
//                  default:
//                      break;
//                  }
                }
                if (!phone.isClosed()) {
                    phone.close();
                }
            }
        }
        return result;
    }

}
