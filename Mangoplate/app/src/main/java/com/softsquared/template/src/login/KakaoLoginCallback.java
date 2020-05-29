//package com.softsquared.template.src.login;
//
//import android.content.Intent;
//
//import com.kakao.auth.ApiErrorCode;
//import com.kakao.auth.ISessionCallback;
//
//import com.kakao.network.ErrorResult;
//import com.kakao.usermgmt.UserManagement;
//import com.kakao.usermgmt.callback.MeV2ResponseCallback;
//import com.kakao.usermgmt.response.MeV2Response;
//import com.kakao.util.exception.KakaoException;
//
//
//import androidx.annotation.Nullable;
//
//public class KakaoLoginCallback implements ISessionCallback {
//
//
//    @Override
//    public void onSessionOpened() {
//        UserManagement.getInstance().me(new MeV2ResponseCallback() {
//            @Override
//            public void onFailure(ErrorResult errorResult) {
//                //로그인에 실패했을 때. 인터넷 연결이 불안정한 경우도 여기에 해당한다.
//                int result = errorResult.getErrorCode();
//
//                if(result == ApiErrorCode.CLIENT_ERROR_CODE) {
//                    Toast.makeText(getApplicationContext(), "네트워크 연결이 불안정합니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
//                    finish();
//                } else {
//                    Toast.makeText(getApplicationContext(),"로그인 도중 오류가 발생했습니다: "+errorResult.getErrorMessage(),Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onSessionClosed(ErrorResult errorResult) {
//                //로그인 도중 세션이 비정상적인 이유로 닫혔을 때
//            }
//
//            @Override
//            public void onSuccess(MeV2Response result) {
//                //로그인에 성공했을 때
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                intent.putExtra("name", result.getNickname());
//                intent.putExtra("profile", result.getProfileImagePath());
//                startActivity(intent);
//                finish();
//            }
//        });
//
//    }
//
//    @Override
//    public void onSessionOpenFailed(KakaoException e) {
//
//    }
//}
