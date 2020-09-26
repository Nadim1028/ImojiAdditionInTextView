package com.example.imojiadd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;

public class ImojiActivity extends AppCompatActivity {

    EmojiconEditText emojiconEditText;
    ImageView emojiButton;
    ImageButton submitButton;
    View rootView;
    TextView myTextView;
    EmojIconActions emojIcon;
    private final String emo_regex = "([\\u20a0-\\u32ff\\ud83c\\udc00-\\ud83d\\udeff\\udbb9\\udce5-\\udbb9\\udcee])";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imoji);

        emojiconEditText = findViewById(R.id.text_send);
        emojiButton = findViewById(R.id.emoji_btn);
        submitButton = findViewById(R.id.btn_send);
        myTextView = findViewById(R.id.myTextView);
        rootView = findViewById(R.id.root_view);
        emojIcon = new EmojIconActions(this, rootView, emojiconEditText, emojiButton);
        emojIcon.ShowEmojIcon();

        emojIcon.setKeyboardListener(new EmojIconActions.KeyboardListener() {
            @Override
            public void onKeyboardOpen() {
                Log.e("Keyboard", "open");
            }
            @Override
            public void onKeyboardClose() {
                Log.e("Keyboard", "close");
            }
        });


        emojIcon.addEmojiconEditTextList(emojiconEditText);

        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String newText = emojiconEditText.getText().toString();
                myTextView.setText(newText);

              /*  Matcher matcher = Pattern.compile(emo_regex).matcher(newText);
                while (matcher.find()) {
                    System.out.println("Matcher Group ========== "+matcher.group().g);
                }*/

                System.out.println("IS Emoji ========= "+isEmoji(newText));

                emojiconEditText.setText("");
            }
        });



    }

    private boolean isEmoji(String message) {
        return message.matches("(?:[\uD83C\uDF00-\uD83D\uDDFF]|[\uD83E\uDD00-\uD83E\uDDFF]|" +
                "[\uD83D\uDE00-\uD83D\uDE4F]|[\uD83D\uDE80-\uD83D\uDEFF]|" +
                "[\u2600-\u26FF]\uFE0F?|[\u2700-\u27BF]\uFE0F?|\u24C2\uFE0F?|" +
                "[\uD83C\uDDE6-\uD83C\uDDFF]{1,2}|" +
                "[\uD83C\uDD70\uD83C\uDD71\uD83C\uDD7E\uD83C\uDD7F\uD83C\uDD8E\uD83C\uDD91-\uD83C\uDD9A]\uFE0F?|" +
                "[\u0023\u002A\u0030-\u0039]\uFE0F?\u20E3|[\u2194-\u2199\u21A9-\u21AA]\uFE0F?|[\u2B05-\u2B07\u2B1B\u2B1C\u2B50\u2B55]\uFE0F?|" +
                "[\u2934\u2935]\uFE0F?|[\u3030\u303D]\uFE0F?|[\u3297\u3299]\uFE0F?|" +
                "[\uD83C\uDE01\uD83C\uDE02\uD83C\uDE1A\uD83C\uDE2F\uD83C\uDE32-\uD83C\uDE3A\uD83C\uDE50\uD83C\uDE51]\uFE0F?|" +
                "[\u203C\u2049]\uFE0F?|[\u25AA\u25AB\u25B6\u25C0\u25FB-\u25FE]\uFE0F?|" +
                "[\u00A9\u00AE]\uFE0F?|[\u2122\u2139]\uFE0F?|\uD83C\uDC04\uFE0F?|\uD83C\uDCCF\uFE0F?|" +
                "[\u231A\u231B\u2328\u23CF\u23E9-\u23F3\u23F8-\u23FA]\uFE0F?)+");
    }

    public void nextImojiView(View view) {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}