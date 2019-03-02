package edu.temple.peoplesmaprepublic;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GetUserName extends AppCompatActivity {

    TextView instructionView;
    EditText userText;
    Button uploadButton;
    public static final String EXTRA_MESSAGE = "UserName should be here";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_name);

        userText=findViewById(R.id.nameField);
        uploadButton=findViewById(R.id.uploadButton);

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",userText.getText().toString());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

    }
}
