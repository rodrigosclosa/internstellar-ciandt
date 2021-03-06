package com.ciandt.internstellarapi.helper;

import com.ciandt.internstellarapi.util.Constants;
import com.google.api.server.spi.response.ConflictException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by rodrigosclosa on 19/06/16.
 */

public class OneSignalHelper {

    private static final Logger log = Logger.getLogger(OneSignalHelper.class.getName());

    private RestClient restClient;

    public OneSignalHelper() {
        restClient = new RestClient();
    }

    public String SendPush(String customPushData, String mensagem, String tag) throws ConflictException {
        String retorno = "";

        log.log(Level.WARNING, "SendPush; customData: " +customPushData + "; Mensagem: "+ mensagem + "; tag: " + tag);

        StringBuilder pushData = new StringBuilder();

        pushData.append("{\n" +
                "  \"app_id\":"+ Constants.getInstance().getOneSignalAppId() + ",\n" +
                "  \"included_segments\": [\"All\"],\n" +
                "  \"data\": " + customPushData + ",\n" +
                "  \"contents\": {\"en\": \"" + mensagem + "\"}\n");

        if(tag != null && !tag.isEmpty()) {
            pushData.append(",  \"tags\": [" + tag + "]\n");
        }

        pushData.append("}");

        log.log(Level.WARNING, "SendPush; pushData:" + pushData.toString());

        ArrayList<RestHeaders> headers = new ArrayList<RestHeaders>();
        headers.add(new RestHeaders("Content-Type", "application/json"));
        headers.add(new RestHeaders("Authorization", "Basic " + Constants.getInstance().getOneSignalKey()));

        String pushReturn = restClient.Post("https://onesignal.com/api/v1/notifications",
                pushData.toString(),
                headers);

        log.log(Level.WARNING, "SendPush;retorno:" + pushReturn);

        if(pushReturn != null && !pushReturn.isEmpty()) {
            try {
                JSONObject jsonObj = new JSONObject(pushReturn);

                if(jsonObj != null) {
                    if(jsonObj.getString("id") != null && jsonObj.getInt("recipients") > 0 ) {
                        retorno = "OK";
                    } else {
                        throw new ConflictException("Ocorreu um erro ao enviar o Push.");
                    }
                }
            } catch (JSONException e) {
                throw new ConflictException("Erro no retorno JSON: " + e.getMessage());
            }

        } else {
            throw new ConflictException("Push não pode ser enviado. Verifique os parâmetros de envio.");
        }

        return retorno;
    }
}
