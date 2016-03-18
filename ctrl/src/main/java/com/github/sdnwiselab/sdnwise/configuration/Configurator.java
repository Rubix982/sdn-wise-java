/* 
 * Copyright (C) 2015 SDN-WISE
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.sdnwiselab.sdnwise.configuration;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.util.logging.*;

/**
 * Holder of the different kind of configuration classes. It provides methods to
 * parse/write configurations from/to a JSON file.
 *
 * @author Sebastiano Milardo
 */
public class Configurator {

    /**
     * Parses a file given in input containing a JSON string and returns the
     * corresponding configurator object described in the file.
     *
     * @param fileName the path to the JSON file
     * @return a configurator object
     */
    public static final Configurator load(final InputStream fileName) {
        try {
            return (new Gson()).fromJson(new JsonReader(new
        InputStreamReader(fileName, "UTF-8")), Configurator.class);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Configurator.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private final ConfigAdaptation Adaptation = new ConfigAdaptation();
    private final ConfigController Controller = new ConfigController();
    private final ConfigFlowVisor Flowvisor = new ConfigFlowVisor();

    /**
     * Returns a configAdaptation object.
     *
     * @return a configAdaptation object
     */
    public final ConfigAdaptation getAdaptation() {
        return Adaptation;
    }

    /**
     * Returns a ConfigController object.
     *
     * @return a configController object
     */
    public final ConfigController getController() {
        return Controller;
    }

    /**
     * Returns a configFlowvisor object.
     *
     * @return a configFlowvisor object
     */
    public final ConfigFlowVisor getFlowvisor() {
        return Flowvisor;
    }

    /**
     * Returns a string representation of the object in JSON format.
     *
     * @return a JSON string representation of this object.
     */
    @Override
    public final String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

}
