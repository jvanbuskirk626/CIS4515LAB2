package edu.temple.peoplesmaprepublic;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView instructionView;
    EditText userText;
    Button uploadButton;
    public static final String EXTRA_MESSAGE = "UserName should be here";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userText=findViewById(R.id.nameField);
        uploadButton=findViewById(R.id.uploadButton);
        final Intent intent = new Intent(this, ActualMain.class);

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(this, ActualMain.class);
                String userName = userText.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, userName);
                startActivity(intent);
            }
        });


    }


}
