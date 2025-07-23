package ec.edu.espe.openai.Service;

import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Service
public class TextToSpeechService {

    public String synthesizeText(String text, String fileName) {
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
            // Texto a convertir
            SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();

            // Voz
            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode("es-ES")
                    .setSsmlGender(SsmlVoiceGender.FEMALE)
                    .build();

            // Configuración del audio
            AudioConfig audioConfig = AudioConfig.newBuilder()
                    .setAudioEncoding(AudioEncoding.MP3)
                    .build();

            // Generar audio
            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);
            ByteString audioContents = response.getAudioContent();

            // carpeta de audio
            String folderPath = "src/main/resources/static/audio";
            File folder = new File(folderPath);
            if (!folder.exists()) folder.mkdirs();

            // Guardar archivo
            String outputFile = folderPath + "/" + fileName;
            try (OutputStream out = new FileOutputStream(outputFile)) {
                out.write(audioContents.toByteArray());
            }

            return outputFile;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
