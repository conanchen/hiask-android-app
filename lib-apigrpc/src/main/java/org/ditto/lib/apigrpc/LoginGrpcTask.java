package org.ditto.lib.apigrpc;

import android.os.AsyncTask;
import android.util.Log;

import org.ditto.api.grpc.LoginRequest;
import org.ditto.api.grpc.LoginResponse;
import org.ditto.api.grpc.LoginServiceGrpc;

import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class LoginGrpcTask extends AsyncTask<Void, Void, LoginResponse> {
    private String accessToken;
    private String expires;
    private String openId;
    private ManagedChannel channel;

    public LoginGrpcTask(String accessToken) {
        super();
        this.accessToken = accessToken;
    }

    @Override
    protected LoginResponse doInBackground(Void... voids) {
        try {
            LoginRequest request = LoginRequest.newBuilder()
                    .setAccessToken(accessToken)
                    .setClientId("test")
                    .setClientSecret("test")
                    .setLoginType(LoginRequest.LoginType.QQ)
                    .build();

            channel = ManagedChannelBuilder.forAddress(BuildConfig.GRPC_SERVER_HOST, BuildConfig.GRPC_SERVER_PORT).usePlaintext(true).build();
            LoginServiceGrpc.LoginServiceBlockingStub stub = LoginServiceGrpc.newBlockingStub(channel);

            return stub.login(request);
        } catch (Exception e) {
            Log.e("LoginActivity", e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(LoginResponse loginResponse) {
        try {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (loginResponse != null) {
            Log.i("LoginActivity", "sessionId: " + loginResponse.getSessionId());
            //Snackbar.make(usernameEditText, "sessionId: " + sessionResponse.getSessionId(), Toast.LENGTH_LONG).show();
        }
    }
}

