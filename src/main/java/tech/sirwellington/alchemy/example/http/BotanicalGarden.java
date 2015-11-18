/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tech.sirwellington.alchemy.example.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author SirWellington
 */
public class BotanicalGarden
{

    private final static Logger LOG = LoggerFactory.getLogger(BotanicalGarden.class);

    public static void main(String[] args) throws Exception
    {
        Herbarium.queryHerbariumByState();
    }

}
