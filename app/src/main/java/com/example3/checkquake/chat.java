package com.example3.checkquake;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//import com.github.kittinunf.fuel.Fuel;
//import com.github.kittinunf.fuel.core.FuelError;
//import com.github.kittinunf.fuel.core.Handler;
//import com.github.kittinunf.fuel.core.Request;
//import com.github.kittinunf.fuel.core.Response;
//import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
//import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
//import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
//import com.ibm.watson.developer_cloud.http.ServiceCallback;

//import java.util.HashMap;
//import java.util.Map;

public class chat extends AppCompatActivity {

//    private static final String TAG = "MainActivity";
//    private ConversationService myConversationService = null;
    private TextView chatDisplayTV;
    private EditText userStatementET;
//    private final String IBM_USERNAME = "Enter IBM Username";
//    private final String IBM_PASSWORD = "Enter IBM Password";
//    private final String IBM_WORKSPACE_ID ="Enter Workspace id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        chatDisplayTV = findViewById(R.id.tv_chat_display);
        userStatementET = findViewById(R.id.et_user_statement);
        chatDisplayTV.append(
                Html.fromHtml("<p><b>BOT:</b> " +
                        "Welcome to Checkquake" + "</p>"));

        //instantiating IBM Watson Conversation Service
//        myConversationService =
//                new ConversationService(
//                        "2017-12-06",
//                        IBM_USERNAME,
//                        IBM_PASSWORD);
        userStatementET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView tv, int action, KeyEvent keyEvent) {
                if (action == EditorInfo.IME_ACTION_DONE) {



                    //show the user statement
                    final String userStatement = userStatementET.getText().toString();
                    chatDisplayTV.append(
                            Html.fromHtml("<p><b>YOU:</b> " + userStatement + "</p>")
                    );

                    if(userStatement.toLowerCase().equals("hii")){
                        chatDisplayTV.append(
                                Html.fromHtml("<p><b>BOT:</b> " +
                                        "Hello, Are you in trouble ?" + "</p>"));


                    }
                    else if(userStatement.toLowerCase().equals("yes")){
                        chatDisplayTV.append(
                                Html.fromHtml("<p><b>BOT:</b> " +
                                        "Are you indoor or outdoor ?" + "</p>"));
                    }



                    else if(userStatement.toLowerCase().equals("no")){
                        chatDisplayTV.append(
                                Html.fromHtml("<p><b>BOT:</b> " +
                                        "How can I help You ?" + "</p>"));
                    }


                    else if(userStatement.toLowerCase().equals("outdoor")){
                        chatDisplayTV.append(
                                Html.fromHtml("<p><b>BOT:</b> " +
                                        "Try to find a car and lock the car and close all the windows of the car. Try to find an open area as to stay away from anything that can fall on you" + "</p>"));
                        chatDisplayTV.append(
                                Html.fromHtml("<p><b>BOT:</b> " +
                                        "What are some of your surrounding objects ? " + "</p>"));
                    }


                    else if(userStatement.toLowerCase().equals("indoor")){
                        chatDisplayTV.append(
                                Html.fromHtml("<p><b>BOT:</b> " +
                                        "find a table and go below it, don’t go into the kitchen as things can fall upon you, stay away from the walls as they can fall upon you. Stay away from thing made of glass." + "</p>"));
                    }





                    else if(userStatement.toLowerCase().equals("table")){
                        chatDisplayTV.append(
                                Html.fromHtml("<p><b>BOT:</b> " +
                                        "go below the table" + "</p>"));
                    }else if(userStatement.toLowerCase().equals("electric pole")){
                        chatDisplayTV.append(
                                Html.fromHtml("<p><b>BOT:</b> " +
                                        "Hello" + "</p>"));
                    }else if(userStatement.toLowerCase().equals("open area")){
                        chatDisplayTV.append(
                                Html.fromHtml("<p><b>BOT:</b> " +
                                        "go in the open area and stay away from everything that might fall\n" + "</p>"));
                    }
                    else if(userStatement.toLowerCase().equals("ocean")){
                        chatDisplayTV.append(
                                Html.fromHtml("<p><b>BOT:</b> " +
                                        "Go away as soon as possible as there is a chance of tsunami\n" + "</p>"));
                    }



                    userStatementET.setText("");
//                    MessageRequest request = new MessageRequest.Builder()
//                            .inputText(userStatement)
//                            .build();
//                    myConversationService
//                            .message(IBM_WORKSPACE_ID, request)
//                            .enqueue(new ServiceCallback<MessageResponse>() {
//                                @Override
//                                public void onResponse(MessageResponse response) {
//                                    final String botStatement = response.getText().get(0);
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            chatDisplayTV.append(
//                                                    Html.fromHtml("<p><b>BOT:</b> " +
//                                                            botStatement + "</p>")); }});
//                                    if (response.getIntents().get(0).getIntent().endsWith("Joke")) {
//                                        final Map<String, String> params = new HashMap<String, String>() {{
//                                            put("Accept", "text/plain"); }};
//                                        Fuel.get("https://icanhazdadjoke.com/").header(params)
//                                                .responseString(new Handler<String>() {
//                                                    @Override
//                                                    public void success(Request request, Response response, String body) {
//                                                        Log.d(TAG, "" + response + " ; " + body);
//                                                        chatDisplayTV.append(
//                                                                Html.fromHtml("<p><b>BOT:</b> " +
//                                                                        body + "</p>")); }
//                                                    @Override
//                                                    public void failure(Request request, Response response, FuelError fuelError) {
//                                                    }}); } }
//                                @Override
//                                public void onFailure(Exception e) {
//                                    Log.d(TAG, e.getMessage());
//                                }});
                }return false;
            }
        }); }}