package com.example.languagetranslator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;
import com.ibm.watson.language_translator.v3.util.Language;

public class MainActivity extends AppCompatActivity {
    private LanguageTranslator translationService;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        translationService = initLanguageTranslatorService();
        new TranslationTask().execute("Hello World and my friend");
    }

    private LanguageTranslator initLanguageTranslatorService() {
        Authenticator authenticator
                = new
                IamAuthenticator(getString(R.string.translateapikey));
        LanguageTranslator service = new LanguageTranslator("2018-05-" +
                "01", authenticator);

        service.setServiceUrl(getString(R.string.translateurl));
        return service;
    }

    private class TranslationTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... string) {
            TranslateOptions translateOptions =
                    new TranslateOptions.Builder()
                            .addText(string[0])
                            .source(Language.ENGLISH)
                            .target("ar")
                            .build();
            TranslationResult result = translationService.translate(translateOptions).execute().getResult();
            String firstTranslation =
                    result.getTranslations().get(0).getTranslation();
            return firstTranslation;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv.setText(s);
        }
    }


}
