package com.artamonov.placeurclient.activity.fragment;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.artamonov.placeurclient.R;
import com.artamonov.placeurclient.dto.RegisterInfo;
import com.artamonov.placeurclient.dto.TokenDTO;
import com.artamonov.placeurclient.service.ApiFactory;
import com.artamonov.placeurclient.service.AuthService;

import java.io.IOException;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    EditText mNicknameView;
    EditText mMailView;
    EditText mPasswordView;
    EditText mConfirmPasswordView;
    EditText mNameView;
    EditText mSurnameView;
    Spinner mCityView;
    View mProgressView;
    View mSignUpFormView;
    UserRegisterTask mTask;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        mNicknameView = (EditText) view.findViewById(R.id.nickname);
        mMailView = (EditText) view.findViewById(R.id.email);
        mPasswordView = (EditText) view.findViewById(R.id.password);
        mConfirmPasswordView = (EditText) view.findViewById(R.id.confirm_password);
        mNameView = (EditText) view.findViewById(R.id.name);
        mSurnameView = (EditText) view.findViewById(R.id.surname);
        mCityView = (Spinner) view.findViewById(R.id.city);
        mProgressView = view.findViewById(R.id.signup_progress);
        mSignUpFormView = view.findViewById(R.id.signup_form_layout);
        Button mSignUpButton = (Button) view.findViewById(R.id.login_sign_in_button);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegister();
            }
        });
        return view;
    }

    private void attemptRegister() {

        if (mTask != null) {
            return;
        }

        mNicknameView.setError(null);
        mMailView.setError(null);
        mPasswordView.setError(null);
        mConfirmPasswordView.setError(null);
        mNameView.setError(null);
        mSurnameView.setError(null);

        String nickname = mNicknameView.getText().toString();
        String mail = mMailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String confirmPassword = mConfirmPasswordView.getText().toString();
        String name = mNameView.getText().toString();
        String surname = mSurnameView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(nickname)) {
            mNicknameView.setError(getString(R.string.error_field_required));
            focusView = mNicknameView;
            cancel = true;
        } else if (!isNicknameValid(nickname)) {
            mNicknameView.setError(getString(R.string.error_invalid_login));
            focusView = mNicknameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mTask = new UserRegisterTask(nickname, password);
            mTask.execute((Void) null);
        }
    }

    private boolean isNicknameValid(String nickname) {
        return nickname.length() > 3;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 3;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mSignUpFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mSignUpFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mSignUpFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mSignUpFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        String nickname;
        String mail;
        String password;
        String name;
        String surname;
        UUID cityId;
        TokenDTO mToken;

        public UserRegisterTask(String nickname, String mail, String password, String name, String surname, UUID cityId) {
            this.nickname = nickname;
            this.mail = mail;
            this.password = password;
            this.name = name;
            this.surname = surname;
            this.cityId = cityId;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            AuthService service = ApiFactory.getAuthService();
            RegisterInfo registerInfo = new RegisterInfo(nickname, mail, password, name, surname, cityId);
            Call<TokenDTO> call = service.signUp(registerInfo);
            try {
                Response<TokenDTO> response = call.execute();
                System.out.println(response.message());
                if (response.isSuccessful()) {
                    mToken = response.body();
                }
                if (mToken == null) return false;
            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
                Toast toast = Toast.makeText(getContext(), "Проблемы с соединением", Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
            return true;
        }



        @Override
        protected void onCancelled() {
            mTask = null;
            showProgress(false);
        }
    }
}
