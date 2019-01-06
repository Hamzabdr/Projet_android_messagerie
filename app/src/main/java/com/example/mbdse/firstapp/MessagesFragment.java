package com.example.mbdse.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class MessagesFragment extends Fragment {
    public MessagesFragment(){}
    TextView tv;
    String txt;
    ImageButton btn;
    EditText msgtext;
    ListView messages;
    MessageAdapter msgAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get intent, action and MIME type
        msgAdapter = new MessageAdapter(this.getContext());
        Intent intent = getActivity().getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null){
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            }
        }}

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            // Update UI to reflect text being shared
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();

        }
    }


    @Override
    public void onResume() {
        super.onResume();
        //tv.setText(txt);

    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_sec,container, false);
        btn = (ImageButton) v.findViewById(R.id.btn_send);
        msgtext = (EditText) v.findViewById(R.id.msg_txt);
        messages = (ListView) v.findViewById(R.id.messages_view);
        messages.setAdapter(msgAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });        //tv = (TextView) v.findViewById(R.id.TXT);
        return v;
    }

    public void sendMessage() {
        String body = msgtext.getText().toString();
        if (body.length() > 0) {

        Message msg = new Message(body,"Hamza",true);
            msgtext.getText().clear();
            msgtext.setText("");
            msgAdapter.add(msg);
            messages.setSelection(messages.getCount() - 1);

        }
    }
    public void setText(String txt)
    {
        this.txt= txt;
        if (tv !=null)
            tv.setText(txt);
    }




}
