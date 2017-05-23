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
import android.widget.Toast;

import com.artamonov.placeurclient.R;
import com.artamonov.placeurclient.activity.MainActivity;
import com.artamonov.placeurclient.dto.AuthToken;
import com.artamonov.placeurclient.dto.UserDTO;
import com.artamonov.placeurclient.service.ApiFactory;
import com.artamonov.placeurclient.service.AuthService;
import com.artamonov.placeurclient.store.Store;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {

    EditText mNicknameView;
    EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private UserLoginTask mAuthTask = null;

    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        mNicknameView = (EditText) view.findViewById(R.id.nickname);
        mPasswordView = (EditText) view.findViewById(R.id.password);

        Button mLoginSignInButton = (Button) view.findViewById(R.id.login_sign_in_button);
        mLoginSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = view.findViewById(R.id.login_form);
        mProgressView = view.findViewById(R.id.login_progress);

        return view;
    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        mNicknameView.setError(null);
        mPasswordView.setError(null);

        String nickname = mNicknameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

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
            mAuthTask = new UserLoginTask(nickname, password);
            mAuthTask.execute((Void) null);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
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
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private boolean isNicknameValid(String nickname) {
        return nickname.length() > 3;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 3;
    }

    private class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mNickname;
        private final String mPassword;
        private AuthToken mToken;

        UserLoginTask(String login, String password) {
            mNickname = login;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            AuthService service = ApiFactory.getAuthService();
            Call<AuthToken> call = service.signIn(mNickname, mPassword);
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
            mAuthTask = null;
            showProgress(false);

            if (success && mToken.getName().equals("ok")) {
                Activity parentActivity = getActivity();
                System.out.println(mToken.getUser());
                Store.setUser(mToken.getUser(), getActivity().getApplication().getApplicationContext());
                parentActivity.startActivity(new Intent(parentActivity, MainActivity.class));
                parentActivity.finish();
            } else if (mToken != null) {
                Toast toast = Toast.makeText(getContext(), "Неправильное имя пользователя/пароль.", Toast.LENGTH_SHORT);
                toast.show();
                mPasswordView.requestFocus();
            } else {
                Toast toast = Toast.makeText(getContext(), "Проблемы с соединением", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
