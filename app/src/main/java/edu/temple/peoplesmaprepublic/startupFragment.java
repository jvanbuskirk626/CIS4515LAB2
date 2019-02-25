package edu.temple.peoplesmaprepublic;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class startupFragment extends Fragment {

    TextView instructionView;
    EditText userText;
    Button uploadButton;
    Context parent;


    public startupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.parent=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_startup, container, false);
        userText=v.findViewById(R.id.nameField);
        uploadButton=v.findViewById(R.id.uploadButton);

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=userText.getText().toString();
                ((getUsername) parent).usernameEntered(userName);
            }
        });

        return v;
    }

    interface getUsername{
        void usernameEntered(String user);
    }

}
