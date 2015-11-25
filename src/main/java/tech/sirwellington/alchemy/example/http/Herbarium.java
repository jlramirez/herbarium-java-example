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
public class Herbarium
{

    private final static Logger LOG = LoggerFactory.getLogger(Herbarium.class);

    public static void queryHerbariumByState() throws Exception
    {

        String state = selectRandomState();

        String url = "http://sweetgum.nybg.org/science/v1/institutions?country=u.s.a.";

        AlchemyHttp http = AlchemyHttp.newDefaultInstance();

        LOG.info("Making GET request to {} for state {}", url, state);

        HttpResponse response = http.go()
            .get()
            .usingQueryParam("state", state)
            .at(url);

        LOG.info("Response: {} : {}", response.statusCode(), response.responseHeaders());

        JsonElement body = response.body();
        LOG.info("Response Body: {}", body);
    }

    private static String selectRandomState()
    {
        Iterable<String> states = Splitter.on(", ")
            .omitEmptyStrings()
            .split("New York, New Jersey, California, Maryland, Virginia, DC, Florida, Texas, Kansas");

        ArrayList<String> statesList = Lists.newArrayList(states);
        return one(stringsFromFixedList(statesList));
    }

}
