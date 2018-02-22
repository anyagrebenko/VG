//package com.example.user.vg;
//
//import android.content.Context;
//import android.media.AudioManager;
//import android.speech.tts.TextToSpeech;
//
//import java.util.HashMap;
//import java.util.Locale;
//
///**
// * Created by user on 15.02.2018.
// */
//
//public class Speaker implements TextToSpeech.OnInitListener {
//    private TextToSpeech tts;
//    private boolean ready = false;
//    private boolean allowed = false;
//
//    public Speaker (Context context) {
//        tts = new TextToSpeech(context, this);
//    }
//
//    public boolean isAllowed(){
//        return allowed;
//    }
//
//    public void allow(boolean allowed) {
//        this.allowed = allowed;
//    }
//
//    @Override
//    public void onInit(int status) {
//        if(status == TextToSpeech.SUCCESS){
//            //выбираем локализацию
//            tts.setLanguage(Locale.US);
//            ready = true;
//        }else{
//            ready = false;
//        }
//    }
//
//    public void speak(String text){
//        //Речь начнется, только если TTS уже готова к использованию
//        //и пользователь позволил ей работать:
//        if(ready && allowed) {
//            HashMap<String, String> hash = new HashMap<String,String>();
//            hash.put(TextToSpeech.Engine.KEY_PARAM_STREAM,
//                    String.valueOf(AudioManager.STREAM_NOTIFICATION));
//            tts.speak(text, TextToSpeech.QUEUE_ADD, hash);
//        }
//    }
//
//    public void pause(int duration){
//        tts.playSilence(duration, TextToSpeech.QUEUE_ADD, null);
//    }
//
//    //Освобождаем оперативку устройства от лишней загрузки, когда TTS не работает:
//    public void destroy(){
//        tts.shutdown();
//    }
//}
