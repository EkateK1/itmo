package org.example.managers;

import org.example.model.LabWork;
import org.example.utility.ConsoleManager;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.Math.max;

public class CollectionManager {

    private TreeMap<Integer, LabWork> collection = new TreeMap<>();
    private java.util.Date lastInitTime;
    private java.util.Date lastSaveTime;
    private final DumpManager dumpManager;
    private ConsoleManager console;

    public CollectionManager(DumpManager dumpManager){
        lastInitTime = null;
        lastSaveTime = null;
        this.dumpManager = dumpManager;
    }

    public java.util.Date getLastInitTime(){ return lastInitTime;}

    public java.util.Date getLastSaveTime(){return lastSaveTime;}

    public Map<Integer, LabWork> getCollection(){return collection;}

    public LabWork getCollectionById(Integer id){
        if (isEmpty()) return null;
        for (Map.Entry<Integer, LabWork> entry : collection.entrySet()){
            if (entry.getValue().getId().equals(id)) return entry.getValue();
        }
        return null;
    }

    public boolean isContain(LabWork labWork){
        if (labWork == null || getCollectionById(labWork.getId()) == null) return false;
        return collection.containsValue(labWork);
    }

    public boolean add(LabWork labWork){
        if (!isEmpty()){
            if(isContain(labWork)) {return false;}}
        if (labWork.getId() <= getMaxId()){
            labWork.setId(getMaxId()+1);
        }
        collection.put(labWork.getId(),labWork);
        return true;
    }

    public boolean addById(Integer id, LabWork labWork){
        if (!isEmpty()){
            if(isContain(labWork)) {return false;}
            if (getCollectionById(id) != null) {return false;}}
        labWork.setId(id);
        collection.put(id,labWork);
        return true;
    }

    public boolean saveCollection(DumpManager dumpManager){
        if (dumpManager.writeIntoFile(collection)){
            lastSaveTime = new Date();
            return true;
        }
        return false;
    }

    public void loadCollection(DumpManager dumpManager){
        collection = dumpManager.readFromFile();
        lastInitTime = new Date();
    }

    public String collectionType(){
        return collection.getClass().getName();
    }

    public int collectionSize(){
        return collection.size();
    }

    public String printCollection(){
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, LabWork> entry : collection.entrySet()){
            result.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }
        return result.toString();
    }

    public void removeById(Integer id){
        if (getCollectionById(id) != null) collection.remove(id);
    }
    public void clearCollection(){
        collection.clear();
    }

    public Integer getMaxId(){
        Integer maxId = 0;
        for (Map.Entry<Integer, LabWork> entry : collection.entrySet()){
            maxId = max(entry.getKey(), maxId);
        }
        return maxId;
    }

    public LabWork getFirstElement(){
        if (collection.isEmpty()) return null;
        return collection.get(collection.firstKey());
    }
    public boolean isEmpty(){
        return collection.isEmpty();
    }
    public void updateCollection(TreeMap<Integer, LabWork> newCollection){
        collection = newCollection;
    }

}
