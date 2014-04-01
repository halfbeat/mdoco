package es.jcyl.cs.mdoco.security;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import es.jcyl.cs.mdoco.main.MainActivity;
import es.jcyl.cs.mdoco.util.AlertDialogManager;
import es.jcyl.cs.mdoco.R;

public class LoginActivity extends Activity {

    // Email, password edittext
    EditText txtUsername, txtPassword;
    // login button
    Button btnLogin;
    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();
    // Session Manager Class
    SessionManager session;
    SecurityServiceProxy securityService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Session Manager
        session = new SessionManager(getApplicationContext());

        securityService = new SecurityServiceProxy();

        // Email, Password input text
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();


        // Login button
        btnLogin = (Button) findViewById(R.id.btnLogin);


        // Login button click event
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                new LoginTask().execute(username, password);
            }
        });
    }

    private class LoginTask extends AsyncTask<String, Integer, Boolean> {
        private String message;
        private String detail;
        private ProgressDialog pdia;
        private Credentials credentials;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdia = new ProgressDialog(LoginActivity.this);
            pdia.setMessage("Autenticando ...");
            pdia.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            if (params[0].length() > 0 && params[1].trim().length() > 0) {
                try {
                    if (securityService.login(new Credentials(params[0], params[1])).getCode() == 0) {
                        session.createLoginSession("Android Hive", "anroidhive@gmail.com");
                        credentials = new Credentials(params[0],params[1]);
                        return true;
                    } else {
                        message = "Login failed..";
                        detail = "Username/Password is incorrect";
                    }
                } catch (Exception e) {
                    message = "Login failed..";
                    detail = e.getMessage();
                }
            } else {
                message = "Login failed..";
                detail = "Please enter username and password";
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            pdia.dismiss();
            if (result) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("credentials", credentials);
                startActivity(i);
                finish();
            } else {
                alert.showAlertDialog(LoginActivity.this, message, detail, false);
            }
        }
    }
}