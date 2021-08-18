package com.helevator.carservicetracker.jsonfilehandler;

import java.util.ArrayList;
import java.util.Collection;

public interface JsonOperations<T> {
    void mapObjectsToJSON(Collection<T> values);
    ArrayList<T> readObjectsFromJSON();
}