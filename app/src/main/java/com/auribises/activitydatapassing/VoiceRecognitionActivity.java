package com.auribises.activitydatapassing;

import android.app.ProgressDialog;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VoiceRecognitionActivity extends AppCompatActivity implements View.OnClickListener, RecognitionListener{

    @BindView(R.id.textViewData)
    TextView txtData;

    @BindView(R.id.buttonSpeak)
    Button btnSpeak;

    SpeechRecognizer speechRecognizer;

    ProgressDialog progressDialog;

    TextToSpeech tts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_recognition);

        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Listening...");

        btnSpeak.setOnClickListener(this);

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(this);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){

                }else{
                    Toast.makeText(VoiceRecognitionActivity.this,"Please Enable TTS Service first",Toast.LENGTH_LONG).show();
                }
            }
        });

        String message = "Its an Awesome Day ! Welcome !";
        tts.speak(message,TextToSpeech.QUEUE_FLUSH,null);
        //tts.speak(message,TextToSpeech.QUEUE_FLUSH,null,null);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.rotate_anim);
        btnSpeak.startAnimation(animation);
    }

    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {
        progressDialog.show();
    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {
        progressDialog.dismiss();
    }

    @Override
    public void onError(int error) {

    }

    @Override
    public void onResults(Bundle results) {

        ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

        if(data !=null && data.size()>0) {
            String closestMatch = data.get(0);
            txtData.setText(closestMatch);

            if(closestMatch.contains("help") && closestMatch.contains("mom") && closestMatch.contains("message")){
                // SmsManager
            }

        }

    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }

    @Override
    public void onClick(View v) {
        speechRecognizer.startListening(RecognizerIntent.getVoiceDetailsIntent(this));
    }
}
