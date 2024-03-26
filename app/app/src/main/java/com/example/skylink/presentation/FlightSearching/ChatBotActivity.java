package com.example.skylink.presentation.FlightSearching;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.skylink.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class ChatBotActivity extends AppCompatActivity {

    private LinearLayout chatBubbleLayout;
    private EditText userInputEditText;

    private final HashMap<String, String> keywordReplies = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        chatBubbleLayout = findViewById(R.id.chatBubbleLayout);
        userInputEditText = findViewById(R.id.userInputEditText);
        Button sendButton = findViewById(R.id.sendButton);

        keywordReplies.put("hello", "Hello! How can I help you?");
        keywordReplies.put("how are you", "I'm doing well, thank you!");
        keywordReplies.put("cancel booking", "You can modify your bookings in the booking page, or call 123-456-7890");
        keywordReplies.put("thank you","You're welcome");
        sendButton.setOnClickListener(v -> sendMessage());
    }

    private void sendMessage() {
        String userMessage = userInputEditText.getText().toString().trim();
        if (!userMessage.isEmpty()) {
            addMessageToChat(userMessage, true);
            String reply = generateReply(userMessage);
            addMessageToChat(reply, false);
            userInputEditText.getText().clear();
        }
    }

    private String generateReply(String userInput) {
        userInput = userInput.toLowerCase();

        for (String keyword : keywordReplies.keySet()) {
            if (userInput.contains(keyword)) {
                return keywordReplies.get(keyword);
            }
        }

        return "Sorry, I didn't understand that.";
    }

    private void addMessageToChat(String message, boolean isUser) {
        TextView textView = new TextView(this);
        textView.setText(message);
        textView.setTextSize(30);
        textView.setPadding(16, 8, 16, 8);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        if (isUser) {
            params.setMargins(1100, 10, 10, 10);
            textView.setBackground(ContextCompat.getDrawable(this, R.drawable.user_message_bg));
        } else {
            params.setMargins(10, 10, 100, 10);
            textView.setBackground(ContextCompat.getDrawable(this, R.drawable.bot_message_bg));
        }
        textView.setLayoutParams(params);
        chatBubbleLayout.addView(textView);

        TextView timeTextView = new TextView(this);
        timeTextView.setText(getCurrentTime());
        timeTextView.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray));
        LinearLayout.LayoutParams timeParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        if (isUser) {
            timeParams.setMargins(100, 0, 10, 0);
        } else {
            timeParams.setMargins(10, 0, 100, 0);
        }
        timeTextView.setLayoutParams(timeParams);
        chatBubbleLayout.addView(timeTextView);
    }

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return sdf.format(new Date());
    }
}
