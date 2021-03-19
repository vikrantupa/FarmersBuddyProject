package main.buddy;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class weather extends AppCompatActivity {
  @Override
  public void onCreate(Bundle savedInstance) {
    super.onCreate(savedInstance);
    setContentView(R.layout.weather);
    TextView output = (TextView)findViewById(R.id.weatherTXT);
    output.setText(user.getWeather());
  }
}
