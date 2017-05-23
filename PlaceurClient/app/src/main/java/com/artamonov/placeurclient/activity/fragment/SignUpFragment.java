package com.artamonov.placeurclient.activity.fragment;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
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
import com.artamonov.placeurclient.activity.MainActivity;
import com.artamonov.placeurclient.dto.AuthToken;
import com.artamonov.placeurclient.dto.UserDTO;
import com.artamonov.placeurclient.service.ApiFactory;
import com.artamonov.placeurclient.service.AuthService;
import com.artamonov.placeurclient.store.Store;

import java.io.IOException;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    EditText mNicknameView;
    EditText mPasswordView;
    EditText mConfirmPasswordView;
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
        mPasswordView = (EditText) view.findViewById(R.id.password);
        mConfirmPasswordView = (EditText) view.findViewById(R.id.confirm_password);
        mCityView = (Spinner) view.findViewById(R.id.city);
        mProgressView = view.findViewById(R.id.signup_progress);
        mSignUpFormView = view.findViewById(R.id.signup_form_layout);
        Button mSignUpButton = (Button) view.findViewById(R.id.sign_up_button);
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
        mPasswordView.setError(null);
        mConfirmPasswordView.setError(null);

        String nickname = mNicknameView.getText().toString();
        String password = mPasswordView.getText().toString();
        String confirmPassword = mConfirmPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        if (!password.equals(confirmPassword)) {
            mConfirmPasswordView.setError(getString(R.string.error_dublicate_password));
            focusView = mConfirmPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
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
            mTask = new UserRegisterTask(nickname, password, UUID.fromString("69f63779-d73c-8187-7aee-522c85ccbf6f"));
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
        String password;
        UUID cityId;
        AuthToken mToken;

        public UserRegisterTask(String nickname, String password, UUID cityId) {
            this.nickname = nickname;
            this.password = password;
            this.cityId = cityId;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            AuthService service = ApiFactory.getAuthService();
            Call<AuthToken> call = service.signUp(nickname, password, cityId);
            try {
                Response<AuthToken> response = call.execute();
                System.out.println(response.message());
                if (response.isSuccessful()) {
                    mToken = response.body();
                }
                if (mToken == null) return false;
            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mTask = null;
            showProgress(false);

            if (success && mToken.getName().equals("ok")) {
                Activity parentActivity = getActivity();
                System.out.println(mToken.getUser());
                Store.setUser(mToken.getUser(), getActivity().getApplication().getApplicationContext());
                parentActivity.startActivity(new Intent(parentActivity, MainActivity.class));
                parentActivity.finish();
            } else if (mToken != null) {
                Toast toast = Toast.makeText(getContext(), "Имя пользователя занято", Toast.LENGTH_SHORT);
                toast.show();
                mNicknameView.requestFocus();
            } else {
                Toast toast = Toast.makeText(getContext(), "Проблемы с соединением", Toast.LENGTH_SHORT);
                toast.show();
            }
        }



        @Override
        protected void onCancelled() {
            mTask = null;
            showProgress(false);
        }
    }
}
