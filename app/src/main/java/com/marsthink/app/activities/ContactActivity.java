package com.marsthink.app.activities;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.marsthink.app.R;
import com.marsthink.app.module.ContactInfoModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoumao on 2017/11/29.
 * Description:
 */

public class ContactActivity extends Activity {

    public static final int READ_CONTACT_CODE = 34;
    public static final int READ_CONTACT_REQUEST_CODE = 54;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        findViewById(R.id.contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestContactPermission();
            }
        });
    }

    private void requestContactPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},
                    READ_CONTACT_REQUEST_CODE);
        } else {
            startPickContact();
        }
    }

    private void startPickContact() {
        Uri uri = Uri.parse("content://contacts/people");
        Intent intent = new Intent(
                Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, READ_CONTACT_CODE);
    }

    private ContactInfoModule getPhoneContacts(Uri uri) {
        ContactInfoModule contactInfoModule = new ContactInfoModule();
        //得到ContentResolver对象
        ContentResolver cr = getContentResolver();
        Cursor cursor = null, phoneCur = null, emailCur = null;
        //取得电话本中开始一项的光标
        cursor = cr.query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            //取得联系人姓名
            contactInfoModule.name = cursor.getString(cursor.getColumnIndex(
                    ContactsContract.Contacts.DISPLAY_NAME));
            String contactId = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts._ID));
            //取得电话号码
            phoneCur = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                    null, null);

            phoneCur = cr.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                    null, null);
            if (phoneCur != null && phoneCur.moveToFirst()) {
                contactInfoModule.phone = phoneCur.getString(
                        phoneCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactInfoModule.phone = phoneCur.getString(phoneCur.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER));
            }

            //查询Email类型的数据操作
            emailCur = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId,
                    null, null);

            if (emailCur != null && emailCur.moveToFirst()) {
                contactInfoModule.email = emailCur.getString(emailCur.getColumnIndex(
                        ContactsContract.CommonDataKinds.Email.DATA));
            }
            if (emailCur != null) {
                emailCur.close();
            }
            if (phoneCur != null) {
                phoneCur.close();
            }
            if (cursor != null) {
                cursor.close();
            }
        } else {
            return null;
        }
        return contactInfoModule;
    }

    public void getContact(Uri contactData) {
        //获取联系人信息的Uri
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        //获取ContentResolver
        ContentResolver contentResolver = getContentResolver();
        //查询数据，返回Cursor
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        while (cursor.moveToNext()) {
            Map<String, Object> map = new HashMap<String, Object>();
            StringBuilder sb = new StringBuilder();
            //获取联系人的ID
            String contactId = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts._ID));
            //获取联系人的姓名
            String name = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            //构造联系人信息
            sb.append("contactId=").append(contactId).append(",Name=").append(name);
            map.put("name", name);
            String id = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts._ID));//联系人ID


            //查询电话类型的数据操作
            Cursor phones = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                    null, null);
            while (phones.moveToNext()) {
                String phoneNumber = phones.getString(phones.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER));
                //添加Phone的信息
                sb.append(",Phone=").append(phoneNumber);
                map.put("mobile", phoneNumber);
            }
            phones.close();


            //查询Email类型的数据操作
            Cursor emails = contentResolver.query(
                    ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId,
                    null, null);
            while (emails.moveToNext()) {
                String emailAddress = emails.getString(emails.getColumnIndex(
                        ContactsContract.CommonDataKinds.Email.DATA));
                //添加Email的信息
                sb.append(",Email=").append(emailAddress);
                Log.e("emailAddress", emailAddress);
                map.put("email", emailAddress);


            }
            emails.close();
            //Log.i("=========ddddddddddd=====", sb.toString());

            //查询==地址==类型的数据操作.StructuredPostal.TYPE_WORK
            Cursor address = contentResolver.query(
                    ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID + " = "
                            + contactId,
                    null, null);
            while (address.moveToNext()) {
                String workAddress = address.getString(address.getColumnIndex(
                        ContactsContract.CommonDataKinds.StructuredPostal.DATA));


                //添加Email的信息
                sb.append(",address").append(workAddress);
                map.put("address", workAddress);
            }
            address.close();
            //Log.i("=========ddddddddddd=====", sb.toString());

            //查询==公司名字==类型的数据操作.Organization.COMPANY  ContactsContract.Data.CONTENT_URI
            String orgWhere =
                    ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE
                            + " = ?";
            String[] orgWhereParams = new String[]{id,
                    ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE};
            Cursor orgCur = contentResolver.query(ContactsContract.Data.CONTENT_URI,
                    null, orgWhere, orgWhereParams, null);
            if (orgCur.moveToFirst()) {
                //组织名 (公司名字)
                String company = orgCur.getString(
                        orgCur.getColumnIndex(ContactsContract.CommonDataKinds.Organization.DATA));
                //职位
                String title = orgCur.getString(
                        orgCur.getColumnIndex(ContactsContract.CommonDataKinds.Organization.TITLE));
                sb.append(",company").append(company);
                sb.append(",title").append(title);
                map.put("company", company);
                map.put("title", title);
            }
            orgCur.close();
            list.add(map);
            Log.i("=========orgName=====", sb.toString());//查看所有的数据
            Log.e("=========map=====", map.toString());//有很多数据的时候，只会添加一条  例如邮箱，
        }

        Log.i("=========list=====", list.toString());//
        cursor.close();
    }


    public void obtainContact(Uri uri) {
        if (uri != null) {
            Cursor cursor = getContentResolver()
                    .query(uri,
                            new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                                    ContactsContract.CommonDataKinds.Phone.NUMBER},
                            null, null, null);
            while (cursor.moveToNext()) {
                String number = cursor.getString(0);
                String name = cursor.getString(1);
            }

        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        if (requestCode == READ_CONTACT_REQUEST_CODE
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startPickContact();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == READ_CONTACT_CODE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                return;
            }
            //处理返回的data,获取选择的联系人信息
            Uri uri = data.getData();
            ContactInfoModule contactInfoModule = getPhoneContacts(uri);
            getContact(uri);
            Log.d("jamal.jo",
                    "onActivityResult: " + contactInfoModule.email + contactInfoModule.name
                            + contactInfoModule.phone);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
