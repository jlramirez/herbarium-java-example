/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tech.sirwellington.alchemy.example.http;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.sirwellington.alchemy.http.AlchemyHttp;
import tech.sirwellington.alchemy.http.HttpResponse;

import static tech.sirwellington.alchemy.generator.AlchemyGenerator.one;
import static tech.sirwellington.alchemy.generator.StringGenerators.stringsFromFixedList;

/**
 *
 * @author SirWellington
 */
public class BotanicalGarden
{

    private final static Logger LOG = LoggerFactory.getLogger(BotanicalGarden.class);

    public static void main(String[] args) throws Exception
    {
        queryHerbariumByState();
    }

    private static void queryHerbariumByState() throws Exception
    {
        Iterable<String> states = Splitter.on(", ")
            .omitEmptyStrings()
            .split("NY, NJ, CA, MD, VA, DC, FL, TX, MN");

        ArrayList<String> statesList = Lists.newArrayList(states);
        String state = one(stringsFromFixedList(statesList));

        String url = "http://sweetgum.nybg.org/science/v1/institution/" + state;

        AlchemyHttp http = AlchemyHttp.newDefaultInstance();

        LOG.info("Making GET request to {}", url);

        HttpResponse response = http.go()
            .get()
            .at(url);

        LOG.info("Response: {} : {}", response.statusCode(), response.responseHeaders());

        JsonElement body = response.body();
        LOG.info("Response Body: {}", body);
    }
}
