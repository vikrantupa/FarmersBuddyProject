package main.buddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;

public class FirstActivity extends AppCompatActivity {
  public static boolean variable = true;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (!user.check()) {
      setContentView(R.layout.first_activity);
      ((ProgressBar)findViewById(R.id.loading)).setVisibility(View.GONE);
      Button login = (Button)findViewById(R.id.BTN);
      TextView output = (TextView)findViewById(R.id.output);
      login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          user.cnt++;
          ((ProgressBar)findViewById(R.id.loading)).setVisibility(View.VISIBLE);
          hideKeyBoard(FirstActivity.this, v);
          String id = ((EditText)findViewById(R.id.id)).getText().toString();
          String pw = ((EditText)findViewById(R.id.pw)).getText().toString();
  //        output.setText("working "+user.cnt+ " times.");
          variable = false;
          new Thread(new Runnable() {
            public void run () {
              user.login(getString(R.string.domain),id,pw);
              variable = true;
            }
          }).start();
          
          while (!variable);
          ((ProgressBar)findViewById(R.id.loading)).setVisibility(View.GONE);
          if (!user.check())
            Toast.makeText( FirstActivity.this, "Login failed. Try again", Toast.LENGTH_SHORT).show();
          FirstActivity.this.recreate();
        }
      });
      ConstraintLayout layout = (ConstraintLayout)findViewById(R.id.first_activity);
      layout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          hideKeyBoard(FirstActivity.this, v);
        }
      });
    } else {
      setContentView(R.layout.menu_layout);
      ((TextView)findViewById(R.id.menutext)).setText("welcome to main");
      Button weather = (Button)findViewById(R.id.weatherBTN);
      weather.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent = new Intent(FirstActivity.this, weather.class);
          startActivity(intent);
        }
      });
    }
  }
  public void hideKeyBoard(android.app.Activity activity, View view) {
    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
  }
}
