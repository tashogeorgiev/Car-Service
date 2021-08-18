package com.helevator.carservicetracker.repositories;

import com.helevator.carservicetracker.data.repair.Item;
import com.helevator.carservicetracker.data.repair.Repairment;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The RepairRepository class stores the data for all the repairs
 */

public class RepairRepository implements Repository<Repairment>{
    /**
     * The repairs are stored in a HashMap, with the key being the
     * repairId and the value being the repairment for that Id
     */
    private final HashMap<Long, Repairment> repairsData = new HashMap<>();
    private final File repairsDataFile;


    /**
     * When the repository is instantiated, read the data from the repairsDataFile
     */
    public RepairRepository(){
        repairsDataFile = DataLoader.loadFile("repairsDataFile");
        readData();
    }

    /**
     * @param differentFileRepository Read the repairments data from a different file
     */
    public RepairRepository(File differentFileRepository){
        this.repairsDataFile = differentFileRepository;
        readData();
    }


    /**
     * When a new repairment is added, put it in the HashMap
     * by generating a unique Id for a key
     * @param repairment The repair we want to add
     */
    @Override
    public void addData(Repairment repairment) {
        repairsData.put(generateId(),repairment);
        saveRepairData();
    }


    /**
     * The repairs data is serialized as a HashMap,
     * so when reading it, it is casted from the file.
     * If successful, the read data is stored in the current HashMap
     */
    @Override
    public void readData() {
        try {
            FileInputStream fileInputStream = new FileInputStream(repairsDataFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            HashMap<Long, Repairment> readMap = (HashMap<Long, Repairment>)objectInputStream.readObject();

            if(readMap.size() > 0) {
                for (Long id : readMap.keySet()) {
                    repairsData.put(id, readMap.get(id));
                }
            }
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("Empty repairs data file.");
        }catch (ClassNotFoundException e){
            System.out.println("Error parsing data for Repairs from file");
        }
    }


    private void saveRepairData(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(repairsDataFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(repairsData);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add item to a repair given it's Id
     * @param repairId Id for the repair
     * @param item Item to add
     */
    public void addItemToRepair(long repairId, Item item){
        repairsData.get(repairId).addItem(item);
        saveRepairData();
    }

    public Repairment getRepairById(long id){
        return repairsData.get(id);
    }

    public long getIdForRepair(Repairment repairment){
        for (Map.Entry<Long, Repairment> entry : repairsData.entrySet()) {
            if(entry.getValue().equals(repairment)){
                return entry.getKey();
            }
        }
        return 0;
    }


    public boolean containsRepair(long id){
        return repairsData.containsKey(id);
    }

    /**
     * Generates an Id for a repair - 10 digits long
     * The first 6 digits are generated from the concatenation of the current date's numbers
     * The last 4 digits are a random number from 1000 to 9999
     * @return The generated Id
     */
    private long generateId(){
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
        int maxIdValue = 9999;
        int minIdValue = 1000;
        return Long.parseLong(dateFormat.format(dateNow) + (long) ((Math.random() * (maxIdValue - minIdValue)) + minIdValue));
    }

    /**
     * @return Return a deep copy of the repairs HashMap
     */
    public HashMap<Long,Repairment> getRepairsData(){
        return (HashMap<Long, Repairment>) repairsData.clone();
    }
}